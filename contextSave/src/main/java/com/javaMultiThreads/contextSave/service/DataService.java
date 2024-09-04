package com.javaMultiThreads.contextSave.service;

import com.javaMultiThreads.contextSave.entities.MariadbData;
import com.javaMultiThreads.contextSave.entities.MysqlData;
import com.javaMultiThreads.contextSave.entities.PostgresData;
import com.javaMultiThreads.contextSave.repositories.mariadb.MariadbDataRepository;
import com.javaMultiThreads.contextSave.repositories.mysql.MysqlDataRepository;
import com.javaMultiThreads.contextSave.repositories.postgres.PostgresDataRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Service
@EnableAsync
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
    public CompletableFuture<Void> saveToPostgres(String data) {
        PostgresData postgresData = new PostgresData();
        postgresData.setContent(data);
        postgresRepo.save(postgresData);
        return CompletableFuture.completedFuture(null);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Void> saveToMysql(String data) {
        MysqlData mysqlData = new MysqlData();
        mysqlData.setContent(data);
        mysqlRepo.save(mysqlData);
        return CompletableFuture.completedFuture(null);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Void> saveToMariadb(String data) {
        MariadbData mariadbData = new MariadbData();
        mariadbData.setContent(data);
        mariadbRepo.save(mariadbData);
        return CompletableFuture.completedFuture(null);
    }

    public ResponseEntity<String> saveDataToDatabase(String dbType, int threadCount) {
        try {
            Path filePath = Paths.get(System.getProperty("user.home"), "Desktop", "big-file.txt");
            byte[] fileData = Files.readAllBytes(filePath);
            int chunkSize = fileData.length / threadCount;

            Consumer<String> saveFunction;

            switch (dbType) {
                case "postgres":
                    saveFunction = this::saveToPostgres;
                    break;
                case "mysql":
                    saveFunction = this::saveToMysql;
                    break;
                case "mariadb":
                    saveFunction = this::saveToMariadb;
                    break;
                default:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown database type");
            }

            for (int i = 0; i < threadCount; i++) {
                int start = i * chunkSize;
                int end = (i == threadCount - 1) ? fileData.length : (i + 1) * chunkSize;
                final String dataChunk = "Thread #" + (i + 1) + ": " + new String(fileData, start, end - start);

                CompletableFuture.runAsync(() -> saveFunction.accept(dataChunk));
            }

            return ResponseEntity.ok("Data successfully added to " + dbType);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading file: " + e.getMessage());
        }
    }
}