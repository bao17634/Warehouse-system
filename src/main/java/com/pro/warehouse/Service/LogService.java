package com.pro.warehouse.Service;

import com.pro.warehouse.dao.LogOperationRepository;
import com.pro.warehouse.dao.LogSystemRepository;
import com.pro.warehouse.pojo.LogOperation;
import com.pro.warehouse.pojo.LogSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogService {
    @Autowired
    private LogSystemRepository logSystemRepository;
    @Autowired
    private LogOperationRepository logOperationRepository;
    /**
     * 记录操作日志
     * @param username
     * @param operation
     * @param result
     * @param detail
     */
    public void saveOpLog(String username,String operation,String result,String detail){
        LogOperation logOperation = new LogOperation();
        logOperation.setUsername(username);
        logOperation.setResult(result);
        logOperation.setOperation(operation);
        if(detail.length()>1023) {
            detail = detail.substring(0, 1023);
        }
        logOperation.setDetail(detail);
        logOperation.setDate(new Date());
        logOperationRepository.save(logOperation);
    }

    /**
     *记录系统日志
     * @param logMessage
     */
    public void saveSysLog(String logMessage){
       LogSystem logSystem = new LogSystem();
       logSystem.setLogMessage(logMessage.substring(0,1023));
       logSystem.setLogDate(new Date());
       logSystemRepository.save(logSystem);
    }
}
