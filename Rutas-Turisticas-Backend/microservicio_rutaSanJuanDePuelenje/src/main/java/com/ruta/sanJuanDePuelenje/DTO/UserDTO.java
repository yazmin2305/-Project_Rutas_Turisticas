package com.ruta.sanJuanDePuelenje.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private Integer userId;
	private String userIdentification;
	private String userName;
	private String userLastName;
	private String userPhone;
	private String userEmail;
	private Boolean userState;
	private String userPassword;
	private RoleDTO roleDTO;
	private List<ReserveDTO> lstReserveDTOs;
}
