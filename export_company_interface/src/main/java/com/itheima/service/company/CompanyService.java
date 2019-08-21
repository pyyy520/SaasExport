package com.itheima.service.company;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Company;

import java.util.List;

public interface CompanyService {
    //查询全部
    List<Company> findAll();
    //根据id查询
    Company findById(String id);
    //删除
    void delete(String id);
    //保存
    void save(Company company);
    //更新
    void update(Company company);
    //分页
    PageInfo findPageByHelper(int page, int size);
}
