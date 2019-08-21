package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.DeptDao;
import com.itheima.domain.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;
    @Override
    public PageInfo findList(String companyId,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Dept> list = deptDao.findList(companyId);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public List<Dept> findAllDept(String companyId) {
        return deptDao.findAllDept(companyId);
    }

    @Override
    public void saveDept(Dept dept) {
        deptDao.saveDept(dept);
    }

    @Override
    public void updDept(Dept dept) {
        deptDao.updDept(dept);
    }

    @Override
    public Dept findById(String id) {
        return deptDao.findById(id);
    }

    @Override
    public void delDept(String id) {
        deptDao.delDept(id);
    }
}
