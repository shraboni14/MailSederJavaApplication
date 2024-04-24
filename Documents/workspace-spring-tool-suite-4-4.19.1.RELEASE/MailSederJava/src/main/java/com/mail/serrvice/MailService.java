package com.mail.serrvice;

import java.io.File;
import java.io.InputStream;

public interface MailService {
//	send email to single person
	void sendEmail(String to, String subject, String message);
	
//	send email to multiple person
	void sendEmail(String[] to, String subject, String message);
	
//	send email with html
	void sendEmailWithHtml(String to, String subject, String htmlConcent);
	
//	void send email with file
	void sendEmailWithFile(String to, String subject, String message, File file);
	
//	void send email with dynamic file
	void sendEmailWithFile(String to, String subject, String message, InputStream is);

}
