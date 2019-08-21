package com.itheima.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.ContractProductService;
import com.itheima.cargo.FactoryService;
import com.itheima.domain.cargo.ContractProduct;
import com.itheima.domain.cargo.ContractProductExample;
import com.itheima.domain.cargo.Factory;
import com.itheima.domain.cargo.FactoryExample;
import com.itheima.utils.UtilFuns;
import com.itheima.web.BaseController;
import com.itheima.web.utils.FileUploadUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cargo/contractProduct")
public class ContractProductController extends BaseController {
    @Reference
    private ContractProductService contractProductService;
    @Reference
    private FactoryService factoryService;
    @Autowired
    private FileUploadUtil fileUploadUtil;
    @RequestMapping("/list")
    public String findPageAll(String contractId, @RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "10")int pageSize){
        //查询货物的所有厂家
        FactoryExample example = new FactoryExample();
        FactoryExample.Criteria criteria = example.createCriteria();
        criteria.andCtypeEqualTo("货物");
        List<Factory> factories = factoryService.findAll(example);
        //查询该合同下所有货物
        ContractProductExample productExample = new ContractProductExample();
        ContractProductExample.Criteria criteria1 = productExample.createCriteria();
        criteria1.andContractIdEqualTo(contractId);
        PageInfo pageInfo = contractProductService.findAll(productExample, pageNum, pageSize);
        request.setAttribute("page",pageInfo);
        request.setAttribute("factoryList",factories);
        request.setAttribute("contractId",contractId);
        return "cargo/product/product-list";
    }
    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        //查询货物生产厂家
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria = factoryExample.createCriteria();
        criteria.andCtypeEqualTo("货物");
        List<Factory> list = factoryService.findAll(factoryExample);
        //查询当前货物
        ContractProduct contractProduct = contractProductService.findById(id);
        request.setAttribute("contractProduct",contractProduct);
        request.setAttribute("factoryList",list);
        return "cargo/product/product-update";
    }
    @RequestMapping("/edit")
    public String edit(ContractProduct contractProduct, MultipartFile productPhoto) throws Exception {
        System.out.println(productPhoto);
        System.out.println(contractProduct);
        if(UtilFuns.isEmpty(contractProduct.getId())){
            contractProduct.setCompanyId(companyId);
            contractProduct.setCompanyName(companyName);
            //货物图片上传七牛云
            if(productPhoto!=null){
                String img = fileUploadUtil.upload(productPhoto);
                //保存数据库
                contractProduct.setProductImage("http://"+img);
                System.out.println(contractProduct);
            }
            //货物添加
            contractProductService.save(contractProduct);
        }else {
            //货物修改
            contractProductService.update(contractProduct);
        }
        //到货物查询
        return "redirect:/cargo/contractProduct/list.do?contractId="+contractProduct.getContractId();
    }
    @RequestMapping("/delete")
    public String delete(String id,String contractId){
        contractProductService.delete(id);
        //到货物查询
        return "redirect:/cargo/contractProduct/list.do?contractId="+contractId;
    }
    //到上传货物
    @RequestMapping("/toImport")
    public String toImport(String contractId){
        request.setAttribute("contractId",contractId);
        return "cargo/product/product-import";
    }
    @RequestMapping("/import")
    public String imports(String contractId,MultipartFile file) throws IOException {
        //通过流读取excil文件
        Workbook wb = new XSSFWorkbook(file.getInputStream());
        //获取工作页
        Sheet sheet = wb.getSheetAt(0);
        ArrayList<ContractProduct> list = new ArrayList<>();
        //获取所有行
        for (int i = 1; i < sheet.getLastRowNum()+1; i++) {
            Row row = sheet.getRow(i);
            Object[]objects = new Object[10];
            //获取行的单元格
            for (int j = 1; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                //将所有单元格放入数组中
                if(cell!=null){
                    objects[j] = getCellValue(cell);
                }
            }
            //保存数组单元格到对象中
            ContractProduct contractProduct = new ContractProduct(objects,companyId,companyName);
            contractProduct.setContractId(contractId);
            list.add(contractProduct);
            //添加到数据库
            contractProductService.saveAll(list);
        }
        return "redirect:/cargo/contractProduct/list.do?contractId="+contractId;
    }
    //解析每个单元格的数据
    public static Object getCellValue(Cell cell) {
        Object obj = null;
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case STRING: { //字符串单元
                obj = cell.getStringCellValue();
                break;
            }
            //excel默认将日志也理解为数字
            case NUMERIC:{ //数字单元格
                if(DateUtil.isCellDateFormatted(cell)) { //日期
                    obj = cell.getDateCellValue();
                }else {
                    obj = cell.getNumericCellValue();
                }
                break;
            }
            case BOOLEAN:{ //boolean
                obj = cell.getBooleanCellValue();
                break;
            }
            default:{
                break;
            }
        }

        return obj;
    }
}
