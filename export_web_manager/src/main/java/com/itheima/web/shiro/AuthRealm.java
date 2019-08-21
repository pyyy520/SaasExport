package com.itheima.web.shiro;

import com.itheima.domain.Module;
import com.itheima.domain.User;
import com.itheima.service.ModuleService;
import com.itheima.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
//自定义reaml
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;
    //用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("拦截一条非法请求......");
        //获取安全数据
        User user =(User)principalCollection.getPrimaryPrincipal();
        //查询当前用户的操作权限
        List<Module> modules = moduleService.findRoleModule(user.getId());
        //设置返回值
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //set集合存放所有菜单名称
        HashSet<String> perms = new HashSet<>();
        for (Module module : modules) {
            perms.add(module.getName());
        }
        info.setStringPermissions(perms);
        return info;
    }
    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //参数 传递过来的Token
//        返回值:
//        如果用户不存在,返回null 抛出异常
//        如果用户存在 返回安全数据 自动调用密码对比器
        //获取用户的账号密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String email = upToken.getUsername();
        String password = new String(upToken.getPassword());
        //根据email查询用户
        User user = userService.findUserByEmail(email);
        if(user!=null){
            //如果不为空 返回安全数据
            return new SimpleAuthenticationInfo(user,user.getPassword(),this.getName()) ;
        }else{
            //如果用户不存在 返回null 抛异常
            return null;
        }

    }
}
