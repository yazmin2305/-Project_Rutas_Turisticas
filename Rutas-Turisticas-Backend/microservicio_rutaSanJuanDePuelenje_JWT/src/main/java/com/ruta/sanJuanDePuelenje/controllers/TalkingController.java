package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.TalkingCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.TalkingQueryDTO;
import com.ruta.sanJuanDePuelenje.services.ITalkingService;

@RestController
@RequestMapping("/talking")
@CrossOrigin("*")
public class TalkingController {
	
	@Autowired
	private ITalkingService iTalkingService;

	// Consultar todas las charlas
	@GetMapping("/ConsultAllTalking")
	public Response<List<TalkingQueryDTO>> ConsultAllTalking() {
		return this.iTalkingService.findAllTalking();
	}

	// Consultar una charla por su id
	@Secured({"ADMIN", "USER"})
	@GetMapping("/ConsultById/{id}")
	public Response<TalkingQueryDTO> ConsultTalkingById(@PathVariable Integer id) {
		return this.iTalkingService.findByTalkingId(id);
	}

	// Guardar una charla
	@Secured("ADMIN")
	@PostMapping("/SaveTalking")
	public Response<TalkingCommandDTO> SaveTalking(@RequestBody TalkingCommandDTO Talking) {
		return this.iTalkingService.saveTalking(Talking);
	}

	// Actualizar una charla
	@Secured("ADMIN")
	@PatchMapping("/UpdateTalking/{id}")
	public Response<TalkingQueryDTO> UpdateTalking(@RequestBody TalkingCommandDTO Talking, @PathVariable Integer id) {
		return this.iTalkingService.updateTalking(id, Talking);
	}

	// Desabilitar una charla registrada en el sistema
	@Secured("ADMIN")
	@PatchMapping("/DisableTalking/{id}")
	public Response<Boolean> DisableTalking(@PathVariable Integer id) {
		return this.iTalkingService.disableTalking(id);
	}
	
	//Consultar todas las charlas deshabilitadas
	@Secured({"ADMIN", "USER"})
	@GetMapping("ConsultAllTalkingDisabled")
	public Response<List<TalkingQueryDTO>> ConsultAllTalkingDisabled(){
		return this.iTalkingService.findAllTalkingDisabled();
	}
	
	//Consultar las charlas dependiento su estado: activado - desactivado
	@Secured({"ADMIN", "USER"})
	@GetMapping("ConsultAllTalkingByState/{state}")
	public Response<List<TalkingQueryDTO>> ConsultAllTalkingByState(@PathVariable Boolean state){
		return this.iTalkingService.findAllTalkingBytState(state);
	}
	
}
