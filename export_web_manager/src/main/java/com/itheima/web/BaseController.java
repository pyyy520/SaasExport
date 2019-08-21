package com.itheima.web;

import com.itheima.domain.Company;
import com.itheima.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {
    public HttpServletRequest request;
    public HttpServletResponse response;
    public HttpSession session;
    public User user;
//    企业id和企业name
    public String companyId;
    public String companyName;
    protected User getLoginUser(){
        return (User)session.getAttribute("loginUser");
    }
    //所有方法执行前执行一次
    @ModelAttribute
    public void init(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        this.request=request;
        this.response=response;
        this.session=session;
        User loginUser = (User) session.getAttribute("loginUser");
        this.user=loginUser;
        if(loginUser!=null){
            this.companyId=loginUser.getCompanyId();
            this.companyName=loginUser.getUserName();

        }
    }
}
