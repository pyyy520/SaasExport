package com.itheima.web.controller.dept;

import com.github.pagehelper.PageInfo;
import com.itheima.web.BaseController;
import com.itheima.domain.Dept;
import com.itheima.service.DeptService;
import com.itheima.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {
    @Autowired
    private DeptService deptService;
    //查询部门信息
    @RequestMapping(value = "/list",name = "查询所有部门")
    public String findList(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "5") int pageSize){
        PageInfo page = deptService.findList(companyId, pageNum, pageSize);
        request.setAttribute("page",page);
        return "system/dept/dept-list";
    }
    //到添加部门页面 根据id查询上级部门
    @RequestMapping(value = "/toAdd",name = "到添加页面")
    public String toAdd(){
        List<Dept> list = deptService.findAllDept(companyId);
        request.setAttribute("deptList",list);
        return "system/dept/dept-add";
    }
//    添加部门
    @RequestMapping(value = "/edit",name = "添加/修改部门")
    public String edit(Dept dept){ ;
        if(UtilFuns.isEmpty(dept.getId())){
            //如果为空 添加部门
            dept.setId(UUID.randomUUID().toString().replace("-",""));
            deptService.saveDept(dept);
        }else {
            //修改
            deptService.updDept(dept);
        }
        //重定向到查询页面
        return "redirect:/system/dept/list.do";
    }
    @RequestMapping(value = "/toUpdate",name = "到修改页面")
    public String toUpdate(String id){
        //根据id查询部门信息
        Dept dept = deptService.findById(id);
        //查询公司所有部门
        List<Dept> list = deptService.findAllDept(companyId);
        request.setAttribute("dept",dept);
        request.setAttribute("deptList",list);
        return "system/dept/dept-update";
    }
    @RequestMapping(value = "/delete",name = "删除部门")
    public String delete(String id){
        deptService.delDept(id);
        //重定向到查询页面
        return "redirect:/system/dept/list.do";
    }
}
