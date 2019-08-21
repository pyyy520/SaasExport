package com.itheima.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.PackageSerive;
import com.itheima.cargo.ShippingService;
import com.itheima.domain.cargo.Package;
import com.itheima.domain.cargo.PackageExample;
import com.itheima.domain.cargo.ShippingOrder;
import com.itheima.domain.cargo.ShippingOrderExample;
import com.itheima.utils.UtilFuns;
import com.itheima.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @创建人 cwj
 * @创建时间 2019/8/11  10:25
 * @描述
 */
@Controller
@RequestMapping("/cargo/shipping")
public class ShippingController extends BaseController {

    @Reference
    private ShippingService shippingService;
    @Reference
    private PackageSerive packageService;

    @RequestMapping(value = "/list", name = "分页查询所有委托单")
    public String findAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4") int pageSize) {
        ShippingOrderExample shippingOrderExample = new ShippingOrderExample();
        ShippingOrderExample.Criteria criteria = shippingOrderExample.createCriteria();
        PageInfo page = shippingService.findAll(shippingOrderExample, pageNum, pageSize);
        request.setAttribute("page", page);
        return "cargo/shipping/shipping-list";
    }

    @RequestMapping(value = "/toAdd", name = "跳转添加页面")
    public String toAdd(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4") int pageSize) {
        //查询装箱单状态为1的展示
        PackageExample packageExample = new PackageExample();
        PackageExample.Criteria criteria = packageExample.createCriteria();
        criteria.andStateEqualTo(1l);
        PageInfo page = packageService.findAll(packageExample, pageNum, pageSize);
        request.setAttribute("page", page);
        return "cargo/shipping/shipping-add";
    }

    @RequestMapping(value = "/edit", name = "保存或修改委托单")
    public String edit(ShippingOrder shippingOrder, String packingListId) {
        shippingOrder.setCreateBy(user.getUserName());//创建人
        shippingOrder.setState(0);//状态
        shippingOrder.setCreateDept(user.getDeptName());//创建部门
        shippingOrder.setCreateTime(new Date());//创建日期
        shippingOrder.setCompanyId(companyId);
        shippingOrder.setCompanyName(companyName);
        if(UtilFuns.isEmpty(shippingOrder.getShippingOrderId())){
            if(UtilFuns.isEmpty(packingListId)){
                return "cargo/shipping/shipping-error";
            }
            //保存
            shippingOrder.setShippingOrderId(packingListId);//id
            shippingService.save(shippingOrder);
            Package packages = packageService.findById(packingListId);
            //更改状态为以委托
            packages.setState(2l);
            packageService.update(packages);
        }else {
            //修改
            shippingService.update(shippingOrder);
        }
        return "redirect:/cargo/shipping/list.do";
    }

    @RequestMapping(value = "/toUpdate",name = "跳转更新页面")
    public String toUpdate(String id,@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4") int pageSize){
        ShippingOrder shippingOrder = shippingService.findById(id);
        request.setAttribute("shipping",shippingOrder);
        return "cargo/shipping/shipping-update";
    }

    @RequestMapping(value = "/delete", name = "删除委托单")
    public String delete(String id) {
        shippingService.delete(id);
        Package packages = packageService.findById(id);
        //更改状态变为已装箱
        packages.setState(1l);
        packageService.update(packages);
        return "redirect:/cargo/shipping/list.do";
    }

    @RequestMapping(value = "/submit", name = "提交委托单")
    public String submit(String id) {
        ShippingOrder shippingOrder = shippingService.findById(id);
        if(shippingOrder.getState() == 0){
            shippingOrder.setState(1);
            shippingService.update(shippingOrder);
        }
        return "redirect:/cargo/shipping/list.do";
    }

    @RequestMapping(value = "/cancel", name = "取消委托单")
    public String cancel(String id) {
        ShippingOrder shippingOrder = shippingService.findById(id);
        if (shippingOrder.getState() == 1) {
            shippingOrder.setState(0);
            shippingService.update(shippingOrder);
        }
        return "redirect:/cargo/shipping/list.do";
    }

    @RequestMapping(value = "/error",name = "跳转提示页面")
    public String error(){
        return "redirect:/cargo/shipping/list.do";
    }
}
