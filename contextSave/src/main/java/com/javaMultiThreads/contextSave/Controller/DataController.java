package com.javaMultiThreads.contextSave.Controller;

import com.javaMultiThreads.contextSave.service.DataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("/postgres/add")
    public ResponseEntity<String> addDataToPostgres(@RequestParam int threadCount) {
        try {
            Path filePath = Paths.get(System.getProperty("user.home"), "Desktop", "file.txt");
            byte[] fileData = Files.readAllBytes(filePath);
            int chunkSize = fileData.length / threadCount;

            for (int i = 0; i < threadCount; i++) {
                int start = i * chunkSize;
                int end = (i == threadCount - 1) ? fileData.length : (i + 1) * chunkSize;
                String dataChunk = new String(fileData, start, end - start);
                dataService.saveToPostgres(dataChunk);
                dataService.saveToMysql(dataChunk);
                dataService.saveToMariadb(dataChunk);
            }

            return ResponseEntity.ok("Data successfully added to all databases");

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading file: " + e.getMessage());
        }
    }
}

