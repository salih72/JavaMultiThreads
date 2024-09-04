package com.javaMultiThreads.contextSave.Controller;

import com.javaMultiThreads.contextSave.service.DataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("/postgres/add")
    public ResponseEntity<String> addDataToPostgres(@RequestParam int threadCount) {
        return dataService.saveDataToDatabase("postgres", threadCount);
    }

    @PostMapping("/mysql/add")
    public ResponseEntity<String> addDataToMysql(@RequestParam int threadCount) {
        return dataService.saveDataToDatabase("mysql", threadCount);
    }

    @PostMapping("/mariadb/add")
    public ResponseEntity<String> addDataToMariadb(@RequestParam int threadCount) {
        return dataService.saveDataToDatabase("mariadb", threadCount);
    }
}
