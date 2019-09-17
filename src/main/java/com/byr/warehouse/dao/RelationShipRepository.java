package com.byr.warehouse.dao;

import com.byr.warehouse.pojo.RelationShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationShipRepository extends JpaRepository<RelationShip,Long> {

     RelationShip findRelationShipByid(int id);

     List<RelationShip> findRelationShipsBysupplyName(String supplyName);


}
