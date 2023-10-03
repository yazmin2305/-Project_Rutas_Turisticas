package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ruta.sanJuanDePuelenje.DTO.FestivalDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.services.IFestivalService;

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
	public Response<List<FestivalDTO>> ConsultAllFestival() {
		return this.iFestivalService.findAllFestival();
	}

	// Consultar festival por id
	//las consultas por id para que rol son?
	@Secured({"ADMIN", "USER"})
	@GetMapping("/ConsultById/{id}")
	public Response<FestivalDTO> ConsultFestivalById(@PathVariable Integer id) {
		return this.iFestivalService.findByFestivalId(id);
	}

	// Guardar festival
	@Secured("ADMIN")
	@PostMapping("/SaveFestival")
	public Response<FestivalDTO> SaveFestival(@RequestBody FestivalDTO festival, @RequestParam("file") MultipartFile imagen) {
		return this.iFestivalService.saveFestival(festival, imagen);
	}

	// Actualizar festival
	@Secured("ADMIN")
	@PutMapping("/UpdateFestival/{id}")
	public Response<FestivalDTO> UpdateFestival(@RequestBody FestivalDTO festival, @PathVariable Integer id) {
		return this.iFestivalService.updateFestival(id, festival);
	}

	// Desabilitar un festival registrado en el sistema
	@Secured("ADMIN")
	@PutMapping("/DisableFestival/{id}")
	public Response<Boolean> DisableFestival(@PathVariable Integer id) {
		return this.iFestivalService.disableFestival(id);
	}

	// Consultar los festivales dependiento su estado: activado - desactivado
	@Secured({"ADMIN", "USER"})
	@GetMapping("ConsultAllFestivalByState/{state}")
	public Response<List<FestivalDTO>> ConsultAllFestivalByState(@PathVariable Boolean state) {
		return this.iFestivalService.findAllFestivalBytState(state);
	}
}
