package com.mail.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mail.serrvice.MailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;

	private Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Override
	public void sendEmail(String to, String subject, String message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("shrabonisinha14@gmail.com");
		mailSender.send(simpleMailMessage);
		logger.info("Email has been sent...");

	}

	@Override
	public void sendEmail(String[] to, String subject, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmailWithHtml(String to, String subject, String htmlConcent) {
		MimeMessage simpleMailMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage, true, "UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("shrabonisinha14@gmail.com");
			helper.setText(htmlConcent, true);
			mailSender.send(simpleMailMessage);
			logger.info("Email has been sent...");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void sendEmailWithFile(String to, String subject, String message, File file) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom("shrabonisinha14@gmail.com");
			helper.setTo(to);
			helper.setText(message);
			helper.setSubject(subject);
			FileSystemResource fileSystemResource = new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(), file);
			mailSender.send(mimeMessage);
			logger.info("email send successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void sendEmailWithFile(String to, String subject, String message, InputStream is) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom("shrabonisinha14@gmail.com");
			helper.setTo(to);
			helper.setText(message);
			helper.setSubject(subject);
			File file = new File(
					"/Users/shrabonisinha/Documents/workspace-spring-tool-suite-4-4.19.1.RELEASE/JavaMailSender/src/main/resources/photo.JPG");
			try {
				Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
			FileSystemResource fileSystemResource = new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(), file);

			mailSender.send(mimeMessage);
			logger.info("email send successfully with stream");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}

}
