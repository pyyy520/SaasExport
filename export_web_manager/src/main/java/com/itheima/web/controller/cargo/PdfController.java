package com.itheima.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.cargo.ExportProductService;
import com.itheima.cargo.ExportService;
import com.itheima.domain.cargo.Export;
import com.itheima.domain.cargo.ExportProduct;
import com.itheima.domain.cargo.ExportProductExample;
import com.itheima.utils.BeanMapUtils;
import com.itheima.web.BaseController;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cargo/export")
public class PdfController extends BaseController {
    @Reference
    private ExportService exportService;
    @Reference
    private ExportProductService exportProductService;
    @RequestMapping("/exportPdf")
    public void exportPdf(String id) throws Exception {
        //查询报运单
        Export export = exportService.findById(id);
        //查询报运单货物
        ExportProductExample exportProductExample = new ExportProductExample();
        ExportProductExample.Criteria criteria = exportProductExample.createCriteria();
        criteria.andExportIdEqualTo(id);
        List<ExportProduct> list = exportProductService.findAll(exportProductExample);
        //解析pdf模板
        String path = session.getServletContext().getRealPath("/") + "/jasper/export.jasper";
        FileInputStream is = new FileInputStream(path);
        //组装pdf报表map数据
        Map<String, Object> map = BeanMapUtils.beanToMap(export);
        //组装DataSource对象
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(list);
        //生成pdf打印对象
        JasperPrint jasperPrint = JasperFillManager.fillReport(is, map, ds);
        //下载pdf
        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
    }
}
