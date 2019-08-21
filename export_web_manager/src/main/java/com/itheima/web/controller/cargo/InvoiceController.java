package com.itheima.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.*;
import com.itheima.domain.cargo.*;
import com.itheima.domain.cargo.Package;
import com.itheima.utils.UtilFuns;
import com.itheima.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cargo/invoice")
public class InvoiceController extends BaseController {
    @Reference
    private InvoiceService invoiceService;
    @Reference
    private ShippingService shippingService;
    @Reference
    private PackageSerive packageSerive;
    @Reference
    private ContractService contractService;
    @Reference
    private ExportService exportService;
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "5")int pageSize){
        PageInfo page = invoiceService.findAll(pageNum,pageSize);
        request.setAttribute("page",page);
        return "/cargo/invoice/invoice-list";
    }
    @RequestMapping("/submit")
    public String submit(String id){
        invoiceService.updateState(id);
        return "redirect:/cargo/invoice/list.do";
    }
    @RequestMapping("/delete")
    public String delete(String id){
        //将委托单状态改为1
        ShippingOrder shippingOrder = new ShippingOrder();
        shippingOrder.setState(1);
        shippingOrder.setShippingOrderId(id);
        shippingService.update(shippingOrder);
        invoiceService.delete(id);
        return "redirect:/cargo/invoice/list.do";
    }
    @RequestMapping("toUpdate")
    public String toUpdate(String id){
        //根据id查询发票单
        Invoice invoice = invoiceService.findById(id);
        //存放所有报运单名
        String contractName="";
        //查询所有报运单号
        List<Package> packages = packageSerive.findAllPack();
        for (Package aPackage : packages) {
            String exportIds = aPackage.getExportIds();
            //查询所有报运单名
            String[] exportid = exportIds.split(",");
            for (String s : exportid) {
                Export export = exportService.findById(s);
                String customerContract = export.getCustomerContract();
                contractName+=customerContract+",";
            }
        }
        invoice.setScNo(contractName);
        request.setAttribute("invoice",invoice);
        return "cargo/invoice/invoice-update";
    }
    @RequestMapping("/edit")
    public String edit(Invoice invoice,String id){
        if(UtilFuns.isEmpty(invoice.getInvoiceId())){
            String shippingId = id.replace(",", "");
//            设置委托单状态
            ShippingOrder shipping = shippingService.findById(shippingId);
            shipping.setState(2);
            shippingService.update(shipping);
            invoice.setInvoiceId(shippingId);
            invoice.setState(0);
            invoiceService.save(invoice);
        }else {
            invoiceService.update(invoice);
        }
        return "redirect:/cargo/invoice/list.do";
    }
    @RequestMapping("/toAdd")
    public String toAdd(){
        //查询所有状态为1委托单到展示页面
        ShippingOrderExample shippingOrderExample = new ShippingOrderExample();
        ShippingOrderExample.Criteria criteria = shippingOrderExample.createCriteria();
        criteria.andStateEqualTo(1);
        List<ShippingOrder>shippingOrder = shippingService.findAllShipp(shippingOrderExample);
        //存放所有报运单名
        String contractName=" ";
        //查询所有报运单号
        List<Package> packages = packageSerive.findAllPack();
        for (Package aPackage : packages) {
            String exportIds = aPackage.getExportIds();
            //查询所有报运单名
            String[] exportid = exportIds.split(",");
            for (String s : exportid) {
                Export export = exportService.findById(s);
                String customerContract = export.getCustomerContract();
                System.out.println(customerContract);
                contractName+=customerContract+",";
            }
        }
        Invoice invoice = new Invoice();
        invoice.setScNo(contractName);
        request.setAttribute("invoice",invoice);
        request.setAttribute("contractName",contractName);
        request.setAttribute("shippingOrder",shippingOrder);
        return "cargo/invoice/invoice-save";
    }
}
