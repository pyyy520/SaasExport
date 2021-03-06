package com.itheima.cargo;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.cargo.Export;
import com.itheima.domain.cargo.ExportExample;
import com.itheima.domain.vo.ExportResult;


public interface ExportService {

    Export findById(String id);

    void save(Export export);

    void update(Export export);

    void delete(String id);

	PageInfo findAll(ExportExample example, int page, int size);

    void updateE(ExportResult exportResult);

    int findByIds(String id);
}
