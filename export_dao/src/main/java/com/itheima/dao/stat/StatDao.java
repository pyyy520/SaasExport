package com.itheima.dao.stat;

import java.util.List;
import java.util.Map;

public interface StatDao {
    List findFactoryData(String companyId);

    List findSellData(String companyId);

    List findOnlineData(String companyId);

    List getProduct();

    List getIp(String companyId);
}
