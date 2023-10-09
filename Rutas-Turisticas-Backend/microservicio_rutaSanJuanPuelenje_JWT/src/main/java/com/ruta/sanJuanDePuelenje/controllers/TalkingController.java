package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.TalkingDTO;
import com.ruta.sanJuanDePuelenje.services.ITalkingService;

import jakarta.annotation.security.PermitAll;

@RestController
@RequestMapping("/talking")
@CrossOrigin("*")
public class TalkingController {
	
	@Autowired
	private ITalkingService iTalkingService;

	// Consultar todas las charlas
	@PermitAll
	@GetMapping("/ConsultAllTalking")
	public Response<List<TalkingDTO>> ConsultAllTalking() {
		return this.iTalkingService.findAllTalking();
	}

	// Consultar una charla por su id
	@Secured({"ADMIN", "USER"})
	@GetMapping("/ConsultById/{id}")
	public Response<TalkingDTO> ConsultTalkingById(@PathVariable Integer id) {
		return this.iTalkingService.findByTalkingId(id);
	}

	// Guardar una charla
	@Secured("ADMIN")
	@PostMapping("/SaveTalking")
	public Response<TalkingDTO> SaveTalking(@RequestBody TalkingDTO Talking) {
		return this.iTalkingService.saveTalking(Talking);
	}

	// Actualizar una charla
	@Secured("ADMIN")
	@PutMapping("/UpdateTalking/{id}")
	public Response<TalkingDTO> UpdateTalking(@RequestBody TalkingDTO Talking, @PathVariable Integer id) {
		return this.iTalkingService.updateTalking(id, Talking);
	}

	// Desabilitar una charla registrada en el sistema
	@Secured("ADMIN")
	@PutMapping("/DisableTalking/{id}")
	public Response<Boolean> DisableTalking(@PathVariable Integer id) {
		return this.iTalkingService.disableTalking(id);
	}
	
	//Consultar todas las charlas deshabilitadas
	@Secured({"ADMIN", "USER"})
	@GetMapping("ConsultAllTalkingDisabled")
	public Response<List<TalkingDTO>> ConsultAllTalkingDisabled(){
		return this.iTalkingService.findAllTalkingDisabled();
	}
	
	//Consultar las charlas dependiento su estado: activado - desactivado
	@Secured({"ADMIN", "USER"})
	@GetMapping("ConsultAllTalkingByState/{state}")
	public Response<List<TalkingDTO>> ConsultAllTalkingByState(@PathVariable Boolean state){
		return this.iTalkingService.findAllTalkingBytState(state);
	}
	
}
