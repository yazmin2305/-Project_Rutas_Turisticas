package com.ruta.sanJuanDePuelenje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.RoleCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Command.UserCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.UserQueryDTO;
import com.ruta.sanJuanDePuelenje.services.IUserService;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

import jakarta.annotation.security.PermitAll;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private IUserService iUserService;

	// Consultar todos los usuarios
	@Secured("SUPER")
	@GetMapping("/ConsultAllUsers")
	public ResponseEntity<GenericPageableResponse> ConsultAllUsers(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK).body(this.iUserService.findAllUsers(pageable));
	}

	// Consultar usuario por id
	@Secured("ADMIN")
	@GetMapping("/ConsultById/{id}")
	public Response<UserQueryDTO> ConsultUserById(@PathVariable Integer id) {
		return this.iUserService.findByUserId(id);
	}

	// Guardar usuario
	@PermitAll
	@PostMapping("/SaveUser")
	public Response<UserCommandDTO> SaveUser(@RequestBody UserCommandDTO user) {
		return this.iUserService.saveUser(user);
	}

	// Actualizar usuario
	@Secured({ "ADMIN", "USER" })
	@PatchMapping("/UpdateUser/{id}")
	public Response<UserQueryDTO> UpdateUser(@RequestBody UserCommandDTO user, @PathVariable Integer id) {
		return this.iUserService.updateUser(id, user);
	}

	// Desabilitar un usuario registrado en el sistema
	@Secured("ADMIN")
	@PatchMapping("/DisableUser/{id}")
	public Response<Boolean> DisableUser(@PathVariable Integer id) {
		return this.iUserService.disableUser(id);
	}

	// Habilitar un usuario registrado en el sistema
	@Secured("ADMIN")
	@PatchMapping("/EnableUser/{id}")
	public Response<Boolean> EnableUser(@PathVariable Integer id) {
		return this.iUserService.enableUser(id);
	}

	// Consultar los usuarios dependiento su estado: activado - desactivado
	@Secured("ADMIN")
	@GetMapping("ConsultAllUsersByState/{state}")
	public ResponseEntity<GenericPageableResponse> ConsultAllUsersByState(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order,
			@PathVariable Boolean state) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK).body(this.iUserService.findAllUserBytState(state, pageable));
	}
	
	//Cambiar el rol de un usuario normal a administrador
	@Secured("SUPER")
	@PutMapping("/changeRolUser/{id}")
	public Response<Boolean> changeRolUser(@PathVariable Integer id, @RequestBody RoleCommandDTO role) {
		return this.iUserService.changeRolUser(id, role);
	}
}
