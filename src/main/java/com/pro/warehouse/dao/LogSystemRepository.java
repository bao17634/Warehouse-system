package com.pro.warehouse.dao;

import com.pro.warehouse.pojo.LogSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogSystemRepository extends JpaRepository<LogSystem, Long> {
}
