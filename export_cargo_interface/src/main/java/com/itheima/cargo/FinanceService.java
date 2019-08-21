package com.itheima.cargo;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.cargo.Finance;
import com.itheima.domain.cargo.FinanceExample;

/**
 * @创建人 gdx
 * @创建时间 2019/8/11 16:39
 * @描述
 */
public interface FinanceService {
    PageInfo findAll(FinanceExample financeExample, int pageNum, int pageSize);

    void save(Finance finance);

    void update(Finance finance);

    Finance findById(String id);

    void delete(String id);
}
