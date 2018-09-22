package com.leukemia.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.leukemia")
public class CureLeuKemiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CureLeuKemiaApplication.class, args);
	}
}
