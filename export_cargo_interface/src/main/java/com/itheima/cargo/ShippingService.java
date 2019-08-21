package com.itheima.cargo;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.cargo.ShippingOrder;
import com.itheima.domain.cargo.ShippingOrderExample;

import java.util.List;

/**
 * @创建人 cwj
 * @创建时间 2019/8/11  10:40
 * @描述
 */
public interface ShippingService {

    /**
     * 保存
     */
    void save(ShippingOrder shipping);

    /**
     * 更新
     */
    void update(ShippingOrder shipping);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 根据id查询
     */
    ShippingOrder findById(String id);

    //分页查询所有
    public PageInfo findAll(ShippingOrderExample shippingExample, int pageNum, int pageSize);

    public List<ShippingOrder> findAllShipp(ShippingOrderExample shippingExample);
}
