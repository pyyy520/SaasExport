package com.itheima.cargo;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.cargo.Product;
import com.itheima.domain.cargo.productExample;

public interface ProductService {

    Product findById(String id);

    void save(Product product);

    void update(Product product);

    void delete(String id);

    PageInfo findAll(productExample example, int pageNum, int pageSize);


}
