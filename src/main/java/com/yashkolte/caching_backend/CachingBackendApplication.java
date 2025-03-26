package com.yashkolte.caching_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching  // Enable caching in Spring Boot
public class CachingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingBackendApplication.class, args);
	}

}
