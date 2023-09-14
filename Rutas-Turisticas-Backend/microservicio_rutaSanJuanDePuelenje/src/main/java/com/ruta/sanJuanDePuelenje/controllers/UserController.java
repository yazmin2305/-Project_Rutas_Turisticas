package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> ConsultAllUsers() {
		List<UserDTO> lstUsers = this.iUserService.findAllUsers();
		if(!lstUsers.isEmpty()) {
			return ResponseEntity.ok(lstUsers);
		}else {
			
//			new ResponseEntity<>(
//			          "Year of birth cannot be in the future", 
//			          HttpStatus.BAD_REQUEST);\
			//String mensaje = "¡Usuarios no encontrados!";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("¡Usuarios no encontrados!");
		}
	}

	// Consultar usuario por id
	@GetMapping("/ConsultById/{id}")
	public ResponseEntity<?> ConsultUserById(@PathVariable Integer id) {
		UserDTO user = this.iUserService.findByUserId(id);
		System.out.println("aqui no llega");
		if(user != null) {
			return ResponseEntity.ok(user);
		}else {
			String mensaje = "Usuario no encontrado";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
		}
	}

	// Guardar usuario
	@PostMapping("/SaveUser")
	public ResponseEntity<String> SaveUser(@RequestBody UserDTO user) {
		UserDTO saveUser = this.iUserService.saveUser(user);
		if(saveUser != null) {
			String mensaje = "Usuario guardado correctamente";
			return ResponseEntity.ok(mensaje);
		}else {
			String mensaje = "Error al guardar el usuario";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
		}
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
