package com.byr.warehouse.dao;

import com.byr.warehouse.pojo.ApplyOutPut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ApplyOutPutRepository extends JpaRepository<ApplyOutPut, Long> {
    Page<ApplyOutPut> findApplyOutPutByStatusNot(String Status, Pageable pageable); //查询状态不是已确认的入库单
    Page<ApplyOutPut> findApplyOutPutByStatus(String Status, Pageable pageable); //根据状态查询入库单
    ApplyOutPut findApplyOutPutById(Integer id);//查看单个出货单
    List<ApplyOutPut> findAllByOutCode(String outCode);

    @Query("SELECT out FROM ApplyOutPut out where out.applyDate >= :startDate and  out.applyDate <= :endDate and status='已确认'")
    List<ApplyOutPut> findBetweenDays(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    /**
     * 这里先用List表示，而不用int。方便功能扩展时如果需要可以不需要重写
     * @return
     */
    @Query("SELECT out FROM ApplyOutPut out WHERE TO_DAYS(NOW()) - TO_DAYS(out.applyDate) = 1 AND STATUS='已确认'")
    List<ApplyOutPut> getYestdayApplys();


    @Query("SELECT out FROM ApplyOutPut out WHERE TO_DAYS(NOW()) - TO_DAYS(out.applyDate) = 0 AND STATUS='已确认'")
    List<ApplyOutPut> getTodayEnsure();
}
