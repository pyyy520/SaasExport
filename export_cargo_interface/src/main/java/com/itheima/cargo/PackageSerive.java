package com.itheima.cargo;


import com.github.pagehelper.PageInfo;
import com.itheima.domain.cargo.Package;
import com.itheima.domain.cargo.PackageExample;

import java.util.List;

/**
 * @创建人 ayz
 * @创建时间 2019/8/11 11:59
 * @描述
 */
public interface PackageSerive {
    void save(Package pack, String ids);

    PageInfo findAll(PackageExample packageExample, int pageNum, int pageSize);

    void delete(String id);

    Package findById(String id);

    void update(Package pack);

    void submits(String id);

    void cancels(String id);

    int panDCancels(String id);

    int panDSubmits(String id);

    List<Package> findAllPack();
}
