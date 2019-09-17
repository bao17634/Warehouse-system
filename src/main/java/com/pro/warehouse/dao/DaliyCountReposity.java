package com.pro.warehouse.dao;

import com.pro.warehouse.pojo.ApplyOutPut;
import com.pro.warehouse.pojo.DaliyCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Repository
public interface DaliyCountReposity extends JpaRepository<DaliyCount, Long> {
    @Query("SELECT daily FROM DaliyCount daily where daily.computeDate >= :startDate and  daily.computeDate <= :endDate")
    List<DaliyCount> findBetweenDays(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
