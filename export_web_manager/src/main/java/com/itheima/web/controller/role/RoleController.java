package com.itheima.web.controller.role;

import com.github.pagehelper.PageInfo;
import com.itheima.web.BaseController;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import com.itheima.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @RequestMapping(value = "/list",name = "查询所有角色")
    public String findAll(@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "5")int pageSize){
        PageInfo page = roleService.findAll(companyId, pageNum, pageSize);
        request.setAttribute("page",page);
        return "system/role/role-list";
    }
    //到添加页面
    @RequestMapping(value = "/toAdd",name = "到添加页面")
    public String toAdd(){
        return "system/role/role-add";
    }
    //到修改页面
    @RequestMapping(value = "/toUpdate",name = "到修改页面")
    public String toUpdate(String id){
        Role role = roleService.findRoleById(id);
        request.setAttribute("role",role);
        return "system/role/role-update";
    }
    @RequestMapping(value = "/edit",name = "修改/添加角色")
    public String edit(Role role){
        role.setCompanyId(companyId);
        role.setCompanyName(companyName);
        if(UtilFuns.isEmpty(role.getId())){
            //如果为空 添加角色
            roleService.save(role);
        }else {
            //修改角色
            roleService.update(role);
        }
        return "redirect:/system/role/list.do";
    }
    @RequestMapping(value = "/delete",name = "删除角色")
    public String delete(String id){
        roleService.delete(id);
        return "redirect:/system/role/list.do";
    }
    //到修改权限的页面
    @RequestMapping(value = "/roleModule",name = "到修改权限页面")
    public String roleModule(String roleid){
        //查询当前角色所有权限
        Role role = roleService.findRoleById(roleid);
        request.setAttribute("role",role);
        return "system/role/role-module";
    }
    //给角色分配权限
    @RequestMapping(value = "/updateRoleModule",name = "给角色分配权限")
    public String updateRoleModule(String roleid,String moduleIds){
        roleService.updateRoleModule(roleid,moduleIds);
        return "redirect:/system/role/list.do";
    }
}
