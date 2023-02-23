package com.allianz.interview.rest;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.allianz.interview.rest",
		"com.allianz.interview.webservice"})

public class RestApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RestApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

	}

}
