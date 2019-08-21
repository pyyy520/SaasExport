package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.ModuleDao;
import com.itheima.domain.Module;
import com.itheima.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ModuleImpl implements ModuleService {
    @Autowired
    private ModuleDao moduleDao;
    @Override
    public PageInfo findAll(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Module> list = moduleDao.findAll();
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public List<Module> findAllModule() {
        List<Module> list = moduleDao.findAllModule();
        return list;
    }

    @Override
    public void save(Module module) {
        moduleDao.save(module);
    }

    @Override
    public void update(Module module) {
        moduleDao.update(module);
    }

    @Override
    public Module findById(String id) {
        return moduleDao.findById(id);
    }

    @Override
    public List<Module> findRoleModule(String id) {
        return moduleDao.findRoleModule(id);
    }
}
