package com.itheima.stat.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.stat.StatDao;
import com.itheima.stat.StatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class StatServiceImpl implements StatService {
@Autowired
private StatDao statDao;
    @Override
    public List<Map> findFactoryData(String companyId) {
        return statDao.findFactoryData(companyId);
    }

    @Override
    public List<Map> findSellData(String companyId) {
        return statDao.findSellData(companyId);
    }

    @Override
    public List findOnlineData(String companyId) {
        return statDao.findOnlineData(companyId);
    }

    @Override
    public List getProduct() {
        return statDao.getProduct();
    }

    @Override
    public List getIp(String companyId) {
        return statDao.getIp(companyId);
    }
}
