package com.ruta.sanJuanDePuelenje.DTO.Query;

import com.ruta.sanJuanDePuelenje.DTO.RoleDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
	private RoleDTO role;
	//es necesario que al consultar me regrese el rol? o depende del rol persona que haga la consulta?
}
