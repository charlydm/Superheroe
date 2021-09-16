package com.w2m.superheroe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SuperheroeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SuperheroeApplication.class, args);
	}

}
