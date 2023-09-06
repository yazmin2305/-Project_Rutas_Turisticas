package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.FestivalDTO;
import com.ruta.sanJuanDePuelenje.services.IFestivalService;

@RestController
@RequestMapping("/festival")
@CrossOrigin("*")
public class FestivalController {
	
	@Autowired
	private IFestivalService iFestivalService;
	
	//Consultar todos los festivales
	@GetMapping("/ConsultAllFestival")
	public List<FestivalDTO> ConsultAllFestival() {
        return this.iFestivalService.findAllFestival();
    }
	
	//Consultar festival por id
	@GetMapping("/ConsultById/{id}")
	public FestivalDTO ConsultFestivalById(@PathVariable Integer id) {
		return this.iFestivalService.findByFestivalId(id);
	}
	
	//Guardar festival
	@PostMapping("/SaveFestival")
	public FestivalDTO SaveFestival(@RequestBody FestivalDTO festival) {
		return this.iFestivalService.saveFestival(festival);
	}
	
	//Actualizar festival
	@PutMapping("/UpdateFestival/{id}")
	public FestivalDTO UpdateFestival(@RequestBody FestivalDTO festival, @PathVariable Integer id) {
		return this.iFestivalService.updateFestival(id, festival);
	}
	
	//Desabilitar un festival registrado en el sistema
	@PutMapping("/DisableFestival/{id}")
	public Boolean DisableFestival(@PathVariable Integer id) {
		return this.iFestivalService.disableFestival(id);
	}
	
	
}
