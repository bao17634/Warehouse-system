package com.pro.warehouse.Service;

import com.pro.warehouse.dao.DaliyCountReposity;
import com.pro.warehouse.pojo.DaliyCount;
import com.pro.warehouse.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 为系统统计图表提供数据
 */
@Service
public class ChartDataProviderService {
    @Autowired
    private DaliyCountReposity daliyCountReposity;

    /**
     * 获得七天的库存数量,直接返回满足echarts格式的json串
     */
    public List<DaliyCount> getStatusCountIn7Days(){
        List<DaliyCount> betweenDays = daliyCountReposity.findBetweenDays(DateUtil.getDateBefore(new Date(), 7), new Date());
        return betweenDays;
    }
    /**
     * 获得七天的入库数量
     */
    public int getEnterCountIn7Days(){
        return 0;
    }
    /**
     * 获得七天的出库数量
     */
    public int getOutountIn7Days(){
        return 0;
    }
}
