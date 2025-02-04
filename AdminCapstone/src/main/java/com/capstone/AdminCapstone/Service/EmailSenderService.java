package com.capstone.AdminCapstone.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String toEmail, String subject, String Body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("pandoraroyaltymangment@gmail.com");
		message.setTo(toEmail);
		message.setText(Body);
		message.setSubject(subject);
		
		mailSender.send(message);
		
		System.out.println("Mail Sent");
	}
	
	
}
