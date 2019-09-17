package com.pro.warehouse.dao;

import com.pro.warehouse.pojo.MaterialInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialInfoRepository extends JpaRepository<MaterialInfo,Long> {
    MaterialInfo findMaterialInfoByid(int id);
}
