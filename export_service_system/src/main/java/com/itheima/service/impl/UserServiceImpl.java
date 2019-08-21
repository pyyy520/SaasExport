package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.DeptDao;
import com.itheima.dao.ModuleDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.Dept;
import com.itheima.domain.Module;
import com.itheima.domain.Role;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private ModuleDao moduleDao;
    @Override
    public PageInfo findAll(String companyId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userDao.findAll(companyId);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public List<Dept> findDept(String companyId) {
        return deptDao.findList(companyId);
    }

    @Override
    public void updUser(User user) {
        userDao.updUser(user);
    }

    @Override
    public User findUserById(String id) {
        return userDao.findUserById(id);
    }

    @Override
    public void delUser(String id) {
        userDao.delUser(id);
    }

    @Override
    public void changeRole(String userid, String[] roleIds) {
        //删除用户角色
        userDao.deletUserRole(userid);
        //更新用户角色
        for (String roleId : roleIds) {
        userDao.saveUserRole(userid,roleId);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        //判断用户等级
        return userDao.findUserByEmail(email);
    }

    @Override
    public List<Module> findUserModule(User user) {
        if(user.getDegree()==0){
            //Saas管理员
           return moduleDao.findUserModule(0);
        }else if(user.getDegree()==1){
            //企业管理员
           return moduleDao.findUserModule(1);
        }else{
            //其他用户(基于rbac)
           return moduleDao.findUserModuleById(user.getId());
        }
    }

}
