package com.itheima.listener;

import com.itheima.utils.MailUtils;
import org.springframework.stereotype.Component;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class EmailListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            MapMessage map = (MapMessage) message;
            String email = map.getStringProperty("email");
            String password = map.getStringProperty("password");
            String msg = map.getStringProperty("msg");
            //调用工具类发邮件
            System.out.println(email);
            System.out.println(password);
            System.out.println(msg);
            MailUtils.sendMail(email,msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
