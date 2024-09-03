package com.javaMultiThreads.contextSave.repositories.mysql;

import com.javaMultiThreads.contextSave.entities.MysqlData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlDataRepository extends JpaRepository<MysqlData, Long> {}

