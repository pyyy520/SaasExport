package com.itheima.web.controller.stat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stat")
public class StatBaiDuController {
    @RequestMapping("/baidu")
    public String baidu(){
        return "stat/stat-baidu";
    }
}
