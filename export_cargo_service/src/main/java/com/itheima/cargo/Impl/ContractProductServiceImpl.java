package com.itheima.cargo.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.ContractProductService;
import com.itheima.dao.cargo.ContractDao;
import com.itheima.dao.cargo.ContractProductDao;
import com.itheima.dao.cargo.ExtCproductDao;
import com.itheima.domain.cargo.*;
import com.itheima.domain.cargo.ContractProductVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ContractProductServiceImpl implements ContractProductService {
    @Autowired
    private ContractProductDao contractProductDao;
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ExtCproductDao extCproductDao;
    @Override
    public void save(ContractProduct contractProduct) {
        System.out.println(contractProduct);
        //设置货物id
        contractProduct.setId(UUID.randomUUID().toString().replace("-",""));
        //货物总金额
        double total = contractProduct.getPrice() * contractProduct.getCnumber();
        contractProduct.setAmount(total);
        //合同总金额
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        System.out.println(contract);
        contract.setTotalAmount(contract.getTotalAmount()+total);
        //修改合同货件数
        contract.setProNum(contract.getProNum()+1);
        //保存货物
        contractProductDao.insertSelective(contractProduct);
        //保存合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void update(ContractProduct contractProduct) {
        //查询货物以前总金额
        ContractProduct contractProduct1 = contractProductDao.selectByPrimaryKey(contractProduct.getId());
        Double oldAmount = contractProduct1.getAmount();
        //货物现在总金额
        double total = contractProduct.getPrice() * contractProduct.getCnumber();
        contractProduct.setAmount(total);
        //合同总金额
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        //设置合同总金额
        contract.setTotalAmount(contract.getTotalAmount()-oldAmount+total);
        //修改货物
        contractProductDao.updateByPrimaryKeySelective(contractProduct);
        //修改合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void delete(String id) {
        //获取货物总金额
        ContractProduct contractProduct = contractProductDao.selectByPrimaryKey(id);
        Double contractProductAmount = contractProduct.getAmount();
        //获取附件总金额/总件数
        ExtCproductExample extCproductExample = new ExtCproductExample();
        ExtCproductExample.Criteria criteria = extCproductExample.createCriteria();
        criteria.andCompanyIdEqualTo(id);
        //该货物下的所有附件
        List<ExtCproduct> extCproducts = extCproductDao.selectByExample(extCproductExample);
        for (ExtCproduct extCproduct : extCproducts) {
            //货物+附件总金额
            contractProductAmount+=extCproduct.getAmount();
            //删除附件
            extCproductDao.deleteByPrimaryKey(extCproduct.getId());
        }
        //删除货物
        contractProductDao.deleteByPrimaryKey(id);
        //合同总金额
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        contract.setTotalAmount(contract.getTotalAmount()-contractProductAmount);
        //修改合同货件数
        contract.setProNum(contract.getProNum()-1);
        //修改合同附件数
        contract.setExtNum(contract.getExtNum()-extCproducts.size());
        //修改合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public ContractProduct findById(String id) {
        ContractProduct contractProduct = contractProductDao.selectByPrimaryKey(id);
        return contractProduct;
    }

    @Override
    public PageInfo findAll(ContractProductExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<ContractProduct> list = contractProductDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public void saveAll(ArrayList<ContractProduct> list) {
        for (ContractProduct contractProduct : list) {
            save(contractProduct);
        }
    }

    @Override
    public List<ContractProductVo> findByDate(String companyId,String inputDate) {
        return contractProductDao.findByDate(companyId,inputDate);
    }
}
