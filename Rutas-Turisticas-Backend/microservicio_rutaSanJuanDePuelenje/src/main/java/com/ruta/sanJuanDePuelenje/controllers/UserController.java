package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.UserDTO;
import com.ruta.sanJuanDePuelenje.services.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private IUserService iUserService;

	// Consultar todos los usuarios
	@GetMapping("/ConsultAllUsers")
	public List<UserDTO> ConsultAllUsers() {
		return this.iUserService.findAllUsers();
	}

	// Consultar usuario por id
	@GetMapping("/ConsultById/{id}")
	public UserDTO ConsultUserById(@PathVariable Integer id) {
		return this.iUserService.findByUserId(id);
	}

	// Guardar usuario
	@PostMapping("/SaveUser")
	public UserDTO SaveUser(@RequestBody UserDTO user) {
		return this.iUserService.saveUser(user);
	}

	// Actualizar usuario
	@PutMapping("/UpdateUser/{id}")
	public UserDTO UpdateUser(@RequestBody UserDTO user, @PathVariable Integer id) {
		return this.iUserService.updateUser(id, user);
	}

	// Desabilitar un usuario registrado en el sistema
	@PutMapping("/DisableUser/{id}")
	public Boolean DisableUser(@PathVariable Integer id) {
		return this.iUserService.disableUser(id);
	}

	// Consultar los usuarios dependiento su estado: activado - desactivado
	@GetMapping("ConsultAllUserByState/{state}")
	public List<UserDTO> ConsultAllUserByState(@PathVariable Boolean state) {
		return this.iUserService.findAllUserBytState(state);
	}

}
