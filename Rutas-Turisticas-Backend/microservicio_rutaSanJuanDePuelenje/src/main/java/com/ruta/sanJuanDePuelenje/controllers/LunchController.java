package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.LunchDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.services.ILunchService;

@RestController
@RequestMapping("/lunch")
@CrossOrigin("*")
public class LunchController {

	@Autowired
	private ILunchService iLunchService;

	// Consultar todos los almuerzos
	@GetMapping("/ConsultAllLunch")
	public Response<List<LunchDTO>> ConsultAllLunch() {
		return this.iLunchService.findAllLunch();
	}

	// Consultar un almuerzo por id
	@GetMapping("/ConsultById/{id}")
	public Response<LunchDTO> ConsultLunchById(@PathVariable Integer id) {
		return this.iLunchService.findByLunchId(id);
	}

	// Guardar almuerzo
	@PostMapping("/SaveLunch")
	public Response<LunchDTO> SaveLunch(@RequestBody LunchDTO lunch) {
		return this.iLunchService.saveLunch(lunch);
	}

	// Actualizar almuerzo
	@PutMapping("/UpdateLunch/{id}")
	public Response<LunchDTO> UpdateLunch(@RequestBody LunchDTO lunch, @PathVariable Integer id) {
		return this.iLunchService.updateLunch(id, lunch);
	}

	// Desabilitar un almuerzo registrado en el sistema
	@PutMapping("/DisableLunch/{id}")
	public Response<Boolean> DisableLunch(@PathVariable Integer id) {
		return this.iLunchService.disableLunch(id);
	}

	// Consultar los almuerzos dependiento su estado: activado - desactivado
	@GetMapping("ConsultAllLunchByState/{state}")
	public Response<List<LunchDTO>> ConsultAllLunchByState(@PathVariable Boolean state) {
		return this.iLunchService.findAllLunchBytState(state);
	}
}
