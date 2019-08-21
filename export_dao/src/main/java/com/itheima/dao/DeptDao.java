package com.itheima.dao;

import com.itheima.domain.Dept;

import java.util.List;

public interface DeptDao {
    List<Dept> findList(String companyId);
    List<Dept> findAllDept(String companyId);
    void saveDept(Dept dept);

    void updDept(Dept dept);

    Dept findById(String id);

    void delDept(String id);
}
