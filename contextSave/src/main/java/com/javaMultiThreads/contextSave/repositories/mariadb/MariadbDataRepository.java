package com.javaMultiThreads.contextSave.repositories.mariadb;

import com.javaMultiThreads.contextSave.entities.MariadbData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MariadbDataRepository extends JpaRepository<MariadbData, Long> {}

