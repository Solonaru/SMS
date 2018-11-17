package com.project.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication();

		@SuppressWarnings({ "all" })
		ApplicationContext applicationContext = springApplication.run(App.class, args);

	}
}