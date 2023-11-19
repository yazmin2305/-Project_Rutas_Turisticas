package com.ruta.sanJuanDePuelenje.DTO.Command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionsDTO {
	/**
	 * Esta clase va a permitir que cuando el super usuario quiera cambiar el rol de
	 * un usuario a administrador de una vez le envie la ruta asignada a este usuario
	 */
	private Integer idUser;
	private RoleCommandDTO role;
	private RutaCommandDTO ruta;
	
}
