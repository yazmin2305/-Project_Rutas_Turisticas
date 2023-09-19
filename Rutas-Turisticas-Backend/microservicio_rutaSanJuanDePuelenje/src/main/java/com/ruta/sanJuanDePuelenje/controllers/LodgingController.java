package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.LodgingDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.services.ILodgingService;

@RestController
@RequestMapping("/lodging")
@CrossOrigin("*")
public class LodgingController {
	@Autowired
	private ILodgingService iLodgingService;

	// Consultar todos los hospedajes
	@GetMapping("/ConsultAllLodging")
	public Response<List<LodgingDTO>> ConsultAllLodging() {
		return this.iLodgingService.findAllLodging();
	}

	// Consultar usuario por id
	@GetMapping("/ConsultById/{id}")
	public Response<LodgingDTO> ConsultLodgingById(@PathVariable Integer id) {
		return this.iLodgingService.findByLodgingId(id);
	}

	// Guardar usuario
	@PostMapping("/SaveLodging")
	public Response<LodgingDTO> SaveLodging(@RequestBody LodgingDTO lodging) {
		return this.iLodgingService.saveLodging(lodging);
	}

	// Actualizar usuario
	@PutMapping("/UpdateLodging/{id}")
	public Response<LodgingDTO> UpdateLodging(@RequestBody LodgingDTO lodging, @PathVariable Integer id) {
		return this.iLodgingService.updateLodging(id, lodging);
	}

	// Desabilitar un usuario registrado en el sistema
	@PutMapping("/DisableLodging/{id}")
	public Response<Boolean> DisableLodging(@PathVariable Integer id) {
		return this.iLodgingService.disableLodging(id);
	}

	// Consultar los hospedajes dependiento su estado: activado - desactivado
	@GetMapping("ConsultAllLodgingByState/{state}")
	public Response<List<LodgingDTO>> ConsultAllLodgingByState(@PathVariable Boolean state) {
		return this.iLodgingService.findAllLodgingBytState(state);
	}
}
