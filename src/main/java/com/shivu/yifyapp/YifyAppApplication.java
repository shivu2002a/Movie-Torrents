package com.shivu.yifyapp;

import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class YifyAppApplication {

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(YifyAppApplication.class, args);
		
	}

}
