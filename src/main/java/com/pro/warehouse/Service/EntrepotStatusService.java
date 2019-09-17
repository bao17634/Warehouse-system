package com.pro.warehouse.Service;

import com.pro.warehouse.dao.EntrepotStatusRepository;
import com.pro.warehouse.pojo.EntrepotStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntrepotStatusService {
    @Autowired
    private EntrepotStatusRepository entrepotStatusRepository;

    /**
     * 两个条件查询，暂时先这样
     * @param enterCode
     * @param materialCode
     * @return
     */
    public List<EntrepotStatus> finEntrpotByEnterCodeAndMaterialId(String enterCode,String materialCode) throws Exception {
        List<EntrepotStatus> results = new ArrayList<EntrepotStatus>();
        List<EntrepotStatus> byEnterCode = entrepotStatusRepository.findEntrepotStatusByEnterCode(enterCode);
        System.err.println("从库存查找"+byEnterCode+"    ");
        if(byEnterCode.size()==0){
            throw new Exception("Don't find the goods!");
        }
        for(EntrepotStatus entrepotStatus:byEnterCode){
            System.out.println("从结果中筛选:"+entrepotStatus.getMaterialCode()+"  " +materialCode);
            if(materialCode.equals(entrepotStatus.getMaterialCode())){
                results.add(entrepotStatus);
            }
        }
        return results;
    }

    /**
     * 获取全部库存数量
     */
    public int getAllEntrepotCount(){
        List<EntrepotStatus> all = entrepotStatusRepository.findAll();
        int total =0;
        for(EntrepotStatus entrepotStatus:all){
            total = total+entrepotStatus.getTotalSize();
        }
        return total;
    }
}
