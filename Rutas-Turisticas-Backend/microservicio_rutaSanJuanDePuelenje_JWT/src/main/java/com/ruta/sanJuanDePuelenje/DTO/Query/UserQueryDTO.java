package com.ruta.sanJuanDePuelenje.DTO.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryDTO {
	private String userIdentification;
	private String userName;
	private String userLastName;
	private String userPhone;
	private String userEmail;
	private Boolean userState;
	private String userPassword;
	private RoleQueryDTO role;
	//es necesario que al consultar me regrese el rol? o depende del rol persona que haga la consulta?
}
