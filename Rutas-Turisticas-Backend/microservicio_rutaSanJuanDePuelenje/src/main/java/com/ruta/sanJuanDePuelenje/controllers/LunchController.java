package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.LunchDTO;
import com.ruta.sanJuanDePuelenje.services.ILunchService;

@RestController
@RequestMapping("/lunch")
@CrossOrigin("*")
public class LunchController {
	
	@Autowired
	private ILunchService iLunchService;

	// Consultar todos los almuerzos
	@GetMapping("/ConsultAllLunch")
	public List<LunchDTO> ConsultAllLunch() {
		return this.iLunchService.findAllLunch();
	}

	// Consultar un almuerzo por id
	@GetMapping("/ConsultById/{id}")
	public LunchDTO ConsultLunchById(@PathVariable Integer id) {
		return this.iLunchService.findByLunchId(id);
	}

	// Guardar almuerzo
	@PostMapping("/SaveLunch")
	public LunchDTO SaveLunch(@RequestBody LunchDTO lunch) {
		return this.iLunchService.saveLunch(lunch);
	}

	// Actualizar almuerzo
	@PutMapping("/UpdateLunch/{id}")
	public LunchDTO UpdateLunch(@RequestBody LunchDTO lunch, @PathVariable Integer id) {
		return this.iLunchService.updateLunch(id, lunch);
	}

	// Desabilitar un almuerzo registrado en el sistema
	@PutMapping("/DisableLunch/{id}")
	public Boolean DisableLunch(@PathVariable Integer id) {
		return this.iLunchService.disableLunch(id);
	}
}
