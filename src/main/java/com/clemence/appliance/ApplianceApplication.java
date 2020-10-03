package com.clemence.appliance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ApplianceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplianceApplication.class, args);
	}

}
