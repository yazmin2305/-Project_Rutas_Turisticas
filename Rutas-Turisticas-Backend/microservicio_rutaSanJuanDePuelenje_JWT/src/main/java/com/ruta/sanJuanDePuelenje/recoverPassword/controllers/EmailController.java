package com.ruta.sanJuanDePuelenje.recoverPassword.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruta.sanJuanDePuelenje.recoverPassword.domain.EmailValueDTO;
import com.ruta.sanJuanDePuelenje.recoverPassword.service.IEmailService;

@RestController
@RequestMapping("/emailPassword")
@CrossOrigin
public class EmailController {
	
	@Autowired
	private IEmailService iEmailService;
	
	@Value("${spring.mail.username}")
	private String mailFrom;
	
//	@GetMapping("/send-email")
//	public ResponseEntity<?> sendEmail(){
//		this.iEmailService.sendEmail();
//		return new ResponseEntity<>("Correo enviado con éxito", HttpStatus.OK);
//	}
	
	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValueDTO emailDTO){
		this.iEmailService.sendEmail(emailDTO);
		return new ResponseEntity<>("Te hemos enviado un correo ", HttpStatus.OK);
	}
	
}
