package com.itheima.web.controller.LoginController;


import com.itheima.domain.Module;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.UtilFuns;
import com.itheima.web.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;
	@RequestMapping("/login")
	public String login(String email,String password) {
        session.setAttribute("pass",password);
//	    //如果为空
//	    if(UtilFuns.isEmpty(email)||UtilFuns.isEmpty(password)){
//            return "forward:login.jsp";
//        }
//	    User user = userService.findUserByEmail(email);
//	    if(user==null|| !user.getPassword().equals(password)){
//	        request.setAttribute("error","账号或密码错误!");
//            return "forward:login.jsp";
//        }

//	    //根据用户等级动态展示菜单
//        List<Module>moduleList = userService.findUserModule(user);
//        session.setAttribute("modules",moduleList);
//	    session.setAttribute("loginUser",user);

            //获取subject
            Subject subject = SecurityUtils.getSubject();
        try {
            //调用subject的login方法
            UsernamePasswordToken upToken = new UsernamePasswordToken(email,password);
            subject.login(upToken);
            //登录成功
            //从shiro获取用户对象
            User user =(User)subject.getPrincipal();
//            根据用户等级动态展示菜单
            List<Module>moduleList = userService.findUserModule(user);
            session.setAttribute("loginUser",user);
            session.setAttribute("modules",moduleList);
            return "home/main";
        } catch (Exception e) {
            //登录失败
            System.out.println("账号或密码错误...");
            request.setAttribute("error","用户名或密码错误");
            return "forward:login.jsp";
        }
    }

    //退出
    @RequestMapping(value = "/logout",name="用户登出")
    public String logout(){
	    //退出
        SecurityUtils.getSubject().logout();
        return "forward:login.jsp";
    }

    @RequestMapping(value = "/home",name = "用户登录")
    public String home(){
	    return "home/home";
    }
}
