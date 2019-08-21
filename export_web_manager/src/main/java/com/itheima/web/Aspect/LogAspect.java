package com.itheima.web.Aspect;

import com.itheima.domain.SysLog;
import com.itheima.domain.User;
import com.itheima.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAspect {
    @Autowired
    private LogService logService;
    @Autowired
    HttpSession session;
    @Autowired
    HttpServletRequest request;
    @Around("execution(* com.itheima.web.controller.*.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        Method method = ms.getMethod();
        String methodName = method.getName();
        if(method.isAnnotationPresent(RequestMapping.class)){
            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
            String action = annotation.name();
            SysLog sysLog = new SysLog();
            //ip
            sysLog.setIp(request.getLocalAddr());
            //时间
            sysLog.setTime(new Date());
            User user = (User) session.getAttribute("loginUser");
            if(user!=null){
                sysLog.setCompanyId(user.getCompanyId());
                sysLog.setCompanyName(user.getUserName());
                sysLog.setUserName(user.getUserName());
            }
            sysLog.setAction(action);
            sysLog.setMethod(methodName);
            logService.saveLog(sysLog);
        }
        Object proceed = pjp.proceed();
        return proceed;
    }
}
