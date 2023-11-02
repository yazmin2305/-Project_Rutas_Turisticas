package com.ruta.sanJuanDePuelenje.recoverPassword.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailValueDTO {

	private String emailFrom;
	private String emailTo;
	private String emailSubject;
	private String userName;
	private String emailTokenPassword;
}
