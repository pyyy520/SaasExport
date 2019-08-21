package com.itheima.cargo.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.cargo.FinanceService;
import com.itheima.dao.cargo.FinanceDao;
import com.itheima.domain.cargo.Finance;
import com.itheima.domain.cargo.FinanceExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @创建人 gdx
 * @创建时间 2019/8/11 16:41
 * @描述
 */
@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private FinanceDao financeDao;

    //查询
    @Override
    public PageInfo findAll(FinanceExample example, int page, int size) {

        PageHelper.startPage(page,size);
        List<Finance> list = financeDao.selectByExample(example);
        return new PageInfo(list);
    }

    //新增
    @Override
    public void save(Finance finance) {
        financeDao.insertSelective(finance);
    }

    @Override
    public void update(Finance finance) {
        financeDao.updateByPrimaryKeySelective(finance);
    }

    @Override
    public Finance findById(String id) {
        return financeDao.selectByPrimaryKey(id);
    }

    @Override
    public void delete(String id) {
        financeDao.deleteByPrimaryKey(id);

    }
}
