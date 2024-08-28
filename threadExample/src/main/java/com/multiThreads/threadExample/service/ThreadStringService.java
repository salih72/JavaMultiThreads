package com.multiThreads.threadExample.service;

import com.multiThreads.threadExample.entity.mysql.MySqlEntity;
import com.multiThreads.threadExample.entity.postgresql.PostgreSqlEntity;
import com.multiThreads.threadExample.repository.mysql.MySqlThreadStringRepository;
import com.multiThreads.threadExample.repository.postgresql.PostgreSqlThreadStringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThreadStringService {

    @Autowired
    private MySqlThreadStringRepository mysqlRepo;

    @Autowired
    private PostgreSqlThreadStringRepository postgresRepo;

    // Veritabanı türüne göre verileri çekip dönen metot
    public String getThreadStringsByDatabase(String databaseType) {
        if ("mysql".equalsIgnoreCase(databaseType)) {
            List<MySqlEntity> mySqlData = mysqlRepo.findAll();
            return mySqlData.stream()
                    .map(MySqlEntity::getContent)
                    .collect(Collectors.joining(" "));
        } else if ("postgresql".equalsIgnoreCase(databaseType)) {
            List<PostgreSqlEntity> postgreSqlData = postgresRepo.findAll();
            return postgreSqlData.stream()
                    .map(PostgreSqlEntity::getContent)
                    .collect(Collectors.joining(" "));
        } else {
            // Geçersiz bir veritabanı türü girilirse bir hata mesajı döndürün
            return "Invalid database type specified. Please use 'mysql' or 'postgres'.";
        }
    }
}
