package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysLog;


public interface LogService {
    PageInfo findAllLog (String companyId, int pageNum, int pageSize);

    void saveLog(SysLog sysLog);
}
