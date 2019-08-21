package com.itheima.dao.cargo;

import com.itheima.domain.cargo.Product;
import com.itheima.domain.cargo.productExample;
import java.util.List;

public interface ProductDao {
    int deleteByPrimaryKey(String id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(productExample example);

    Product selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}