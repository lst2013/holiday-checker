package com.recruitment.task.holidaychecker.web;

import com.recruitment.task.holidaychecker.rest.client.errorhandler.HolidayApiResponseErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.recruitment.task.holidaychecker")
@EnableJpaRepositories(basePackages = "com.recruitment.task.holidaychecker.config.repository")
@EntityScan(basePackages = "com.recruitment.task.holidaychecker.config.entity")
public class HolidayCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolidayCheckerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new HolidayApiResponseErrorHandler());

		return restTemplate;
	}
}