package com.mail.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.mail.serrvice.MailService;

@Controller
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private MailService mailService;

//	send mail
	@PostMapping("/send")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {
		mailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
		return ResponseEntity.ok(CustomResponse.builder().message("email sent successfully").httpStatus(HttpStatus.OK)
				.success(true).build());

	}

	@PostMapping("/send-with-file")
	public ResponseEntity<CustomResponse> sendWithFile(@RequestPart EmailRequest request,
			@RequestPart MultipartFile file) throws IOException {
		mailService.sendEmailWithFile(request.getTo(), request.getSubject(), request.getMessage(),
				file.getInputStream());
		return ResponseEntity.ok(CustomResponse.builder().message("email sent successfully").httpStatus(HttpStatus.OK)
				.success(true).build());
	}

}
