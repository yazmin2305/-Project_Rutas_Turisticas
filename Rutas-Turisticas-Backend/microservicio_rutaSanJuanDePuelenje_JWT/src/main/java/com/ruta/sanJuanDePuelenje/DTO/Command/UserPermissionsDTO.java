package com.ruta.sanJuanDePuelenje.DTO.Command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionsDTO {
	/**
	 * Esta clase va a permitir al super usuario cambiar el rol de
	 * un usuario a administrador y de una vez asignarle la respectiva ruta
	 */
	private String email;
	private RoleCommandDTO role;
	private RutaCommandDTO ruta;
	
}
