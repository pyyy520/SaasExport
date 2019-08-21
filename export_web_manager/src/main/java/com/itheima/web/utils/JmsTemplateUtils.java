package com.itheima.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class JmsTemplateUtils {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination queue;
    public void send(String email,String password,String msg){
        jmsTemplate.send(queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                //给中间件传递数据
                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setStringProperty("email",email);
                mapMessage.setStringProperty("msg",msg);
                mapMessage.setStringProperty("password",password);
                return mapMessage;
            }
        });
    }
}
