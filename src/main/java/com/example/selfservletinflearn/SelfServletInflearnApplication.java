package com.example.selfservletinflearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SelfServletInflearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelfServletInflearnApplication.class, args);
	}

}
