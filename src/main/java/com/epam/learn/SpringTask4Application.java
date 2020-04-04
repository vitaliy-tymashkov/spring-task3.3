package com.epam.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;

@SpringBootApplication
@ComponentScan({"com.epam.learn.controller", "com.epam.learn.service"})
public class SpringTask4Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringTask4Application.class, args);
	}


}
