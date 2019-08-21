package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.LogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;
    @Override
    public PageInfo findAllLog(String companyId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysLog> list = logDao.findAllLog(companyId);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public void saveLog(SysLog sysLog) {
        sysLog.setId(UUID.randomUUID().toString().replace("-",""));
        logDao.saveLog(sysLog);
    }
}
