package com.multiThreads.threadExample.repository.postgresql;

import com.multiThreads.threadExample.entity.postgresql.PostgreSqlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgreSqlThreadStringRepository extends JpaRepository<PostgreSqlEntity, Long> {
}

