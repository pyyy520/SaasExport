package com.itheima.web.controller.log;

import com.github.pagehelper.PageInfo;
import com.itheima.web.BaseController;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("system/log/")
public class LogController extends BaseController {
    //查询所有日志
    @Autowired
    private LogService logService;
    @RequestMapping(value = "/list",name = "查询所有日志")
    public String findAllLog(@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "10")int pageSize){
    PageInfo page = logService.findAllLog(companyId,pageNum,pageSize);
    request.setAttribute("page",page);
    return "system/log/log-list";
}
}
