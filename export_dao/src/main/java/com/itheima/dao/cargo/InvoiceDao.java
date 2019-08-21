package com.itheima.dao.cargo;

import com.itheima.domain.cargo.Invoice;
import com.itheima.domain.cargo.InvoiceExample;

import java.util.List;

public interface InvoiceDao {
    int deleteByPrimaryKey(String invoiceId);

    int insert(Invoice record);

    int insertSelective(Invoice record);

    List<Invoice> selectByExample(InvoiceExample example);

    Invoice selectByPrimaryKey(String invoiceId);

    void updateByPrimaryKeySelective(Invoice record);

    int updateByPrimaryKey(Invoice record);
}