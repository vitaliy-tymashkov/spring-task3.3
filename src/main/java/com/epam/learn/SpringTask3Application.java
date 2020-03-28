package com.epam.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.epam.learn.controller", "com.epam.learn.service"})
public class SpringTask3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringTask3Application.class, args);
	}

}
