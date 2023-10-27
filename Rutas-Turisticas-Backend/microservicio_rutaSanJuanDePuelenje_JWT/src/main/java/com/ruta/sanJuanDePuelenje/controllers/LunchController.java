package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.LunchCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.LunchQueryDTO;
import com.ruta.sanJuanDePuelenje.services.ILunchService;

@RestController
@RequestMapping("/lunch")
@CrossOrigin("*")
public class LunchController {

	@Autowired
	private ILunchService iLunchService;

	// Consultar todos los almuerzos
	@GetMapping("/ConsultAllLunch")
	public Response<List<LunchQueryDTO>> ConsultAllLunch() {
		return this.iLunchService.findAllLunch();
	}

	// Consultar un almuerzo por id
	@Secured({"ADMIN", "USER"})
	@GetMapping("/ConsultById/{id}")
	public Response<LunchQueryDTO> ConsultLunchById(@PathVariable Integer id) {
		return this.iLunchService.findByLunchId(id);
	}

	// Guardar almuerzo
	@Secured("ADMIN")
	@PostMapping("/SaveLunch")
	public Response<LunchCommandDTO> SaveLunch(@RequestBody LunchCommandDTO lunch) {
		return this.iLunchService.saveLunch(lunch);
	}

	// Actualizar almuerzo
	@Secured("ADMIN")
	@PatchMapping("/UpdateLunch/{id}")
	public Response<LunchQueryDTO> UpdateLunch(@RequestBody LunchCommandDTO lunch, @PathVariable Integer id) {
		return this.iLunchService.updateLunch(id, lunch);
	}

	// Desabilitar un almuerzo registrado en el sistema
	@Secured("ADMIN")
	@PatchMapping("/DisableLunch/{id}")
	public Response<Boolean> DisableLunch(@PathVariable Integer id) {
		return this.iLunchService.disableLunch(id);
	}

	// Consultar los almuerzos dependiento su estado: activado - desactivado
	@Secured({"ADMIN", "USER"})
	@GetMapping("ConsultAllLunchByState/{state}")
	public Response<List<LunchQueryDTO>> ConsultAllLunchByState(@PathVariable Boolean state) {
		return this.iLunchService.findAllLunchBytState(state);
	}
}
