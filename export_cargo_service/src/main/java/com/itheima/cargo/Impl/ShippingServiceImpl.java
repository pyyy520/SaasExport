package com.itheima.cargo.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.ShippingService;
import com.itheima.dao.cargo.ShippingOrderDao;
import com.itheima.domain.cargo.ShippingOrder;
import com.itheima.domain.cargo.ShippingOrderExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @创建人 cwj
 * @创建时间 2019/8/11  10:42
 * @描述
 */
@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingOrderDao shippingOrderDao;

    @Override
    public void save(ShippingOrder shipping) {
        shippingOrderDao.insert(shipping);
    }

    @Override
    public void update(ShippingOrder shipping) {
        shippingOrderDao.updateByPrimaryKeySelective(shipping);
    }

    @Override
    public void delete(String id) {
        shippingOrderDao.deleteByPrimaryKey(id);
    }

    @Override
    public ShippingOrder findById(String id) {
        return shippingOrderDao.selectByPrimaryKey(id);
    }

    //分页查询所有委托单信息
    @Override
    public PageInfo findAll(ShippingOrderExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<ShippingOrder> contracts = shippingOrderDao.selectByExample(example);
        return new PageInfo(contracts);
    }

    @Override
    public List<ShippingOrder> findAllShipp(ShippingOrderExample shippingExample) {
        List<ShippingOrder> shippingOrders = shippingOrderDao.selectByExample(shippingExample);
        return shippingOrders;
    }
}
