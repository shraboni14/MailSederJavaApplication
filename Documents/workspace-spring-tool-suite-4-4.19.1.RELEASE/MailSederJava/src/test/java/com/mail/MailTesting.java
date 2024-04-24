package com.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mail.serrvice.MailService;

@SpringBootTest
public class MailTesting {

	@Autowired
	private MailService mailService;

	@Test
	void emailSendTest() {
		System.out.println("Sending email");
		mailService.sendEmail("shrabonisinha36@gmail.com", "subject", "This is testing mail");
	}

}
