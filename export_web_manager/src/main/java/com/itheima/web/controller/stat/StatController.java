package com.itheima.web.controller.stat;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.stat.StatService;
import com.itheima.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stat")
public class StatController extends BaseController {
    @Reference
    private StatService statService;
    @RequestMapping("/toCharts")
    public String toCharts(String chartsType){
        return "stat/stat-"+chartsType;
    }
    @RequestMapping("/getFactoryData")
    @ResponseBody
    public List factoryCharts(){
        List factoryData = statService.findFactoryData(companyId);
        return factoryData;
    }


    @RequestMapping("/getSellData")
    @ResponseBody
    public List findSellData(){
        List sellData = statService.findSellData(companyId);
        return sellData;
    }

    @RequestMapping("/getOnlineData")
    @ResponseBody
    public List findOnlineData(){
        List onlineData = statService.findOnlineData(companyId);
        return onlineData;
    }

    @RequestMapping(value = "/getProduct")
    @ResponseBody
    public List getProduct (){
        //调用service
        List list = statService.getProduct();
        return list;
    }



    @RequestMapping(value = "/getIp")
    @ResponseBody
    public List getIp (){
        //调用service
        List list = statService.getIp(companyId);
        return list;
    }
}
