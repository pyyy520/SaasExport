package com.itheima.cargo.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.ContractService;
import com.itheima.cargo.ExportService;
import com.itheima.dao.cargo.*;
import com.itheima.domain.cargo.*;
import com.itheima.domain.vo.ExportProductResult;
import com.itheima.domain.vo.ExportResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class ExportServiceImpl implements ExportService {
    @Autowired
    private ExportDao exportDao;
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ContractProductDao contractProductDao;
    @Autowired
    private ExportProductDao exportProductDao;
    @Autowired
    private ExtCproductDao extCproductDao;
    @Autowired
    private ExtEproductDao extEproductDao;

    @Override
    public Export findById(String id) {
        return exportDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Export export) {
        //1.设置合同状态
        String[] contractIds = export.getContractIds().split(",");
        //报运单中合同id(打断字段)
        StringBuffer exportContractIds = new StringBuffer();
        for (String contractId : contractIds) {
            //查询合同
            Contract contract = contractDao.selectByPrimaryKey(contractId);
            //设置合同状态
            contract.setState(2);
            exportContractIds.append(contract.getId()).append("");
            //
//            修改合同
            contractDao.updateByPrimaryKeySelective(contract);
        }
        //2.设置报运单属性
        export.setId(UUID.randomUUID().toString().replace("-",""));
        export.setCreateTime(new Date());
        export.setInputDate(new Date());
        export.setState(0);
        //设置打断字段
        export.setCustomerContract(exportContractIds.toString());
        //3.保存报运单下的货物
        //查询合同下的货物
        ContractProductExample contractProductExample = new ContractProductExample();
        ContractProductExample.Criteria criteria = contractProductExample.createCriteria();
        criteria.andContractIdIn(Arrays.asList(contractIds));
        List<ContractProduct> contractProducts = contractProductDao.selectByExample(contractProductExample);
        //保存合同货物id 报运单货物id
        HashMap<String, String> map = new HashMap<>();
        for (ContractProduct contractProduct : contractProducts) {
            //创建报运货物对象
            ExportProduct exportProduct = new ExportProduct();
            //合同货物导入报运单货物对象
            BeanUtils.copyProperties(contractProduct,exportProduct);
            //设置报运单货物id
            exportProduct.setId(UUID.randomUUID().toString().replace("-",""));
            //设置报运单id
            exportProduct.setExportId(export.getId());
            //保存合同货物id和报运单货物id
            map.put(contractProduct.getId(),exportProduct.getId());
            //保存报运单货物
            exportProductDao.insertSelective(exportProduct);
        }
        //4.保存报运单下的货物附件
        //获取合同下的货物附件
        ExtCproductExample extCproductExample = new ExtCproductExample();
        ExtCproductExample.Criteria extCproduct = extCproductExample.createCriteria();
        extCproduct.andContractIdIn(Arrays.asList(contractIds));
        //合同下的所有附件
        List<ExtCproduct> extCproducts = extCproductDao.selectByExample(extCproductExample);
        for (ExtCproduct cproduct : extCproducts) {
            //报运单的附件
            ExtEproduct extEproduct = new ExtEproduct();
            //封装报运单数据
            BeanUtils.copyProperties(cproduct,extEproduct);
            //设置报运单附件id
            extEproduct.setId(UUID.randomUUID().toString().replace("-",""));
            //设置附件的报运单id
            extEproduct.setExportId(export.getId());
            //设置报运单货物id
            extEproduct.setExportProductId(map.get(cproduct.getContractProductId()));
            //设置报运单附件的报运单id

            //保存附件
            extEproductDao.insertSelective(extEproduct);
        }
        //5.保存报运单
        export.setProNum(contractProducts.size());
        export.setExtNum(extCproducts.size());
        exportDao.insertSelective(export);
    }

    @Override
    public void update(Export export) {
        //修改报运单
        exportDao.updateByPrimaryKeySelective(export);
        //修改报运单下的货物
        List<ExportProduct> exportProducts = export.getExportProducts();
        for (ExportProduct exportProduct : exportProducts) {
            exportProductDao.updateByPrimaryKeySelective(exportProduct);
        }
    }

    @Override
    public void delete(String id) {
        //删除报运单下的货物
        exportProductDao.deleteByExportId(id);
        //删除报运单下的附件
        extEproductDao.deleteByExportId(id);
        //删除报运单
        exportDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ExportExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Export> list = exportDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public void updateE(ExportResult exportResult) {
        //查询报运单
        Export export = exportDao.selectByPrimaryKey(exportResult.getExportId());
        //设置状态和备注
        export.setState(exportResult.getState());
        export.setRemark(exportResult.getRemark());
        //更新报运单
        exportDao.updateByPrimaryKeySelective(export);
        //设置报运单下的货物税金
        if(exportResult.getProducts()!=null){
            for (ExportProductResult product : exportResult.getProducts()) {
                //获取报运单货物
                ExportProduct exportProduct = exportProductDao.selectByPrimaryKey(product.getExportProductId());
                exportProduct.setTax(product.getTax());
                //更新报运单货物
                exportProductDao.updateByPrimaryKeySelective(exportProduct);
            }
        }
    }
    @Override
    public int findByIds(String id) {
        String[] split = id.split(",");
        int state=0;
        for (String i :split){
            Export export = exportDao.selectByPrimaryKey(i);
            if (export.getState() == 2) {
                state++;
            }
        }
        //判断state是否都为2
        if (state == split.length) {
            return 1;
        }else {
            return 0;
        }


    }

}
