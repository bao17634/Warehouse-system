package com.pro.warehouse.dao;

import com.pro.warehouse.pojo.ApplyEnter;
import com.pro.warehouse.pojo.ApplyOutPut;
import com.pro.warehouse.pojo.EntrepotStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ApplyEnterRepository extends JpaRepository<ApplyEnter, Long> {
    Page<ApplyEnter> findApplyEnterByStatusNot(String Status, Pageable pageable); //查询状态不是已确认的入库单
    Page<ApplyEnter>  findApplyEnterByStatus(String Status, Pageable pageable); //根据状态查询入库单
    ApplyEnter findApplyEnterByenterId(Integer id);//查看单个出货单

    @Query("SELECT enter FROM ApplyEnter enter where enter.applyDate <= :applyDate and status ='待审核'")
    List<ApplyEnter> findBeforeDate(@Param("applyDate") Date date);

    @Query("SELECT enter FROM ApplyEnter enter where enter.enterCode = :enterCode and materialCode =:materialCode")
    List<ApplyEnter> findApplyEnterByEnterCodeAndMaterialCode(@Param("enterCode") String enterCode, @Param("materialCode") String materialCode);

    @Query("SELECT enter FROM ApplyEnter enter where enter.applyDate >= :startDate and  enter.applyDate <= :endDate and enter.status='已确认'")
    List<ApplyEnter> findBetweenDays(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 这里先用List表示，而不用int。方便功能扩展时如果需要可以不需要重写
     * @return
     */
    @Query("SELECT number FROM ApplyEnter enter WHERE TO_DAYS(NOW()) - TO_DAYS(enter.applyDate) = 1 AND STATUS='已确认'")
    List<ApplyEnter> getYestdayApplys();

    @Query("SELECT enter FROM ApplyEnter enter WHERE TO_DAYS(NOW()) - TO_DAYS(enter.applyDate) = 0 AND STATUS='已确认'")
    List<ApplyEnter> getTodayEnsure();
}
