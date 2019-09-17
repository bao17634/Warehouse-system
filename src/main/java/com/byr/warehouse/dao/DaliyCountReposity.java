package com.byr.warehouse.dao;

import com.byr.warehouse.pojo.DaliyCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DaliyCountReposity extends JpaRepository<DaliyCount, Long> {
    @Query("SELECT daily FROM DaliyCount daily where daily.computeDate >= :startDate and  daily.computeDate <= :endDate")
    List<DaliyCount> findBetweenDays(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
