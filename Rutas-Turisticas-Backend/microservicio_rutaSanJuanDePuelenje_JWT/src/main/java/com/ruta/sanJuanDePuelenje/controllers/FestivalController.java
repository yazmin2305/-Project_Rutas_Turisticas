package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.FestivalCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Command.RutaCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.FestivalQueryDTO;
import com.ruta.sanJuanDePuelenje.services.IFestivalService;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

import org.springframework.http.HttpStatus;

import jakarta.annotation.security.PermitAll;

@RestController
@RequestMapping("/festival")
@CrossOrigin("*")
public class FestivalController {

	@Autowired
	private IFestivalService iFestivalService;

	// Consultar todos los festivales
	@PermitAll
	@GetMapping("/ConsultAllFestival")
	public Response<List<FestivalQueryDTO>> ConsultAllFestival() {
		return this.iFestivalService.findAllFestival();
	}

	// Consultar todos los festivales por ruta
	@GetMapping("/ConsultAllFestivalesByRuta")
	@Secured("ADMIN")
	public ResponseEntity<GenericPageableResponse> ConsultAllFestivalByRuta(RutaCommandDTO ruta, @RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.iFestivalService.findAllFestivalBytRuta(ruta, pageable));
	}

	// Consultar festival por id
	@Secured({ "ADMIN", "USER" })
	@GetMapping("/ConsultById/{id}")
	public Response<FestivalQueryDTO> ConsultFestivalById(@PathVariable Integer id) {
		return this.iFestivalService.findByFestivalId(id);
	}

	// Guardar festival
	@Secured("ADMIN")
	@PostMapping("/SaveFestival")
	public Response<FestivalCommandDTO> SaveFestival(@RequestBody FestivalCommandDTO festival) {
		return this.iFestivalService.saveFestival(festival);
	}

	// Actualizar festival
	@Secured("ADMIN")
	@PatchMapping("/UpdateFestival/{id}")
	public Response<FestivalQueryDTO> UpdateFestival(@RequestBody FestivalCommandDTO festival,
			@PathVariable Integer id) {
		return this.iFestivalService.updateFestival(id, festival);
	}

	// Desabilitar un festival registrado en el sistema
	@Secured("ADMIN")
	@PatchMapping("/DisableFestival/{id}")
	public Response<Boolean> DisableFestival(@PathVariable Integer id) {
		return this.iFestivalService.disableFestival(id);
	}

	// Habilitar un festival registrado en el sistema
	@Secured("ADMIN")
	@PatchMapping("/EnableFestival/{id}")
	public Response<Boolean> EnableFestival(@PathVariable Integer id) {
		return this.iFestivalService.enableFestival(id);
	}

	// Consultar los festivales dependiento su estado: activado - desactivado
	@Secured({ "ADMIN", "USER" })
	@GetMapping("ConsultAllFestivalByState/{state}")
	public ResponseEntity<GenericPageableResponse> ConsultAllFestivalByState(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order,
			@PathVariable Boolean state) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.iFestivalService.findAllFestivalBytState(state, pageable));
	}
}
