package com.javaMultiThreads.contextSave.service;

import com.javaMultiThreads.contextSave.entities.MariadbData;
import com.javaMultiThreads.contextSave.interfaces.DatabaseSaver;
import com.javaMultiThreads.contextSave.repositories.mariadb.MariadbDataRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MariadbSaver implements DatabaseSaver {
    private final MariadbDataRepository mariadbRepo;

    public MariadbSaver(MariadbDataRepository mariadbRepo) {
        this.mariadbRepo = mariadbRepo;
    }

    @Override
    public CompletableFuture<Void> save(String data) {
        MariadbData mariadbData = new MariadbData();
        mariadbData.setContent(data);
        mariadbRepo.save(mariadbData);
        return CompletableFuture.completedFuture(null);
    }
}
