package com.itheima.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.cargo.ContractProductService;
import com.itheima.domain.User;
import com.itheima.domain.cargo.ContractProductVo;
import com.itheima.utils.DownloadUtil;
import com.itheima.web.BaseController;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cargo/contract")
public class OutProductController extends BaseController {
@Reference
private ContractProductService contractProductService;
    //到出货表打印
    @RequestMapping("/print")
    public String print(){
        return "cargo/print/contract-print";
    }
@RequestMapping("/printExcel")
    public void printExcel(String inputDate) throws IOException {
    //查询数据库
    List<ContractProductVo> list = contractProductService.findByDate(companyId,inputDate);
    if (list != null) {
        //读取模板内容
        //读取模板样式并写入内容
        String path = session.getServletContext().getRealPath("/") + "/make/xlsprint/tOUTPRODUCT.xlsx";
        Workbook wb = new XSSFWorkbook(path);
        //获取工作页
        Sheet sheet = wb.getSheetAt(0);
        //设置基本参数
        int rowIndex = 0;
        Row row = null;
        Cell cell = null;
        //获取第一行
        row = sheet.getRow(rowIndex++);
        //获取第二个单元格(索引为1)
        cell = row.getCell(1);
        String value = inputDate.replaceAll("-0", "-").replaceAll("-", "年");
        //设置第一行单元格
        cell.setCellValue(value + "月份出货表");
        //略过第二行
        rowIndex++;
        //获取第二行样式
        CellStyle[] stylesArr = new CellStyle[9];
        for (int i = 1; i < stylesArr.length; i++) {
            row = sheet.getRow(rowIndex);
            //获取单元格
            cell = row.getCell(i);
            //获取样式
            stylesArr[i] = cell.getCellStyle();
        }
        //循环list添加数据
        for (ContractProductVo contractProductVo : list) {
            //创建一行
            row = sheet.createRow(rowIndex++);
            //创建单元格
            cell = row.createCell(1);
            //设置单元格属性
            cell.setCellValue(contractProductVo.getCustomName());
            //设置单元格样式
            cell.setCellStyle(stylesArr[1]);

            //创建单元格
            cell = row.createCell(2);
            //设置单元格属性
            cell.setCellValue(contractProductVo.getContractNo());
            //设置单元格样式
            cell.setCellStyle(stylesArr[2]);

            //创建单元格
            cell = row.createCell(3);
            //设置单元格属性
            cell.setCellValue(contractProductVo.getProductNo());
            //设置单元格样式
            cell.setCellStyle(stylesArr[3]);

            //创建单元格
            cell = row.createCell(4);
            //设置单元格属性
            cell.setCellValue(contractProductVo.getCnumber());
            //设置单元格样式
            cell.setCellStyle(stylesArr[4]);

            //创建单元格
            cell = row.createCell(5);
            //设置单元格属性
            cell.setCellValue(contractProductVo.getFactoryName());
            //设置单元格样式
            cell.setCellStyle(stylesArr[5]);
            //创建单元格
            cell = row.createCell(6);
            //设置单元格属性
            cell.setCellValue(contractProductVo.getDeliveryPeriod());
            //设置单元格样式
            cell.setCellStyle(stylesArr[6]);

            //创建单元格
            cell = row.createCell(7);
            //设置单元格属性
            cell.setCellValue(contractProductVo.getShipTime());
            //设置单元格样式
            cell.setCellStyle(stylesArr[7]);

            //创建单元格
            cell = row.createCell(8);
            //设置单元格属性
            cell.setCellValue(contractProductVo.getTradeTerms());
            //设置单元格样式
            cell.setCellStyle(stylesArr[8]);
        }
        //下载excil
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        wb.write(byteArrayOutputStream);
        new DownloadUtil().download(byteArrayOutputStream, response, "出货表.xlsx");
        response.getWriter().print("当月没有数据...");
    }
}
}
