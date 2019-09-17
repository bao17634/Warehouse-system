package com.pro.warehouse.dao;

import com.pro.warehouse.pojo.LogOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogOperationRepository extends JpaRepository<LogOperation, Long> {
}
