package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Dept;

import java.util.List;

public interface DeptService {
    PageInfo findList(String companyId, int pageNumber, int pageSize);

    List<Dept> findAllDept(String companyId);

    void saveDept(Dept dept);

    void updDept(Dept dept);

    Dept findById(String id);

    void delDept(String id);
}
