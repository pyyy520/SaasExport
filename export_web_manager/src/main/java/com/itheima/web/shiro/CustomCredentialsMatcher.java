package com.itheima.web.shiro;

import com.itheima.utils.Encrypt;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

//自定义密码比较器
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //获取用户的账号密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String email = upToken.getUsername();
        String password = new String(upToken.getPassword());
        //获取数据库密码
        String dbPassword = (String) info.getCredentials();
        //对用户密码进行加密
        password = Encrypt.md5(password,email);
        //比较两次密码
        boolean equals = password.equals(dbPassword);
        return equals;
    }
}
