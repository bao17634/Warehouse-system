package com.byr.warehouse.dao;

import com.byr.warehouse.pojo.MaterialInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialInfoRepository extends JpaRepository<MaterialInfo,Long> {
    MaterialInfo findMaterialInfoByid(int id);
}
