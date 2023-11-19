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
import com.ruta.sanJuanDePuelenje.DTO.Command.UserCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Command.UserPermissionsDTO;
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
	@Secured("SUPER")
	@GetMapping("/ConsultById/{id}")
	public Response<UserQueryDTO> ConsultUserById(@PathVariable Integer id) {
		return this.iUserService.findByUserId(id);
	}

	// Consultar usuario por id
	@Secured({ "ADMIN", "USER" })
	@GetMapping("/ConsultUserByEmail/{email}")
	public Response<UserCommandDTO> ConsultUserByEmail(@PathVariable String email) {
		return this.iUserService.findUserByEmail(email);
	}

	// Guardar usuario
	@PermitAll
	@PostMapping("/SaveUser")
	public Response<UserCommandDTO> SaveUser(@RequestBody UserCommandDTO user) {
		return this.iUserService.saveUser(user);
	}

	// Actualizar usuario
	@Secured({ "SUPER", "USER" })
	@PatchMapping("/UpdateUser/{id}")
	public Response<UserQueryDTO> UpdateUser(@RequestBody UserCommandDTO user, @PathVariable Integer id) {
		return this.iUserService.updateUser(id, user);
	}

	// Desabilitar un usuario registrado en el sistema
	@Secured("SUPER")
	@PatchMapping("/DisableUser/{id}")
	public Response<Boolean> DisableUser(@PathVariable Integer id) {
		return this.iUserService.disableUser(id);
	}

	// Habilitar un usuario registrado en el sistema
	@Secured("SUPER")
	@PatchMapping("/EnableUser/{id}")
	public Response<Boolean> EnableUser(@PathVariable Integer id) {
		return this.iUserService.enableUser(id);
	}

	// Consultar los usuarios dependiento su estado: activado - desactivado
	@Secured("SUPER")
	@GetMapping("ConsultAllUsersByState/{state}")
	public ResponseEntity<GenericPageableResponse> ConsultAllUsersByState(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order,
			@PathVariable Boolean state) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK).body(this.iUserService.findAllUserBytState(state, pageable));
	}

	// Cambiar el rol de un usuario normal a administrador
	@Secured("SUPER")
	@PatchMapping("/changePermissions")
	public Response<Boolean> changePermissions(@RequestBody UserPermissionsDTO userPermissionsDTO) {
		return this.iUserService.changePermissions(userPermissionsDTO);
	}

	// Consultar los usuarios que hayan realizado reservas en determinada ruta
	@Secured({ "SUPER", "ADMIN" })
	@GetMapping("/ConsultAllUsersByRuta/{idRuta}")
	public GenericPageableResponse ConsultAllUsersByRuta(@PathVariable Integer idRuta,@RequestParam Integer page, @RequestParam Integer size,
			@RequestParam String sort, @RequestParam String order) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return this.iUserService.findAllUsersByRuta(idRuta, pageable);
	}
}
