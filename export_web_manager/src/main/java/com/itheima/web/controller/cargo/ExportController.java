package com.itheima.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.ContractService;
import com.itheima.cargo.ExportProductService;
import com.itheima.cargo.ExportService;
import com.itheima.domain.cargo.*;
import com.itheima.domain.vo.ExportProductVo;
import com.itheima.domain.vo.ExportResult;
import com.itheima.domain.vo.ExportVo;
import com.itheima.utils.UtilFuns;
import com.itheima.web.BaseController;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.beans.beancontext.BeanContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("cargo/export")
public class ExportController extends BaseController {
    @Reference
    private ContractService contractService;
    @Reference
    private ExportService exportService;
    @Reference
    private ExportProductService exportProductService;
    @RequestMapping(value = "/contractList",name = "合同管理")
    public String contractList(@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "8")int pageSize){
        //查询状态为1的合同
        ContractExample example = new ContractExample();
        ContractExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        criteria.andStateEqualTo(1);
        example.setOrderByClause("create_time desc");
        PageInfo page = contractService.findAll(example, pageNum, pageSize);
        request.setAttribute("page",page);
        return "cargo/export/export-contractList";
    }
    @RequestMapping(value = "/list",name = "查询报运单")
    public String exportList(@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "8")int pageSize){
        //查询出口报运
        ExportExample exportExample = new ExportExample();
        ExportExample.Criteria criteria = exportExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        exportExample.setOrderByClause("create_time desc");
        PageInfo page = exportService.findAll(exportExample, pageNum, pageSize);
        request.setAttribute("page",page);
        return "cargo/export/export-list";
    }

    @RequestMapping(value = "toExport",name = "到报运页面")
    public String toExport(String id){
        request.setAttribute("id",id);
        return "cargo/export/export-toExport";
    }
    @RequestMapping("/edit")
    public String edit(Export export){
        if(UtilFuns.isEmpty(export.getId())){
            //添加
            export.setCompanyId(companyId);
            export.setCompanyName(companyName);
            exportService.save(export);
        }else {
            exportService.update(export);
            //修改
        }
        //到查询页面
        return "redirect:/cargo/export/list.do";
    }
    @RequestMapping("/delete")
    public String delete(String id){
        exportService.delete(id);
        //到查询页面
        return "redirect:/cargo/export/list.do";
    }
    @RequestMapping(value = "/toView",name = "报运单查看")
    public String toView(String id){
        //根据报运单的id查询报运单
        Export export = exportService.findById(id);
        // 放入request中带页面
        request.setAttribute("export",export);
        return "cargo/export/export-view";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        //获取报运单
        Export export = exportService.findById(id);
        //获取报运单货物
        ExportProductExample exportProductExample = new ExportProductExample();
        ExportProductExample.Criteria criteria = exportProductExample.createCriteria();
        criteria.andExportIdEqualTo(id);
        criteria.andCompanyIdEqualTo(companyId);
        List<ExportProduct> exportProducts = exportProductService.findAll(exportProductExample);
        System.out.println(exportProducts);
        request.setAttribute("eps",exportProducts);
        request.setAttribute("export",export);
        //到修改页面
        return "cargo/export/export-update";
    }
    @RequestMapping("/exportE")
    public String exportE(String id){
        //查询当前报运单
        Export export = exportService.findById(id);
        //导入vo类
        ExportVo exportVo = new ExportVo();
        BeanUtils.copyProperties(export,exportVo);
        //设置vo对象报运单id
        exportVo.setExportId(export.getId());
        //查询报运单下货物
        ExportProductExample exportProductExample = new ExportProductExample();
        ExportProductExample.Criteria criteria = exportProductExample.createCriteria();
        //根据报运单id查询
        criteria.andExportIdEqualTo(id);
        //报运单下的货物
        List<ExportProduct> exportProducts = exportProductService.findAll(exportProductExample);
        //vo类下的货物
        ArrayList<ExportProductVo> exportProductsVo = new ArrayList<>();
        for (ExportProduct exportProduct : exportProducts) {
            ExportProductVo exportProductVo = new ExportProductVo();
            BeanUtils.copyProperties(exportProduct,exportProductsVo);
            //设置vo对象报运单货物id
            exportProductVo.setExportProductId(exportProduct.getId());
            exportProductsVo.add(exportProductVo);
        }
        exportVo.setProducts(exportProductsVo);
        //调用报关服务
        WebClient.create("http://localhost:7070/ws/export/user/").post(exportVo);
        //查询报运接口
        ExportResult exportResult = WebClient.create("http://localhost:7070/ws/export/user/" + id).get(ExportResult.class);
        exportService.updateE(exportResult);
        //到查询页面
        return "redirect:/cargo/export/list.do";
    }
}
