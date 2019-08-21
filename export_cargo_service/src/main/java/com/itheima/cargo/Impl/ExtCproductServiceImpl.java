package com.itheima.cargo.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.ExtCproductService;
import com.itheima.dao.cargo.ContractDao;
import com.itheima.dao.cargo.ExtCproductDao;
import com.itheima.domain.cargo.Contract;
import com.itheima.domain.cargo.ExtCproduct;
import com.itheima.domain.cargo.ExtCproductExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class ExtCproductServiceImpl implements ExtCproductService {
    @Autowired
    private ExtCproductDao extCproductDao;
    @Autowired
    private ContractDao contractDao;
    @Override
    public void save(ExtCproduct extCproduct) {
        //设置附件id
        extCproduct.setId(UUID.randomUUID().toString().replace("-",""));
        //当前附件总金额
        double amount = extCproduct.getPrice() * extCproduct.getCnumber();
        extCproduct.setAmount(amount);
        //保存附件
        extCproductDao.insertSelective(extCproduct);
        //获取当前附件合同
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        //修改合同附件数
        contract.setExtNum(contract.getExtNum()+1);
        //修改合同总金额
        contract.setTotalAmount(contract.getTotalAmount()+amount);
        //修改合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void update(ExtCproduct extCproduct) {
        //当前附件旧的总金额
        ExtCproduct extCproduct1 = extCproductDao.selectByPrimaryKey(extCproduct.getId());
        Double oldAmount = extCproduct1.getAmount();
        //当前附件新的总金额
        Double amount = extCproduct.getPrice()*extCproduct.getCnumber();
        extCproduct.setAmount(amount);
        //修改附件
        extCproductDao.updateByPrimaryKeySelective(extCproduct);
        //合同的总金额
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        //设置合同的金额
        contract.setTotalAmount(contract.getTotalAmount()-oldAmount+amount);
        //修改合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void delete(String id) {
        //附件总金额
        ExtCproduct extCproduct = extCproductDao.selectByPrimaryKey(id);
        Double extCproductAmount = extCproduct.getAmount();
        //合同总金额
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        contract.setTotalAmount(contract.getTotalAmount()-extCproductAmount);
        //合同附件数
        contract.setExtNum(contract.getExtNum()-1);
        //删除附件
        extCproductDao.deleteByPrimaryKey(id);
        //修改合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public ExtCproduct findById(String id) {
        ExtCproduct extCproduct = extCproductDao.selectByPrimaryKey(id);
        return extCproduct;
    }

    @Override
    public PageInfo findAll(ExtCproductExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<ExtCproduct> extCproducts = extCproductDao.selectByExample(example);
        return new PageInfo(extCproducts);
    }
}
