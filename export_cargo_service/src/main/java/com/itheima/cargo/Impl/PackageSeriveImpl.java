package com.itheima.cargo.Impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.PackageSerive;
import com.itheima.dao.cargo.ExportDao;
import com.itheima.dao.cargo.PackageDao;
import com.itheima.domain.cargo.Package;
import com.itheima.domain.cargo.Export;
import com.itheima.domain.cargo.PackageExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * @创建人 ayz
 * @创建时间 2019/8/11 11:59
 * @描述
 */
@Service
public class PackageSeriveImpl implements PackageSerive {
    @Autowired
    private PackageDao packageDao;
    @Autowired
    private ExportDao exportDao;


    public void save(Package pack, String ids) {
        //设置报运单的状态为3  已装箱
            //根据ids 获取每个装箱单  修改状态
        String[] idss = ids.split(",");
        //打断字段
        StringBuffer exportIds=new StringBuffer();
        //打断字段
        StringBuffer exportNos=new StringBuffer();
        for (String id :idss){
            //根据报运单id过去报运单
            Export export = exportDao.selectByPrimaryKey(id);
            //设置报运单状态
            export.setState(3);
            //获取报运单id
            exportIds.append(export.getId()).append(" ");
            //获取报运单合同号
            exportNos.append(export.getCustomerContract()).append(" ");
            //修改报运单
            exportDao.updateByPrimaryKeySelective(export);
        }
        //1.设置装箱单的状态为0 2.设置装箱单id  3.设置报运单id和合同号  4.保存装箱单
        pack.setState(0l);
        pack.setPackingListId(UUID.randomUUID().toString().replace("-",""));
        pack.setExportIds(exportIds.toString());
        pack.setExportNos(exportNos.toString());
        //保存装箱单
        packageDao.insert(pack);
    }

    public PageInfo findAll(PackageExample packageExample, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Package> packages = packageDao.selectByExample(packageExample);
        return new PageInfo(packages);
    }

    public void delete(String id) {
        packageDao.deleteByPrimaryKey(id);
    }

    public Package findById(String id) {
        return packageDao.selectByPrimaryKey(id);
    }

    public void update(Package pack) {
        packageDao.updateByPrimaryKeySelective(pack);
    }

    public void submits(String id) {
        Package aPackage = packageDao.selectByPrimaryKey(id);
        aPackage.setState(1l);
        packageDao.updateByPrimaryKeySelective(aPackage);
    }

    public void cancels(String id) {
        //查询装箱单
        Package aPackage = packageDao.selectByPrimaryKey(id);
        aPackage.setState(0l);
        packageDao.updateByPrimaryKeySelective(aPackage);

    }
    //判断取消
    public int panDCancels(String id) {
        //判断  根据id查询装箱单
        Package aPackage = packageDao.selectByPrimaryKey(id);
        if (aPackage.getState()==1){
            return 1;
        }else {
            return 0;
        }
    }
    //判断提交
    public int panDSubmits(String id) {
        Package aPackage = packageDao.selectByPrimaryKey(id);
        if (aPackage.getState()==0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public List<Package> findAllPack() {
        List<Package> packages = packageDao.selectByExample(null);
        return packages;
    }
}
