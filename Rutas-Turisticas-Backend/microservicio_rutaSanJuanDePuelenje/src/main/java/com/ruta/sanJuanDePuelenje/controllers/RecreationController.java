package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.RecreationDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.services.IRecreationService;

@RestController
@RequestMapping("/recreation")
@CrossOrigin("*")
public class RecreationController {

	@Autowired
	private IRecreationService iRecreationService;

	// Consultar todos las actividades de recreación
	@GetMapping("/ConsultAllRecreation")
	public Response<List<RecreationDTO>> ConsultAllRecreation() {
		return this.iRecreationService.findAllRecreation();
	}

	// Consultar una actividad recreativa por su id
	@GetMapping("/ConsultById/{id}")
	public Response<RecreationDTO> ConsultRecreationById(@PathVariable Integer id) {
		return this.iRecreationService.findByRecreationId(id);
	}

	// Guardar una actividad recreativa
	@PostMapping("/SaveRecreation")
	public Response<RecreationDTO> SaveRecreation(@RequestBody RecreationDTO recreation) {
		return this.iRecreationService.saveRecreation(recreation);
	}

	// Actualizar una actividad recreativa
	@PutMapping("/UpdateRecreation/{id}")
	public Response<RecreationDTO> UpdateRecreation(@RequestBody RecreationDTO recreation, @PathVariable Integer id) {
		return this.iRecreationService.updateRecreation(id, recreation);
	}

	// Desabilitar una actividad recreativa registrada en el sistema
	@PutMapping("/DisableRecreation/{id}")
	public Response<Boolean> DisableRecreation(@PathVariable Integer id) {
		return this.iRecreationService.disableRecreation(id);
	}

	// Consultar las actividades de recreacion dependiento su estado: activado - desactivado
	@GetMapping("ConsultAllRecreationByState/{state}")
	public Response<List<RecreationDTO>> ConsultAllRecreationByState(@PathVariable Boolean state) {
		return this.iRecreationService.findAllRecreationBytState(state);
	}
}
