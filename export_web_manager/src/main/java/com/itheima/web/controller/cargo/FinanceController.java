package com.itheima.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.FinanceService;
import com.itheima.cargo.InvoiceService;
import com.itheima.domain.cargo.Finance;
import com.itheima.domain.cargo.FinanceExample;
import com.itheima.domain.cargo.Invoice;
import com.itheima.domain.cargo.InvoiceExample;
import com.itheima.utils.UtilFuns;
import com.itheima.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @创建人 gdx
 * @创建时间 2019/8/11 10:55
 * @描述
 */
@Controller
@RequestMapping(value ="/cargo/finance")
public class FinanceController extends BaseController {

    @Reference
    private FinanceService financeService;

    @Reference
    private InvoiceService invoiceService;

    @RequestMapping(value = "/list",name = "查询财务管理模块列表")
    public String list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){
        FinanceExample financeExample = new FinanceExample();
        FinanceExample.Criteria criteria = financeExample.createCriteria();
        PageInfo page=financeService.findAll(financeExample,pageNum,pageSize);
        request.setAttribute("page",page);
        return "/cargo/finance/finance-list";
    }

    @RequestMapping(value = "/toAdd",name = "跳转到添加页面")
    public String toAdd(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4")int pageSize){
        //查询状态为1的发票
        InvoiceExample invoiceExample = new InvoiceExample();
        InvoiceExample.Criteria criteria = invoiceExample.createCriteria();
        criteria.andStateEqualTo(1);
        List<Invoice> invoices = invoiceService.findAlll(invoiceExample);
        request.setAttribute("page",invoices);
        return "/cargo/finance/finance-add";

    }


    //新增财务报运单
    @RequestMapping(value = "/edit",name = "新增和修改财务报运单")
    public String edit(Finance finance, String invoiceId){
        if (UtilFuns.isEmpty(finance.getFinanceId())){
            //新增
            finance.setState(0);//财务单状态
            finance.setFinanceId(invoiceId);
            financeService.save(finance);

        }else {
            //修改
            financeService.update(finance);
        }

        //到查询页面
        return "redirect:/cargo/finance/list.do";
    }

    @RequestMapping(value = "/toUpdate",name = "财务报运单修改页面")
    public String toUpdate(String id){
        Finance finance=financeService.findById(id);
        request.setAttribute("finance",finance);
        return "/cargo/finance/finance-update";
    }

    //删除
    @RequestMapping(value = "/delete",name = "删除")
    public String delete(String id){
        financeService.delete(id);

        return "redirect:/cargo/finance/list.do";
    }

    @RequestMapping(value = "/submit",name = "提交财务报运单")
    public String submit(String id){
        Finance finance=financeService.findById(id);
        if (finance.getState()==0){
            finance.setState(1);
            financeService.update(finance);
        }
        return "redirect:/cargo/finance/list.do";
    }

    @RequestMapping(value = "/cancel",name = "取消")
    public String cancel(String id){
        Finance finance=financeService.findById(id);
        if (finance.getState()==1){
            finance.setState(0);
            financeService.update(finance);
        }
        return "redirect:/cargo/finance/list.do";
    }
}
