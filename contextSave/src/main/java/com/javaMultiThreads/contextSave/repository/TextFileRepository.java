package com.javaMultiThreads.contextSave.repository;

import com.javaMultiThreads.contextSave.model.TextFileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextFileRepository extends JpaRepository<TextFileData, Long> {
}
