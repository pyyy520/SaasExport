package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.RoleDao;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public PageInfo findAll(String companyId,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> list = roleDao.findAll(companyId);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public void save(Role role) {
        role.setId(UUID.randomUUID().toString().replace("-",""));
        roleDao.save(role);
    }

    @Override
    public Role findRoleById(String id) {
        return roleDao.findRoleById(id);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void delete(String id) {
        roleDao.delete(id);
    }

    @Override
    public void updateRoleModule(String roleid, String moduleIds) {
        //根据id删除中间表所有权限
        roleDao.deleteRoleModule(roleid);
        String[] modules = moduleIds.split(",");
        for (String module : modules) {
            roleDao.saveRoleModule(roleid,module);
        }
    }

    @Override
    public List<Role> findAllRole(String companyId) {
        return roleDao.findAll(companyId);
    }

    @Override
    public List<Role> findByUserRole(String id) {
        return roleDao.findByUserRole(id);
    }
}
