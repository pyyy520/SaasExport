package com.itheima.dao;

import com.itheima.domain.Module;

import java.util.List;

public interface ModuleDao {
    List<Module> findAll();

    List<Module> findAllModule();

    void save(Module module);

    void update(Module module);

    Module findById(String id);

    List<Module> findRoleModule(String id);

    List<Module> findUserModule(Integer degree);

    List<Module> findUserModuleById(String id);
}
