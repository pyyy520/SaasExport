package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Dept;
import com.itheima.domain.Module;
import com.itheima.domain.Role;
import com.itheima.domain.User;

import java.util.List;

public interface UserService {
    PageInfo findAll(String companyId, int pageNum, int pageSize);

    void saveUser(User user);

    List<Dept> findDept(String companyId);

    void updUser(User user);

    User findUserById(String id);

    void delUser(String id);

    void changeRole(String userid, String[] roleIds);

    User findUserByEmail(String email);

    List<Module> findUserModule(User user);
}
