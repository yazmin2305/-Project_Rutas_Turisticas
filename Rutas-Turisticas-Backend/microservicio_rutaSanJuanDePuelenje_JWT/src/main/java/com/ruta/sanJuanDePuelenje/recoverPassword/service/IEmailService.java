package com.ruta.sanJuanDePuelenje.recoverPassword.service;

import org.springframework.http.ResponseEntity;

import com.ruta.sanJuanDePuelenje.recoverPassword.domain.EmailValueDTO;

public interface IEmailService {
	
	public ResponseEntity<?> sendEmail(EmailValueDTO emailDTO);
	
	public ResponseEntity<?> changePassword();
}
