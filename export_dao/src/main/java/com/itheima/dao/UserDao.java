package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<User> findAll(String companyId);

    void saveUser(User user);

    void updUser(User user);

    User findUserById(String id);

    void delUser(String id);

    List<Role> findUserRole(String id);

    void deletUserRole(String userid);

    void saveUserRole(@Param("userid") String userid, @Param("roleId") String roleId);

    User findUserByEmail(String email);
}
