package com.pro.warehouse.Service;


import com.pro.warehouse.dao.ApplyEnterRepository;
import com.pro.warehouse.dao.ApplyOutPutRepository;
import com.pro.warehouse.dao.EntrepotStatusRepository;
import com.pro.warehouse.pojo.ApplyEnter;
import com.pro.warehouse.pojo.ApplyOutPut;
import com.pro.warehouse.pojo.EntrepotStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {
    @Autowired
    private ApplyEnterRepository applyEnterRepository;
    @Autowired
    private ApplyOutPutRepository applyOutPutRepository;
    @Autowired
    private EntrepotStatusRepository entrepotStatusRepository;
    /**
     * 获得昨日入库数量
     */
    public int getYestdayApplyEnterCount(){
        int size = 0;
        List<ApplyEnter> yestdayApplys = applyEnterRepository.getYestdayApplys();
        if(null!=yestdayApplys){
            size = yestdayApplys.size();
        }
        return  size;
    }

    /**
     * 获得昨日出库数量
     */
    public int getYestdayApplyOutCount(){
        int size = 0;
        List<ApplyOutPut> yestdayApplys = applyOutPutRepository.getYestdayApplys();
        if(null!=yestdayApplys){
            size = yestdayApplys.size();
        }
        return  size;
    }

    /**
     * 获得库存数量
     */
    public int getEntrpotSize(){
        int size = 0;
        List<EntrepotStatus> totalSize = entrepotStatusRepository.getTotalSize();
        if(null!=totalSize){
            size = totalSize.size();
        }
        return  size;
    }

}
