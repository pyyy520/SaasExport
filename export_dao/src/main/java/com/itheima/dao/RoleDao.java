package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {
    List<Role> findAll(String companyId);

    void save(Role role);

    Role findRoleById(String id);

    void update(Role role);

    void delete(String id);

    void saveRoleModule(@Param("roleid") String roleid,@Param("module") String module);

    void deleteRoleModule(String roleid);

    List<Role> findAllRole();

    List<Role> findByUserRole(String id);
}
