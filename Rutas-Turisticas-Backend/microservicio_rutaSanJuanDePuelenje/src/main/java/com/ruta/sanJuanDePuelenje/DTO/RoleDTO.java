package com.ruta.sanJuanDePuelenje.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
	private Integer roleId;
	private String roleName;
	private UserDTO userDTO;
}
