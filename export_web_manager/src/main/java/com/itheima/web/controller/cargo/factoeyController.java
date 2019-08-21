package com.itheima.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.FactoryService;
import com.itheima.domain.cargo.Factory;
import com.itheima.domain.cargo.FactoryExample;
import com.itheima.utils.UtilFuns;
import com.itheima.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/system/factory",name = "工厂信息")
public class factoeyController extends BaseController {
    @Reference
    private FactoryService factoryService;
    @RequestMapping(value = "/list",name = "跳转工厂信息页面")
    public String factoryList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4") int pageSize){
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria = factoryExample.createCriteria();
       PageInfo info = factoryService.findAll(factoryExample,pageNum,pageSize);
        request.setAttribute("page",info);
        return "system/factory/factory-list";
    }
    @RequestMapping(value = "/toAdd",name = "跳转添加工厂页面")
    public String toAdd(){
        return "system/factory/factory-add";
    }
    @RequestMapping(value = "/edit",name = "添加新建工厂")
    public String edit(Factory factory){
        if(UtilFuns.isEmpty(factory.getId())){
            factoryService.save(factory);
        }else {
            factoryService.update(factory);
        }

        return "redirect:/system/factory/list.do";

    }
    @RequestMapping(value = "/delete",name = "删除工厂")
    public String delete(String id){
        factoryService.delete(id);
        return "redirect:/system/factory/list.do";
    }
    @RequestMapping(value = "/toUpdate",name = "跳转修改页面")
    public String toUpdate(String id){
        Factory byId = factoryService.findById(id);
       // System.out.println(byId);
        request.setAttribute("factory",byId);
        return  "system/factory/factory-update";

    }
    @RequestMapping(value = "/show",name = "跳转查看页面")
    public String show(String id){
        Factory byId = factoryService.findById(id);
        request.setAttribute("factory",byId);
        return  "system/factory/factory-show";
    }
}
