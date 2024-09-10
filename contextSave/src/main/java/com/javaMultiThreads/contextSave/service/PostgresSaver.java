package com.javaMultiThreads.contextSave.service;


import com.javaMultiThreads.contextSave.entities.PostgresData;
import com.javaMultiThreads.contextSave.interfaces.DatabaseSaver;
import com.javaMultiThreads.contextSave.repositories.postgres.PostgresDataRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PostgresSaver implements DatabaseSaver {
    private final PostgresDataRepository postgresRepo;

    public PostgresSaver(PostgresDataRepository postgresRepo) {
        this.postgresRepo = postgresRepo;
    }

    @Override
    public CompletableFuture<Void> save(String data) {
        PostgresData postgresData = new PostgresData();
        postgresData.setContent(data);
        postgresRepo.save(postgresData);
        return CompletableFuture.completedFuture(null);
    }
}
