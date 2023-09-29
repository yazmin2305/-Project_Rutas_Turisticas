package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.FincaDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.services.IFincaService;

@RestController
@RequestMapping("/finca")
@CrossOrigin("*")
public class FincaController {

	@Autowired
	private IFincaService iFincaService;

	// Consultar todas las fincas
	@GetMapping("/ConsultAllFincas")
	//@RequestMapping(value = {"/ConsultAllFincas", "/"} , method = RequestMethod.GET )
	public Response<List<FincaDTO>> ConsultAllFincas() {
		return this.iFincaService.findAllFincas();
	}

	// Consultar finca por id
	@GetMapping("/ConsultById/{id}")
	public Response<FincaDTO> ConsultFincaById(@PathVariable Integer id) {
		return this.iFincaService.findByFincaId(id);
	}

	// Guardar finca
	@PostMapping("/SaveFinca")
	public Response<FincaDTO> SaveFinca(@RequestBody FincaDTO finca) {
		return this.iFincaService.saveFinca(finca);
	}

	// Actualizar finca
	@PutMapping("/UpdateFinca/{id}")
	public Response<FincaDTO> UpdateFinca(@RequestBody FincaDTO finca, @PathVariable Integer id) {
		return this.iFincaService.updateFinca(id, finca);
	}

	// Desabilitar una finca registrada en el sistema
	@PutMapping("/DisableFinca/{id}")
	public Response<Boolean> DisableFinca(@PathVariable Integer id) {
		return this.iFincaService.disableFinca(id);
	}

	// Consultar las fincas dependiento su estado: activado - desactivado
	@GetMapping("ConsultAllFincaByState/{state}")
	public Response<List<FincaDTO>> ConsultAllFincaByState(@PathVariable Boolean state) {
		return this.iFincaService.findAllFincaBytState(state);
	}
}
