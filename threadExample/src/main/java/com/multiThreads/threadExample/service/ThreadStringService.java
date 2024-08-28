package com.multiThreads.threadExample.service;

import com.multiThreads.threadExample.entity.MySqlEntity;
import com.multiThreads.threadExample.entity.PostgreSqlEntity;
import com.multiThreads.threadExample.repository.mysql.MySqlThreadStringRepository;
import com.multiThreads.threadExample.repository.postgresql.PostgreSqlThreadStringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadStringService {

    @Autowired
    private MySqlThreadStringRepository mysqlRepo;

    @Autowired
    private PostgreSqlThreadStringRepository postgresRepo;

    public void saveThreadStringToBothDatabases(String content) {
        // MySQL için entity oluşturma ve kaydetme
        MySqlEntity mySqlThreadString = new MySqlEntity();
        mySqlThreadString.setContent(content);
        mysqlRepo.save(mySqlThreadString);

        // PostgreSQL için entity oluşturma ve kaydetme
        PostgreSqlEntity postgreSqlThreadString = new PostgreSqlEntity();
        postgreSqlThreadString.setContent(content);
        postgresRepo.save(postgreSqlThreadString);
    }
}
