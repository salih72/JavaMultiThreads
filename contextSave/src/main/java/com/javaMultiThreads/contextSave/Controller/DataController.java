package com.javaMultiThreads.contextSave.Controller;

import com.javaMultiThreads.contextSave.service.DataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/{dbType}/save")
    public ResponseEntity<String> addDataToDatabase(@PathVariable String dbType, @RequestParam int threadCount) {
        return dataService.saveDataToDatabase(dbType, threadCount);
    }
}
