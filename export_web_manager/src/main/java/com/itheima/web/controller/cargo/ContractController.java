package com.itheima.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.ContractService;
import com.itheima.domain.User;
import com.itheima.domain.cargo.Contract;
import com.itheima.domain.cargo.ContractExample;
import com.itheima.utils.UtilFuns;
import com.itheima.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cargo/contract")
public class ContractController extends BaseController {
    @Reference
    private ContractService contractService;
    //查询所有购销合同
    @RequestMapping("/list")
    public String contractList(@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "10")int pageSize){
        //条件查询
        ContractExample example = new ContractExample();
        ContractExample.Criteria criteria = example.createCriteria();
        //细粒度权限查询
        //查询登录用户等级
        User user = getLoginUser();
        if(user.getDegree()==4){
            //普通员工 根据创建人查询
            criteria.andCreateByEqualTo(user.getId());
        }else if(user.getDegree()==3){
            //管理本部门 根据部门查询
            criteria.andCreateDeptEqualTo(user.getDeptId());
        }else if(user.getDegree()==2){
            //管理所有下属部门和人员
            criteria.andCreateDeptLike(user.getDeptId()+"%");
        }
        //指定条件
        example.setOrderByClause("create_time desc");
        PageInfo page = contractService.findAll(example,pageNum,pageSize);
        request.setAttribute("page",page);
        return "cargo/contract/contract-list";
    }
    //到添加页面
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "cargo/contract/contract-add";
    }
//    添加/修改购销合同
    @RequestMapping("/edit")
    public String edit(Contract contract){
        if(UtilFuns.isEmpty(contract.getId())){
            //获取当前登录用户
            User user = getLoginUser();
            //设置合同创建人和部门
            contract.setCreateBy(user.getId());
            contract.setCreateDept(user.getDeptId());
            contract.setCompanyId(companyId);
            contract.setCompanyName(companyName);
            contractService.save(contract);
        }else {
            contractService.update(contract);
        }
        return "redirect:/cargo/contract/list.do";
    }
    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        Contract contract = contractService.findById(id);
        request.setAttribute("contract",contract);
        return "/cargo/contract/contract-update";
    }
    @RequestMapping("/delete")
    public String delete(String id){
        contractService.delete(id);
        return "redirect:/cargo/contract/list.do";
    }
    //提交购销合同
    @RequestMapping("/submit")
    public String submit(String id){
        //将合同状态设置为1
        Contract contract = new Contract();
        contract.setId(id);
        contract.setState(1);
        contractService.update(contract);
        return "redirect:/cargo/contract/list.do";
    }
    //提交购销合同
    @RequestMapping("/cancel")
    public String cancel(String id){
        //将合同状态设置为0
        Contract contract = new Contract();
        contract.setId(id);
        contract.setState(0);
        contractService.update(contract);
        return "redirect:/cargo/contract/list.do";
    }
    //查看合同
    @RequestMapping("/toView")
    public String toView(String id){
        Contract contract = contractService.findById(id);
        request.setAttribute("contract",contract);
        return "/cargo/contract/contract-view";
    }
}
