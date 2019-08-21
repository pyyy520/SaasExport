package com.itheima.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.ContractProductService;
import com.itheima.cargo.ExtCproductService;
import com.itheima.cargo.FactoryService;
import com.itheima.domain.cargo.ExtCproduct;
import com.itheima.domain.cargo.ExtCproductExample;
import com.itheima.domain.cargo.Factory;
import com.itheima.domain.cargo.FactoryExample;
import com.itheima.utils.UtilFuns;
import com.itheima.web.BaseController;
import com.itheima.web.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/cargo/extCproduct")
public class ExtCproductController extends BaseController {
    @Reference
    private ExtCproductService extCproductService;
    @Reference
    private ContractProductService contractProductService;
    @Reference
    private FactoryService factoryService;
    @Autowired
    private FileUploadUtil fileUploadUtil;
    @RequestMapping("/list")
    public String findAll(String contractId, String contractProductId, @RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "8")int pageSize){
        //查询所有附件的厂家
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria = factoryExample.createCriteria();
        criteria.andCtypeEqualTo("附件");
        List<Factory> list = factoryService.findAll(factoryExample);
        //查询当前货物下的所有附件
        ExtCproductExample extCproductExample = new ExtCproductExample();
        ExtCproductExample.Criteria criteria1 = extCproductExample.createCriteria();
        criteria1.andContractProductIdEqualTo(contractProductId);
        PageInfo pageInfo = extCproductService.findAll(extCproductExample, pageNum, pageSize);
        request.setAttribute("factoryList",list);
        request.setAttribute("page",pageInfo);
        request.setAttribute("contractId",contractId);
        request.setAttribute("contractProductId",contractProductId);
        return "/cargo/extc/extc-list";
    }
    @RequestMapping("/edit")
    public String edit(ExtCproduct extCproduct, MultipartFile productPhoto) throws Exception {
        extCproduct.setCompanyId(companyId);
        extCproduct.setCompanyName(companyName);
        if(UtilFuns.isEmpty(extCproduct.getId())){
            //新增附件
            //货物图片上传七牛云
            if(productPhoto!=null){
                String img = fileUploadUtil.upload(productPhoto);
                //保存数据库
                extCproduct.setProductImage("http://"+img);
            }
            extCproductService.save(extCproduct);
        }else {
            //修改附件
            extCproductService.update(extCproduct);
        }
        //到查询
        return "redirect:/cargo/extCproduct/list.do?contractId="+extCproduct.getContractId()+"&contractProductId="+extCproduct.getContractProductId();
    }
    @RequestMapping("/toUpdate")
    public String toUpdate(String id,String contractId,String contractProductId){
        //查询当前附件回显
        ExtCproduct extCproduct = extCproductService.findById(id);
        //查询附件生产厂家回显
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria = factoryExample.createCriteria();
        criteria.andCtypeEqualTo("附件");
        List<Factory> factories = factoryService.findAll(factoryExample);
        request.setAttribute("factoryList",factories);
        request.setAttribute("extCproduct",extCproduct);
        request.setAttribute("contractId",extCproduct.getContractId());
        request.setAttribute("contractProductId",contractProductId);
        return "cargo/extc/extc-update";
    }
    @RequestMapping("/delete")
    public String delete(String id,String contractId,String contractProductId){
        extCproductService.delete(id);
        return "redirect:/cargo/extCproduct/list.do?contractId="+contractId+"&contractProductId="+contractProductId;
    }
}
