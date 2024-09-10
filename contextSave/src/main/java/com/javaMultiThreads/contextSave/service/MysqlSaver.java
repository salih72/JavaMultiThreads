package com.javaMultiThreads.contextSave.service;

import com.javaMultiThreads.contextSave.entities.MysqlData;
import com.javaMultiThreads.contextSave.interfaces.DatabaseSaver;
import com.javaMultiThreads.contextSave.repositories.mysql.MysqlDataRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MysqlSaver implements DatabaseSaver {
    private final MysqlDataRepository mysqlRepo;

    public MysqlSaver(MysqlDataRepository mysqlRepo) {
        this.mysqlRepo = mysqlRepo;
    }

    @Override
    public CompletableFuture<Void> save(String data) {
        MysqlData mysqlData = new MysqlData();
        mysqlData.setContent(data);
        mysqlRepo.save(mysqlData);
        return CompletableFuture.completedFuture(null);
    }
}
