package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Module;

import java.util.List;


public interface ModuleService {

    PageInfo findAll(int pageNum, int pageSize);

    List<Module> findAllModule();

    void save(Module module);

    void update(Module module);

    Module findById(String id);

    List<Module> findRoleModule(String id);
}
