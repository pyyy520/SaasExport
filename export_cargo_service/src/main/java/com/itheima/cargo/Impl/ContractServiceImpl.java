package com.itheima.cargo.Impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.ContractService;
import com.itheima.dao.cargo.ContractDao;
import com.itheima.domain.cargo.Contract;
import com.itheima.domain.cargo.ContractExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ContractServiceImpl  implements ContractService {
    @Autowired
    private ContractDao contractDao;
    @Override
    public Contract findById(String id) {
        return contractDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Contract contract) {
        // 设置合同的保存数据
        contract.setId(UUID.randomUUID().toString().replace("-",""));
        // 设置合同状态  0:草稿  1：已提交  2:已报运
        contract.setState(0);
        // 设置合同的保存时间
        contract.setCreateTime(new Date());
        contract.setUpdateTime(new Date());
        // 设置合同的总金额
        contract.setTotalAmount(0.0);
        // 设置货物数
        contract.setProNum(0);
        // 设置附件数
        contract.setExtNum(0);
        contractDao.insertSelective(contract);
    }

    @Override
    public void update(Contract contract) {
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void delete(String id) {
        contractDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ContractExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Contract> list = contractDao.selectByExample(example);
        return new PageInfo(list);
    }
}
