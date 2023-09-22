package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ruta.sanJuanDePuelenje.DTO.FestivalDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.services.IFestivalService;

@RestController
@RequestMapping("/festival")
@CrossOrigin("*")
public class FestivalController {

	@Autowired
	private IFestivalService iFestivalService;

	// Consultar todos los festivales
	@GetMapping("/ConsultAllFestival")
	public Response<List<FestivalDTO>> ConsultAllFestival() {
		return this.iFestivalService.findAllFestival();
	}

	// Consultar festival por id
	@GetMapping("/ConsultById/{id}")
	public Response<FestivalDTO> ConsultFestivalById(@PathVariable Integer id) {
		return this.iFestivalService.findByFestivalId(id);
	}

	// Guardar festival
	@PostMapping("/SaveFestival")
	public Response<FestivalDTO> SaveFestival(@RequestBody FestivalDTO festival, @RequestParam("file") MultipartFile imagen) {
		return this.iFestivalService.saveFestival(festival, imagen);
	}

	// Actualizar festival
	@PutMapping("/UpdateFestival/{id}")
	public Response<FestivalDTO> UpdateFestival(@RequestBody FestivalDTO festival, @PathVariable Integer id) {
		return this.iFestivalService.updateFestival(id, festival);
	}

	// Desabilitar un festival registrado en el sistema
	@PutMapping("/DisableFestival/{id}")
	public Response<Boolean> DisableFestival(@PathVariable Integer id) {
		return this.iFestivalService.disableFestival(id);
	}

	// Consultar los festivales dependiento su estado: activado - desactivado
	@GetMapping("ConsultAllFestivalByState/{state}")
	public Response<List<FestivalDTO>> ConsultAllFestivalByState(@PathVariable Boolean state) {
		return this.iFestivalService.findAllFestivalBytState(state);
	}
}
