package com.pro.warehouse.Service;

import com.pro.warehouse.dao.ApplyOutPutRepository;
import com.pro.warehouse.pojo.ApplyEnter;
import com.pro.warehouse.pojo.ApplyOutPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyOutService {
    @Autowired
    private ApplyOutPutRepository applyOutPutRepository;

    public int getNumberOfTodayApplyEnter(){
        List<ApplyOutPut> todayEnsure = applyOutPutRepository.getTodayEnsure();
        int total = 0;
        for(ApplyOutPut applyOutPut:todayEnsure){
            total = applyOutPut.getSize()+total;
        }
        return total;
    }
}
