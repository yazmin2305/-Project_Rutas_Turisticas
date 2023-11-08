package com.ruta.sanJuanDePuelenje.recoverPassword.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruta.sanJuanDePuelenje.recoverPassword.domain.ChangePasswordDTO;
import com.ruta.sanJuanDePuelenje.recoverPassword.domain.EmailValueDTO;
import com.ruta.sanJuanDePuelenje.recoverPassword.service.IEmailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/emailPassword")
@CrossOrigin
public class EmailController {
	
	@Autowired
	private IEmailService iEmailService;
	
	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailValueDTO emailDTO){
		return this.iEmailService.sendEmail(emailDTO);
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO changePassDTO, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return new ResponseEntity<>("Campos incorrectos ", HttpStatus.BAD_REQUEST);
		if(!changePassDTO.getPassword().equals(changePassDTO.getConfirmPassword()))
			return new ResponseEntity<>("Las contraseñas no coinciden ", HttpStatus.BAD_REQUEST);
		return this.iEmailService.changePassword(changePassDTO);	
	}
}
