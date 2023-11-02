package com.ruta.sanJuanDePuelenje.recoverPassword.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ruta.sanJuanDePuelenje.recoverPassword.domain.EmailValueDTO;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService implements IEmailService{
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	TemplateEngine templateEngine;
	
	@Value("${mail.urlFront}")
	private String urlFront;
	
//	@Override
//	public void sendEmail() {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom("yazminsarria@gmail.com");
//		message.setTo("yazminsarria@gmail.com");
//		message.setSubject("prueba envio email simple");
//		message.setText("Contenido del email");
//		javaMailSender.send(message);
//	}
	
	@Override
	public void sendEmail(EmailValueDTO emailDTO) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			Context context = new Context();
			Map<String, Object> model = new HashMap<>();
			model.put("userName", emailDTO.getUserName());
			model.put("url", urlFront + emailDTO.getEmailTokenPassword());
			context.setVariables(model);
			String htmlText = this.templateEngine.process("email-template", context);
			helper.setFrom(emailDTO.getEmailFrom());
			helper.setTo(emailDTO.getEmailTo());
			helper.setSubject(emailDTO.getEmailSubject());
			helper.setText(htmlText, true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
