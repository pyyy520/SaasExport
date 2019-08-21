package com.itheima.company.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.CompanyDao;
import com.itheima.domain.Company;
import com.itheima.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public Company findById(String id) {
        return companyDao.findCompanyById(id);
    }

    @Override
    public void delete(String id) {
        companyDao.delCompany(id);
    }

    @Override
    public void save(Company company) {
        company.setId(UUID.randomUUID().toString());
        companyDao.addCompany(company);
    }

    @Override
    public void update(Company company) {
        companyDao.updCompany(company);
    }

    public PageInfo findPageByHelper(int page, int size) {
        PageHelper.startPage(page,size);
        List<Company> list = companyDao.findAll();
        return new PageInfo(list);
    }
}
