package com.javaMultiThreads.contextSave.controller;

import com.javaMultiThreads.contextSave.model.TextFileData;
import com.javaMultiThreads.contextSave.service.MariaDBService;
import com.javaMultiThreads.contextSave.service.MySQLService;
import com.javaMultiThreads.contextSave.service.PostgreSQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class FileController {

    private final PostgreSQLService postgreSQLService;
    private final MySQLService mySQLService;
    private final MariaDBService mariaDBService;

    @Autowired
    public FileController(PostgreSQLService postgreSQLService, MySQLService mySQLService, MariaDBService mariaDBService) {
        this.postgreSQLService = postgreSQLService;
        this.mySQLService = mySQLService;
        this.mariaDBService = mariaDBService;
    }

    @PostMapping("/postgres/add")
    public ResponseEntity<String> addDataToPostgres(@RequestParam("filePath") String filePath) {
        String content = readFileContent(filePath);
        TextFileData data = new TextFileData(content);
        postgreSQLService.saveData(data);
        return ResponseEntity.ok("Data saved to PostgreSQL");
    }

    @PostMapping("/mysql/add")
    public ResponseEntity<String> addDataToMySQL(@RequestParam("filePath") String filePath) {
        String content = readFileContent(filePath);
        TextFileData data = new TextFileData(content);
        mySQLService.saveData(data);
        return ResponseEntity.ok("Data saved to MySQL");
    }

    @PostMapping("/mariadb/add")
    public ResponseEntity<String> addDataToMariaDB(@RequestParam("filePath") String filePath) {
        String content = readFileContent(filePath);
        TextFileData data = new TextFileData(content);
        mariaDBService.saveData(data);
        return ResponseEntity.ok("Data saved to MariaDB");
    }

    private String readFileContent(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file content", e);
        }
    }
}
