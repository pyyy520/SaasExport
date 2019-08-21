package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Company;
import java.util.List;

public interface CompanyService {
    PageInfo findAll(int pageNum, int pageSize);

    void addCompany(Company company);

    Company findCompanyById(String id);

    void updCompany(Company company);

    void delCompany(String id);
}
