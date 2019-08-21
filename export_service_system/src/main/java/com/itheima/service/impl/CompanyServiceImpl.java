package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.CompanyDao;
import com.itheima.domain.Company;
import com.itheima.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;
    @Override
    public PageInfo findAll(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Company> list = companyDao.findAll();
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public void addCompany(Company company) {
        companyDao.addCompany(company);
    }

    @Override
    public Company findCompanyById(String id) {
        return companyDao.findCompanyById(id);
    }

    @Override
    public void updCompany(Company company) {
        companyDao.updCompany(company);
    }

    @Override
    public void delCompany(String id) {
        companyDao.delCompany(id);
    }
}
