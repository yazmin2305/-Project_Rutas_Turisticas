package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.TalkingCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.TalkingQueryDTO;
import com.ruta.sanJuanDePuelenje.services.ITalkingService;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

@RestController
@RequestMapping("/talking")
@CrossOrigin("*")
public class TalkingController {

	@Autowired
	private ITalkingService iTalkingService;

	// Consultar todas las charlas
	@GetMapping("/ConsultAllTalking")
	public Response<List<TalkingCommandDTO>> ConsultAllTalking() {
		return this.iTalkingService.findAllTalking();
	}

	// Consultar todas las charlas por ruta
	@Secured({ "ADMIN", "SUPER" })
	@GetMapping("/ConsultAllTalkingByRuta/{rutaId}")
	public Response<List<TalkingQueryDTO>> ConsultAllTalkingByRuta(@PathVariable Integer rutaId) {
		return this.iTalkingService.findAllTalkingBytRuta(rutaId);
	}

	// Consultar una charla por su id
	@Secured({ "ADMIN", "SUPER" })
	@GetMapping("/ConsultById/{id}")
	public Response<TalkingCommandDTO> ConsultTalkingById(@PathVariable Integer id) {
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

	// Habilitar una charla registrada en el sistema
	@Secured("ADMIN")
	@PatchMapping("/EnableTalking/{id}")
	public Response<Boolean> EnableTalking(@PathVariable Integer id) {
		return this.iTalkingService.enableTalking(id);
	}

	// Consultar las charlas dependiento su estado: activado - desactivado
	@Secured("SUPER")
	@GetMapping("ConsultAllTalkingByState/{state}")
	public ResponseEntity<GenericPageableResponse> ConsultAllTalkingByState(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order,
			@PathVariable Boolean state) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK).body(this.iTalkingService.findAllTalkingBytState(state, pageable));
	}

	// Consultar todas las charlas su estado y por la ruta a la cual estan relacionadas
	@Secured({ "ADMIN", "SUPER" })
	@GetMapping("/ConsultAllTalkingByStateByRuta/{state}/{rutaId}")
	public Response<List<TalkingQueryDTO>> ConsultAllTalkingByStateByRuta(@PathVariable Boolean state,
			@PathVariable Integer rutaId) {
		return this.iTalkingService.findTalkingByStateByRuta(state, rutaId);
	}

}
