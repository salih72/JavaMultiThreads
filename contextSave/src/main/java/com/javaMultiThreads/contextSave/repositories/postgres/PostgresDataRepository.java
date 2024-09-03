package com.javaMultiThreads.contextSave.repositories.postgres;

import com.javaMultiThreads.contextSave.entities.PostgresData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresDataRepository extends JpaRepository<PostgresData, Long> {}

