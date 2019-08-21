package com.itheima.dao;

import com.itheima.domain.SysLog;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface LogDao {
    List<SysLog> findAllLog(String companyId);

    void saveLog(SysLog sysLog);
}
