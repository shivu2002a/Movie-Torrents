package com.shivu.yifyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Slf4j
public class YifyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(YifyAppApplication.class, args);
		LOGGER.info("Application started");
	}

}
