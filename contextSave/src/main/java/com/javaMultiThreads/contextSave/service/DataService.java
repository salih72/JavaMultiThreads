package com.javaMultiThreads.contextSave.service;

import com.javaMultiThreads.contextSave.factories.DatabaseSaverFactory;
import com.javaMultiThreads.contextSave.interfaces.DatabaseSaver;
import com.javaMultiThreads.contextSave.repositories.mariadb.MariadbDataRepository;
import com.javaMultiThreads.contextSave.repositories.mysql.MysqlDataRepository;
import com.javaMultiThreads.contextSave.repositories.postgres.PostgresDataRepository;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    public ResponseEntity<String> saveDataToDatabase(String dbType, int threadCount) {
        Path filePath = Paths.get(System.getProperty("user.home"), "Desktop", "big-file.txt");

        DatabaseSaver saveFunction = DatabaseSaverFactory.createSaver(dbType, postgresRepo, mysqlRepo, mariadbRepo);

        try {
            long fileSize = Files.size(filePath);
            long chunkSize = fileSize / threadCount;

            List<CompletableFuture<Void>> futures = new ArrayList<>();

            for (int i = 0; i < threadCount; i++) {
                long startByte = i * chunkSize;
                long endByte = (i == threadCount - 1) ? fileSize : (i + 1) * chunkSize;
                int threadNumber = i + 1;

                futures.add(CompletableFuture.runAsync(() -> {
                    try (RandomAccessFile raf = new RandomAccessFile(filePath.toFile(), "r")) {
                        raf.seek(startByte);
                        byte[] buffer = new byte[(int) chunkSize];
                        long bytesToRead = endByte - startByte;
                        long bytesRead = 0;

                        while (bytesRead < bytesToRead) {
                            int bytesToLoad = (int) Math.min(buffer.length, bytesToRead - bytesRead);
                            int read = raf.read(buffer, 0, bytesToLoad);
                            if (read == -1) break;

                            String dataChunk = new String(buffer, 0, read, StandardCharsets.UTF_8);
                            String dataWithThreadInfo = "Thread #" + threadNumber + ": " + dataChunk;
                            saveFunction.save(dataWithThreadInfo).join();

                            bytesRead += read;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
            }

            CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
            allFutures.join();

            return ResponseEntity.ok("Data successfully added to " + dbType);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading file: " + e.getMessage());
        }
    }
}
