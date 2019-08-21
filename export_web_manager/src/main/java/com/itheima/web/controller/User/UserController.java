package com.itheima.web.controller.User;

import com.github.pagehelper.PageInfo;
import com.itheima.utils.Encrypt;
import com.itheima.web.BaseController;
import com.itheima.domain.Dept;
import com.itheima.domain.Role;
import com.itheima.domain.User;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import com.itheima.utils.UtilFuns;
import com.itheima.web.utils.JmsTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JmsTemplateUtils jmsTemplateUtils;

    @RequestMapping(value = "/set",name = "跳转个人设置")
    public String set(){
        request.setAttribute("user",user);
        return "system/user/user-set";
    }
    @RequestMapping(value = "setUpdate",name = "个人设置")
    public String setUpdate(User user1){
        user.setUserName(user1.getUserName());
        user.setGender(user1.getGender());
        user.setTelephone(user1.getTelephone());
        user.setBirthday(user1.getBirthday());
        userService.updUser(user);
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        return "redirect:/login.do?email="+user.getEmail()+"&password="+session.getAttribute("pass");
    }
    //查询当前企业用户
    @RequestMapping(value = "/list",name = "查询当前企业用户")
    public String findAll(@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "5")int pageSize){
        PageInfo page = userService.findAll(companyId, pageNum, pageSize);
        request.setAttribute("page",page);
        return "system/user/user-list";
    }
    //增加用户(到添加)
    @RequestMapping(value = "/toAdd",name = "到添加用户页面")
    public String toAdd(){
        //根据公司id加载所有部门
        List<Dept> dept = userService.findDept(companyId);
        request.setAttribute("deptList",dept);
        return "system/user/user-add";
    }
    //修改用户(修改页面数据回显)
    @RequestMapping(value = "/toUpdate",name = "到修改用户页面")
    public String toUpdate(String id){
        User user = userService.findUserById(id);
        List<Dept> dept = userService.findDept(companyId);
        request.setAttribute("deptList",dept);
        request.setAttribute("user",user);
        return "system/user/user-update";
    }
    //增加/修改用户
    @RequestMapping(value = "/edit",name = "修改/增加用户")
    public String edit(User user){
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        //密码进行md5加密
        user.setPassword(Encrypt.md5(user.getPassword(),user.getEmail()));
        if(UtilFuns.isEmpty(user.getId())){
            String email = user.getEmail();
            String msg = "欢迎加入saas大家庭,你的登录账号是："+email;
            String password = user.getPassword();
            //如果id为空,添加
            user.setId(UUID.randomUUID().toString().replace("-",""));
            userService.saveUser(user);
            //使用中间件发送邮件
            jmsTemplateUtils.send(email,password,msg);
        }else {
            //修改
            userService.updUser(user);
        }
        //重定向到查询页面
        return "redirect:/system/user/list.do";
    }
    @RequestMapping(value = "/delete",name = "删除用户")
    public String delUser(String id){
        userService.delUser(id);
        //重定向到查询页面
        return "redirect:/system/user/list.do";
    }
    //回显角色信息
    @RequestMapping(value = "/roleList" ,name = "回显角色信息")
    public String roleList(String id){
        //通过id查询用户信息
        User user = userService.findUserById(id);
        //根据id查询用户的角色(中间表)
        List<Role> userRoleList = roleService.findByUserRole(user.getId());
        //将用户id拼成字符串
        String roleStr="";
        for (Role role : userRoleList) {
            roleStr+=role.getId()+",";
        }
        //查询所有角色
        List<Role> roleList = roleService.findAllRole(companyId);
        request.setAttribute("userRoleStr",roleStr);
        request.setAttribute("user",user);
        request.setAttribute("roleList",roleList);
        return  "system/user/user-role";
    }
//        更新用户角色
        @RequestMapping("/changeRole")
        public String changeRole(String userid,String[]roleIds){
            userService.changeRole(userid,roleIds);
            return "redirect:/system/user/list.do";
        }
}
