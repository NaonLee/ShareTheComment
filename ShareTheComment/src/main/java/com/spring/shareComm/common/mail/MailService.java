package com.spring.shareComm.common.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	
	//Send email to the administrator (Contact us)
	public void sendMail(String from, String title, String body) {
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setFrom(from);
				messageHelper.setSubject(title);
				messageHelper.setTo("giraffe1254@gmail.com");
				messageHelper.setText(body);
				mailSender.send(message);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Send password to email stored in DB
	public void sendPwd(String email, String pwd) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom("ShareComment");
			messageHelper.setSubject("This is your password");
			messageHelper.setTo(email);
			messageHelper.setText("This is your password: " + pwd);
			mailSender.send(message);
			
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
}
