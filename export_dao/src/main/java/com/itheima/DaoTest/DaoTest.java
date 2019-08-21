package com.itheima.DaoTest;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoTest {
    @Test
    public void cs(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
        UserDao dao = ac.getBean(UserDao.class);
        dao.delUser("002108e2-9a10-4510-9683-8d8fd1d374ef");
        System.out.println("执行成功");
    }
}
