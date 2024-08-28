package com.multiThreads.threadExample.controller;

import com.multiThreads.threadExample.service.ThreadStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threads")
public class ThreadStringController {

    @Autowired
    private ThreadStringService threadStringService;

    @GetMapping("/data")
    public String getThreadStringsByDatabase(@RequestParam String database) {
        return threadStringService.getThreadStringsByDatabase(database);
    }
}
