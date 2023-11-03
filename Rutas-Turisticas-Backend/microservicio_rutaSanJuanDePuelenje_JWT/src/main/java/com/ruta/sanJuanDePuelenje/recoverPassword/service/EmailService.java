package com.ruta.sanJuanDePuelenje.recoverPassword.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ruta.sanJuanDePuelenje.models.User;
import com.ruta.sanJuanDePuelenje.recoverPassword.domain.EmailValueDTO;
import com.ruta.sanJuanDePuelenje.repository.IUserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService implements IEmailService{
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	TemplateEngine templateEngine;
	
	@Autowired
	IUserRepository iUserRepository;
	
	@Value("${mail.urlFront}")
	private String urlFront;
	
	@Value("${spring.mail.username}")
	private String mailFrom;
	
	private static final String subject = "Cambio de contraseña";
	
	@Override
	public ResponseEntity<?> sendEmail(EmailValueDTO emailDTO) {
		MimeMessage message = javaMailSender.createMimeMessage();
		User user = this.iUserRepository.findByEmail(emailDTO.getEmailTo());
		if(user == null)
			return new ResponseEntity<>("No existe un usuario registrado con este correo", HttpStatus.NOT_FOUND);
		emailDTO.setEmailFrom(mailFrom);
		emailDTO.setEmailTo(user.getEmail());
		emailDTO.setEmailSubject(subject);
		emailDTO.setUserName(user.getName());
		UUID uuid = UUID.randomUUID();	
		String tokenPassword = uuid.toString();
		emailDTO.setEmailTokenPassword(tokenPassword);
		user.setTokenPassword(tokenPassword);
		this.iUserRepository.save(user);
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			Context context = new Context();
			Map<String, Object> model = new HashMap<>();
			model.put("userName", emailDTO.getUserName());
			System.out.println("emailllll: "+ emailDTO.getEmailTokenPassword());
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
		return new ResponseEntity<>("Te hemos enviado un correo ", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> changePassword() {
		// TODO Auto-generated method stub
		return null;
	}

}
