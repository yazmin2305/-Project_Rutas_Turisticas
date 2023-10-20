package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.RoleDTO;
import com.ruta.sanJuanDePuelenje.DTO.UserDTO;
import com.ruta.sanJuanDePuelenje.services.IUserService;

import jakarta.annotation.security.PermitAll;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private IUserService iUserService;

	// Consultar todos los usuarios
	@Secured("ADMIN")
	@GetMapping("/ConsultAllUsers")
	public Response<List<UserDTO>> ConsultAllUsers() {
		return this.iUserService.findAllUsers();
	}

	// Consultar usuario por id
	@Secured("ADMIN")
	@GetMapping("/ConsultById/{id}")
	public Response<UserDTO> ConsultUserById(@PathVariable Integer id) {
		return this.iUserService.findByUserId(id);
	}

	// Guardar usuario
	@PermitAll
	@PostMapping("/SaveUser")
	public Response<UserDTO> SaveUser(@RequestBody UserDTO user) {
		return this.iUserService.saveUser(user);
	}

	// Actualizar usuario
	@Secured({"ADMIN", "USER"})
	@PutMapping("/UpdateUser/{id}")
	public Response<UserDTO> UpdateUser(@RequestBody UserDTO user, @PathVariable Integer id) {
		return this.iUserService.updateUser(id, user);
	}

	// Desabilitar un usuario registrado en el sistema
	@Secured("ADMIN")
	@PutMapping("/DisableUser/{id}")
	public Response<Boolean> DisableUser(@PathVariable Integer id) {
		return this.iUserService.disableUser(id);
	}

	// Consultar los usuarios dependiento su estado: activado - desactivado
	@Secured("ADMIN")
	@GetMapping("ConsultAllUsersByState/{state}")
	public Response<List<UserDTO>> ConsultAllUsersByState(@PathVariable Boolean state) {
		return this.iUserService.findAllUserBytState(state);
	}

	@Secured("SUPER")
	@PutMapping("/changeRolUser/{id}")
	public Response<Boolean> changeRolUser(@PathVariable Integer id, @RequestBody RoleDTO role) {
		return this.iUserService.changeRolUser(id, role);
	}
}
