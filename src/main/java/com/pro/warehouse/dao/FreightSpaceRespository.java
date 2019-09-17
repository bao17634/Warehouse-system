package com.pro.warehouse.dao;

import com.pro.warehouse.pojo.FreightSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreightSpaceRespository extends JpaRepository<FreightSpace,Long>{

    FreightSpace findFreightSpaceByid(int id);
}
