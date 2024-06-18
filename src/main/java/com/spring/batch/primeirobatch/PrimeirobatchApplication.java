package com.spring.batch.primeirobatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PrimeirobatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimeirobatchApplication.class, args);
	}

}
