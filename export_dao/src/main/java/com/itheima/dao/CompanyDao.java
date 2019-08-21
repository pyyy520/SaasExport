package com.itheima.dao;

import com.itheima.domain.Company;

import java.util.List;

public interface CompanyDao {
    List<Company>findAll();

    void addCompany(Company company);

    Company findCompanyById(String id);

    void updCompany(Company company);

    void delCompany(String id);
}
