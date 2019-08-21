package com.itheima.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.web.BaseController;
import com.itheima.domain.Module;
import com.itheima.service.ModuleService;
import com.itheima.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/system/module")
public class ModuleController extends BaseController {
    @Autowired
    private ModuleService moduleService;
    //查询所有模块
    @RequestMapping(value = "/list",name = "查询所有模块")
    public String findAll(@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "10")int pageSize){
        PageInfo page = moduleService.findAll(pageNum,pageSize);
        request.setAttribute("page",page);
        return "system/module/module-list";
    }
    //到添加页面
    @RequestMapping(value = "/toAdd",name = "到添加页面")
    public String toAdd(){
        List<Module> list = moduleService.findAllModule();
        //查询全部模块
        request.setAttribute("menus",list);
        return "/system/module/module-add";
    }
    //到修改页面
    @RequestMapping(value = "/toUpdate",name = "到修改页面")
    public String toUpdate(String id){
        //查询所有模块
        List<Module> list = moduleService.findAllModule();
        request.setAttribute("menus",list);
        //查询当前模块
        Module module = moduleService.findById(id);
        request.setAttribute("module",module);
        return "/system/module/module-update";
    }
    //增加/修改模块
    @RequestMapping(value = "/edit",name = "增加/修改模块")
    public String edit(Module module){
        if(UtilFuns.isEmpty(module.getId())){
            //增加模块
            module.setId(UUID.randomUUID().toString().replace("-",""));
            moduleService.save(module);
        }else {
            //修改模块
            moduleService.update(module);
        }
        //重定向到查询页面
        return "redirect:/system/module/list.do";
    }
    //初始化树状图
    @RequestMapping(value = "/initModuleData",name = "初始化树状图")
    @ResponseBody
    public ArrayList getZtreeNodes(String id){
        //查询所有菜单模块 以json格式返回
        List<Module> moduleList = moduleService.findAllModule();
        //从中间表查询 当前角色所有的权限
        List<Module> roleModule = moduleService.findRoleModule(id);
        ArrayList list = new ArrayList();
        for (Module module : moduleList) {
            HashMap map = new HashMap();
            map.put("id",module.getId());
            map.put("pId",module.getParentId());
            map.put("name",module.getName());
            if(roleModule.contains(module)){
                map.put("checked",true);
            }
            list.add(map);
        }
        return list;
    }
}
