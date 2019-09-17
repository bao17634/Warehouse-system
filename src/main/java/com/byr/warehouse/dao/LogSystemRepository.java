package com.byr.warehouse.dao;

import com.byr.warehouse.pojo.LogSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogSystemRepository extends JpaRepository<LogSystem, Long> {
}
