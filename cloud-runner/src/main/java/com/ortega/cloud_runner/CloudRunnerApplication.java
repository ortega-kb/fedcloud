package com.ortega.cloud_runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class CloudRunnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudRunnerApplication.class, args);
	}

}
