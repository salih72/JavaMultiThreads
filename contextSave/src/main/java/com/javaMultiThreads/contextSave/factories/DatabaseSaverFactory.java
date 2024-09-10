package com.javaMultiThreads.contextSave.factories;

import com.javaMultiThreads.contextSave.interfaces.DatabaseSaver;
import com.javaMultiThreads.contextSave.repositories.mariadb.MariadbDataRepository;
import com.javaMultiThreads.contextSave.repositories.mysql.MysqlDataRepository;
import com.javaMultiThreads.contextSave.repositories.postgres.PostgresDataRepository;
import com.javaMultiThreads.contextSave.service.MariadbSaver;
import com.javaMultiThreads.contextSave.service.MysqlSaver;
import com.javaMultiThreads.contextSave.service.PostgresSaver;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSaverFactory {
    public static DatabaseSaver createSaver(String dbType, PostgresDataRepository postgresRepo, MysqlDataRepository mysqlRepo, MariadbDataRepository mariadbRepo) {
        switch (dbType.toLowerCase()) {
            case "postgres":
                return new PostgresSaver(postgresRepo);
            case "mysql":
                return new MysqlSaver(mysqlRepo);
            case "mariadb":
                return new MariadbSaver(mariadbRepo);
            default:
                throw new IllegalArgumentException("Unknown database type: " + dbType);
        }
    }
}
