package com.itheima.web.controller.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.FactoryService;
import com.itheima.cargo.ProductService;
import com.itheima.domain.cargo.Factory;
import com.itheima.domain.cargo.FactoryExample;
import com.itheima.domain.cargo.Product;
import com.itheima.domain.cargo.productExample;
import com.itheima.utils.UtilFuns;
import com.itheima.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/system/product",name = "商品模块")
public class productController extends BaseController {

    @Reference
    private ProductService productService;
    @Reference
    private FactoryService factoryService;
    @RequestMapping(value = "/list",name = "跳转商品列表页面")
    public String list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4") int pageSize){
        productExample productExample = new productExample();
        com.itheima.domain.cargo.productExample.Criteria criteria = productExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        PageInfo info = productService.findAll(productExample, pageNum, pageSize);
        request.setAttribute("page",info);
        return "/system/product/product-list";
    }
    @RequestMapping(value = "/toAdd",name = "跳转添加商品页面")
    public String toAdd(){
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria = factoryExample.createCriteria();

        List<Factory> list = factoryService.findAll(factoryExample);
        request.setAttribute("factoryList",list);
        return "system/product/product-add";
    }
    @RequestMapping(value = "/edit",name = "添加新建商品")
    public String edit(Product product){
        product.setCompanyName(companyName);
        product.setCompanyId(companyId);
        if(UtilFuns.isEmpty(product.getId())){
            product.setCreateTime(new Date());
            productService.save(product);
        }else {
            product.setUpdateTime(new Date());
            productService.update(product);

        }

        return "redirect:/system/product/list.do";

    }
    @RequestMapping(value = "/delete",name = "删除商品")
    public String delete(String id){
        productService.delete(id);
        return "redirect:/system/product/list.do";
    }
    @RequestMapping(value = "/toUpdate",name = "跳转修改页面")
    public String toUpdate(String id){
        Product byId = productService.findById(id);
        // System.out.println(byId);
        request.setAttribute("product",byId);
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria = factoryExample.createCriteria();

        List<Factory> list = factoryService.findAll(factoryExample);
        request.setAttribute("factoryList",list);
        return  "system/product/product-update";

    }

}
