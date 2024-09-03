package com.javaMultiThreads.contextSave.service;

import com.javaMultiThreads.contextSave.entities.MariadbData;
import com.javaMultiThreads.contextSave.entities.MysqlData;
import com.javaMultiThreads.contextSave.entities.PostgresData;
import com.javaMultiThreads.contextSave.repositories.mariadb.MariadbDataRepository;
import com.javaMultiThreads.contextSave.repositories.mysql.MysqlDataRepository;
import com.javaMultiThreads.contextSave.repositories.postgres.PostgresDataRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    private final PostgresDataRepository postgresRepo;
    private final MysqlDataRepository mysqlRepo;
    private final MariadbDataRepository mariadbRepo;

    public DataService(PostgresDataRepository postgresRepo, MysqlDataRepository mysqlRepo, MariadbDataRepository mariadbRepo) {
        this.postgresRepo = postgresRepo;
        this.mysqlRepo = mysqlRepo;
        this.mariadbRepo = mariadbRepo;
    }

    @Async("threadPoolTaskExecutor")
    public void saveToPostgres(String data) {
        PostgresData postgresData = new PostgresData();
        postgresData.setContent(data);
        postgresRepo.save(postgresData);
    }

    @Async("threadPoolTaskExecutor")
    public void saveToMysql(String data) {
        MysqlData mysqlData = new MysqlData();
        mysqlData.setContent(data);
        mysqlRepo.save(mysqlData);
    }

    @Async("threadPoolTaskExecutor")
    public void saveToMariadb(String data) {
        MariadbData mariadbData = new MariadbData();
        mariadbData.setContent(data);
        mariadbRepo.save(mariadbData);
    }
}

