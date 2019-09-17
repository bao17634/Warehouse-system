package com.byr.warehouse.Service;

import com.byr.warehouse.dao.ApplyOutPutRepository;
import com.byr.warehouse.pojo.ApplyOutPut;
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
