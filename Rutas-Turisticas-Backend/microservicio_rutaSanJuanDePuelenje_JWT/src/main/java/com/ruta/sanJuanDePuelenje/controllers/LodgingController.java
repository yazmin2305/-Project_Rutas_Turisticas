package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.LodgingCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.LodgingQueryDTO;
import com.ruta.sanJuanDePuelenje.services.ILodgingService;

@RestController
@RequestMapping("/lodging")
@CrossOrigin("*")
public class LodgingController {
	@Autowired
	private ILodgingService iLodgingService;

	// Consultar todos los hospedajes
	
	@GetMapping("/ConsultAllLodging")
	public Response<List<LodgingQueryDTO>> ConsultAllLodging() {
		return this.iLodgingService.findAllLodging();
	}

	// Consultar usuario por id
	@Secured({"ADMIN", "USER"})
	@GetMapping("/ConsultById/{id}")
	public Response<LodgingQueryDTO> ConsultLodgingById(@PathVariable Integer id) {
		return this.iLodgingService.findByLodgingId(id);
	}

	// Guardar usuario
	@Secured("ADMIN")
	@PostMapping("/SaveLodging")
	public Response<LodgingCommandDTO> SaveLodging(@RequestBody LodgingCommandDTO lodging) {
		return this.iLodgingService.saveLodging(lodging);
	}

	// Actualizar usuario
	@Secured("ADMIN")
	@PutMapping("/UpdateLodging/{id}")
	public Response<LodgingQueryDTO> UpdateLodging(@RequestBody LodgingCommandDTO lodging, @PathVariable Integer id) {
		return this.iLodgingService.updateLodging(id, lodging);
	}

	// Desabilitar un usuario registrado en el sistema
	@Secured("ADMIN")
	@PutMapping("/DisableLodging/{id}")
	public Response<Boolean> DisableLodging(@PathVariable Integer id) {
		return this.iLodgingService.disableLodging(id);
	}

	// Consultar los hospedajes dependiento su estado: activado - desactivado
	@Secured({"ADMIN", "USER"})
	@GetMapping("ConsultAllLodgingByState/{state}")
	public Response<List<LodgingQueryDTO>> ConsultAllLodgingByState(@PathVariable Boolean state) {
		return this.iLodgingService.findAllLodgingBytState(state);
	}
}
