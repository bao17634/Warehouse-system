package com.pro.warehouse.dao;

import com.pro.warehouse.pojo.ApplyOutPut;
import com.pro.warehouse.pojo.EntrepotStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EntrepotStatusRepository extends JpaRepository<EntrepotStatus, Long> {
    List<EntrepotStatus> findEntrepotStatusBymaterialCode(String materialCode);
    List<EntrepotStatus> findEntrepotStatusByEnterCode(String enterCode);
    List<EntrepotStatus> findEntrepotStatusByid(Long id);
   // List<EntrepotStatus> findByEnterCodeAndMaterialCode(String enterCode,String materialCode);
    @Query("SELECT entrepot FROM EntrepotStatus entrepot where entrepot.enterCode = :enterCode and materialCode like :materialCode")
    List<EntrepotStatus> findByEnterCodeAndMaterialCode(@Param("enterCode") String enterCode, @Param("materialCode") String materialCode);

    @Query("SELECT entrepot FROM EntrepotStatus entrepot where entrepot.entranceDate <= :entranceDate and goodsStatus='良品'")
    List<EntrepotStatus> findBeforeDate(@Param("entranceDate") Date date);

    @Query("select entrepot from EntrepotStatus entrepot")
    List<EntrepotStatus> getTotalSize();
}
