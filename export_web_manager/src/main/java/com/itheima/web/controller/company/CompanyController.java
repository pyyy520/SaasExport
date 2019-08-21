package com.itheima.web.controller.company;

import com.github.pagehelper.PageInfo;
import com.itheima.web.BaseController;
import com.itheima.domain.Company;
import com.itheima.service.CompanyService;
import com.itheima.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {
    @Autowired
    private CompanyService companyService;
//    查询所有公司
    @RequestMapping(value = "/list",name = "查询所有公司")
    public String findAll(HttpServletRequest request, @RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "5")int pageSize){
        PageInfo page = companyService.findAll(pageNum,pageSize);
        request.setAttribute("page",page);
        return "company/company-list";
    }
//    跳转添加企业页面
    @RequestMapping(value = "/toAdd",name = "到添加页面")
    public String toAdd(){
        return "company/company-add";
    }
    //企业添加/修改功能
    @RequestMapping(value = "/edit",name = "添加/修改企业")
     public String edit(Company company){
        //如果id为空 添加数据
        if(UtilFuns.isEmpty(company.getId())){
            //设置uuid
            company.setId(UUID.randomUUID().toString().replace("-",""));
            companyService.addCompany(company);
            return "redirect:/company/list.do";
        }
        //修改企业功能
        companyService.updCompany(company);
        return "redirect:/company/list.do";
    }
//    查询企业数据回显
    @RequestMapping(value = "/toUpdate",name = "查询企业数据回显")
    public String toUpdate(String id){
        Company companyById = companyService.findCompanyById(id);
        request.setAttribute("company",companyById);
        return "company/company-update";
    }
    //删除企业功能
    @RequestMapping(value = "/delete",name = "删除企业")
    public String delCompany(String id){
        companyService.delCompany(id);
        return "redirect:/company/list.do";
    }
}
