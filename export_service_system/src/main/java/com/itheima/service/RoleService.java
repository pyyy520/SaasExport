package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {

    PageInfo findAll(String companyId, int pageNum, int pageSize);

    void save(Role role);

    Role findRoleById(String id);

    void update(Role role);

    void delete(String id);

    void updateRoleModule(String roleid,String moduleIds);

    List<Role> findAllRole(String companyId);

    List<Role> findByUserRole(String id);
}
