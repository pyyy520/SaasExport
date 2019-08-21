package com.itheima.cargo.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.ExportProductService;
import com.itheima.cargo.ExportService;
import com.itheima.dao.cargo.ExportProductDao;
import com.itheima.domain.cargo.Export;
import com.itheima.domain.cargo.ExportExample;
import com.itheima.domain.cargo.ExportProduct;
import com.itheima.domain.cargo.ExportProductExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class ExportProductImpl implements ExportProductService {
@Autowired
private ExportProductDao exportProductDao;

    @Override
    public ExportProduct findById(String id) {
        return null;
    }

    @Override
    public void save(ExportProduct exportProduct) {

    }

    @Override
    public void update(ExportProduct exportProduct) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<ExportProduct> findAll(ExportProductExample exportExample) {
        List<ExportProduct> exportProducts = exportProductDao.selectByExample(exportExample);
        return exportProducts;
    }
}
