package com.spring.shareComm.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	
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
}
