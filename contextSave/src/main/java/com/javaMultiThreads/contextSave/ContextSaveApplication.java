package com.javaMultiThreads.contextSave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.javaMultiThreads.repository")
@EntityScan(basePackages = "com.javaMultiThreads.model")
public class ContextSaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContextSaveApplication.class, args);
	}

}
