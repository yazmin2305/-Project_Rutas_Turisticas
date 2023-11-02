package com.ruta.sanJuanDePuelenje.recoverPassword.service;

import com.ruta.sanJuanDePuelenje.recoverPassword.domain.EmailValueDTO;

public interface IEmailService {
	
//	public void sendEmail();
	
	public void sendEmail(EmailValueDTO emailDTO);
}
