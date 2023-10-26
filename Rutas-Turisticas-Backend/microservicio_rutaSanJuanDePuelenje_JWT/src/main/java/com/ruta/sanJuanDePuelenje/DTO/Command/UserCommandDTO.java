package com.ruta.sanJuanDePuelenje.DTO.Command;

import com.ruta.sanJuanDePuelenje.DTO.RoleDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCommandDTO {
	private String userIdentification;
	private String userName;
	private String userLastName;
	private String userPhone;
	private String userEmail;
	private Boolean userState;
	private String userPassword;
	private RoleDTO role;
	//private List<ReserveCommandDTO> lstReserveDTOs;
	private RutaCommandDTO ruta;
}
