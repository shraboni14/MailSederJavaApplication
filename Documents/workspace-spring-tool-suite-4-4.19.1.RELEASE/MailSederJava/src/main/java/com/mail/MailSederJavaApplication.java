package com.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MailSederJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailSederJavaApplication.class, args);
		System.out.println("Welcome to mail sending application");
	}

}
