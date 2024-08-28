package com.multiThreads.threadExample.repository.mysql;

import com.multiThreads.threadExample.entity.MySqlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySqlThreadStringRepository extends JpaRepository<MySqlEntity, Long> {
}

