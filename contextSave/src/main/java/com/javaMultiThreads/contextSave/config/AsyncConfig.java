package com.javaMultiThreads.contextSave.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);  // Minimum thread sayısı
        executor.setMaxPoolSize(50);  // Maksimum thread sayısı
        executor.setQueueCapacity(100);  // Kuyruk kapasitesini sıfır yaptık (her iş hemen yeni thread alacak)
        executor.setThreadNamePrefix("Async-");
        executor.setAllowCoreThreadTimeOut(true);  // Core thread'lerin iş bittikten sonra sonlanmasına izin ver
        executor.setKeepAliveSeconds(30);  // Thread boşta 1 saniye kaldığında sonlansın
        executor.initialize();
        return executor;
    }
}


