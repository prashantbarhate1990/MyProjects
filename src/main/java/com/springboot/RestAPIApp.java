package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.springboot"})
public class RestAPIApp {
	public static void main(String[] args) {
		SpringApplication.run(RestAPIApp.class, args);
	}

}
