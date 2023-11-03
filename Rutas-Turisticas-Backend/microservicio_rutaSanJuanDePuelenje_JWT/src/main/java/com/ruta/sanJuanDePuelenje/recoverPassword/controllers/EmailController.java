package com.ruta.sanJuanDePuelenje.recoverPassword.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailValueDTO emailDTO){
		return this.iEmailService.sendEmail(emailDTO);
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(){
		return null;
		
	}
}
