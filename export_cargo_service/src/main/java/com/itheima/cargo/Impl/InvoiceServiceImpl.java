package com.itheima.cargo.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.InvoiceService;
import com.itheima.dao.cargo.InvoiceDao;
import com.itheima.domain.cargo.Invoice;
import com.itheima.domain.cargo.InvoiceExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
@Autowired
private InvoiceDao invoiceDao;
    @Override
    public void save(Invoice invoice) {
        invoiceDao.insertSelective(invoice);
    }

    @Override
    public void delete(String id) {
        invoiceDao.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Invoice invoice) {
        invoiceDao.updateByPrimaryKeySelective(invoice);
    }

    @Override
    public Invoice findById(String id) {
        Invoice invoice = invoiceDao.selectByPrimaryKey(id);
        return invoice;
    }

    @Override
    public PageInfo findAll(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        //查询所有发票
        List<Invoice> list = invoiceDao.selectByExample(null);
        //查询当前装箱单的合同名

        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void updateState(String id) {
        Invoice invoice = invoiceDao.selectByPrimaryKey(id);
            Invoice i = new Invoice();
            i.setInvoiceId(id);
            if(invoice.getState()==0){
                i.setState(1);
            }else if(invoice.getState()==1){
                i.setState(2);
            }else {
                i.setState(0);
            }
            invoiceDao.updateByPrimaryKeySelective(i);
        }

    @Override
    public List<Invoice> findAlll(InvoiceExample invoiceExample) {
        List<Invoice> invoices = invoiceDao.selectByExample(invoiceExample);
        return invoices;
    }
}
