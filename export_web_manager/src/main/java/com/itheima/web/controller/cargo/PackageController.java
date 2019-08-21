package com.itheima.web.controller.cargo;


import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.ExportService;
import com.itheima.cargo.PackageSerive;
import com.itheima.domain.Dept;
import com.itheima.domain.cargo.PackageExample;
import com.itheima.domain.cargo.Package;
import com.itheima.service.DeptService;
import com.itheima.utils.UtilFuns;
import com.itheima.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

;

/**
 * @创建人 ayz
 * @创建时间 2019/8/11 9:36
 * @描述
 */
@Controller
@RequestMapping("cargo/packing")
public class PackageController extends BaseController {
    @Autowired
    private DeptService deptService;
    @Reference
    private PackageSerive packageSerive;
    @Reference
    private ExportService exportService;


    @RequestMapping(value = "/state",name = "查询状态")
    @ResponseBody
    public int state(String id){
        //根据id 查询所有报销单 循环遍历 判断
        System.out.println(id);
        return exportService.findByIds(id);
    }

    @RequestMapping(value = "/toAdd",name = "点击装箱来到这")
    public String toAdd(String id){
        //需要创建部门 根据公司id查询 所有部门   遍历部门
        List<Dept> allDept = deptService.findAllDept(companyId);
        session.setAttribute("allDept",allDept);
        session.setAttribute("id",id);
        return "/cargo/package/package-add";
    }

    @RequestMapping(value = "/edit",name = "点击确认装箱保存装箱单")
    public String save(Package pack, String ids){
       pack.setCompanyId(companyId);
       pack.setCompanyName(companyName);
       if (UtilFuns.isEmpty(pack.getPackingListId())){
           packageSerive.save(pack,ids);
           return "redirect:/cargo/export/list.do";
       }else {
           packageSerive.update(pack);
           return "redirect:/cargo/packing/list.do";
       }
    }

    @RequestMapping(value = "/list",name = "展示所有装箱单")
    public String list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){
        //查询所有装箱单带到页面
        PackageExample packageExample = new PackageExample();
        PackageExample.Criteria criteria = packageExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        PageInfo page=packageSerive.findAll(packageExample,pageNum,pageSize);
        request.setAttribute("page",page);
        return "/cargo/package/package-list";
    }

    @RequestMapping(value = "/delete",name = "删除选中的装箱单")
    public String delete(String id){
        packageSerive.delete(id);
        return "redirect:/cargo/packing/list.do";
    }

    @RequestMapping(value = "/toUpdate",name = "跳转到修改页面")
    public String toUpdate(String id){
        //需要创建部门 根据公司id查询 所有部门   遍历部门
        List<Dept> allDept = deptService.findAllDept(companyId);
        request.setAttribute("allDept",allDept);
        //根据id 查询装箱单
        Package pack= packageSerive.findById(id);
        request.setAttribute("pack",pack);
        return "/cargo/package/package-update";
    }

    @RequestMapping(value = "/toView",name = "跳转查看页面")
    public String toView(String id){
        //根据id查询所有装箱单
        Package pack = packageSerive.findById(id);
        request.setAttribute("pack",pack);
        return "/cargo/package/package-view";
    }

    //提交 判断
    @RequestMapping(value = "/pdSubmit",name = "合同状态的提交判断")
    @ResponseBody
    public int pdSubmit(String id){
        //根据id查询装箱单
       return packageSerive.panDSubmits(id);

    }
    //提交 submit
    @RequestMapping(value = "/submit",name = "合同状态的提交")
    public String submit(String id){
        //根据id查询装箱单
        packageSerive.submits(id);
        return "redirect:/cargo/packing/list.do";
    }

    //取消判断
    @RequestMapping(value = "/pdCancel",name = "合同状态的取消判断")
    @ResponseBody
    public int pdCancel(String id){
        return packageSerive.panDCancels(id);
    }
    //取消
    @RequestMapping(value = "/cancel",name = "合同状态的取消")
    public String cancel(String id){
        // 取消  1改成0
        packageSerive.cancels(id);

        //到查询
        return "redirect:/cargo/packing/list.do";
    }

}
