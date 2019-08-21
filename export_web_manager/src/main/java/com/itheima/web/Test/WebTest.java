package com.itheima.web.Test;

import com.itheima.web.BaseController;
import com.itheima.domain.User;
import com.itheima.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WebTest extends BaseController {
    @Test
    public void cs(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext-tx.xml");
        UserServiceImpl bean = ac.getBean(UserServiceImpl.class);
        User userById = bean.findUserById("002108e2-9a10-4510-9683-8d8fd1d374ef");
        System.out.println(userById);
        System.out.println("执行成功");
    }
}
