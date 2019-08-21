package com.itheima.cargo;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.cargo.Invoice;
import com.itheima.domain.cargo.InvoiceExample;

import java.util.List;

public interface InvoiceService {
    void save(Invoice invoice);
    void delete(String id);
    void update(Invoice invoice);
    Invoice findById(String id);
    PageInfo findAll(int pageNum, int pageSize);

    void updateState(String id);

    List<Invoice> findAlll(InvoiceExample invoiceExample);
}
