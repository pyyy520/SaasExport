package com.itheima.cargo.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.ProductService;
import com.itheima.dao.cargo.ProductDao;
import com.itheima.domain.cargo.Product;
import com.itheima.domain.cargo.productExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findById(String id) {
        return productDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Product product) {
        product.setId(UUID.randomUUID().toString().replace("-",""));
        productDao.insert(product);
    }

    @Override
    public void update(Product product) {
        productDao.updateByPrimaryKey(product);
    }

    @Override
    public void delete(String id) {
        productDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(productExample example, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        List<Product> list = productDao.selectByExample(example);
        return new PageInfo(list);
    }
}
