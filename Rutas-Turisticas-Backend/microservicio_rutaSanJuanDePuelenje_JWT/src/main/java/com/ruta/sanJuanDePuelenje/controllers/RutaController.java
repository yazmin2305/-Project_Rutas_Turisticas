package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.RutaQueryDTO;
import com.ruta.sanJuanDePuelenje.DTO.RutaCommandDTO;
import com.ruta.sanJuanDePuelenje.services.IRutaService;

@RestController
@RequestMapping("/ruta")
@CrossOrigin("*")
public class RutaController {
	
	@Autowired
	private IRutaService iRutaService;
	
	@GetMapping("/ConsultAllRutas")
	public Response<List<RutaQueryDTO>> ConsultAllRutas(){
		return this.iRutaService.findAllRutas();
	}
	@Secured("SUPER")
	@GetMapping("/ConsultRutaById/{id}")
	public Response<RutaQueryDTO> ConsultRutaById(@PathVariable Integer id){
		return this.iRutaService.findByRutaId(id);
	}
	
	@Secured("SUPER")
	@PostMapping("/SaveRuta")
	public Response<RutaCommandDTO> SaveRuta(@RequestBody RutaCommandDTO ruta){
		return this.iRutaService.saveRuta(ruta);
	}
	
	@Secured("SUPER")
	@PutMapping("/UpdateRuta/{id}")
	public Response<RutaCommandDTO> UpdateRuta(@RequestBody RutaCommandDTO Ruta, @PathVariable Integer id) {
		return this.iRutaService.updateRuta(id, Ruta);
	}
	
	@Secured("SUPER")
	@PutMapping("/DisableTalking/{id}")
	public Response<Boolean> DisableTalking(@PathVariable Integer id) {
		return this.iRutaService.disableRuta(id);
	}
	
	@Secured("SUPER")
	@GetMapping("/ConsultAllRutasByState/{state}")
	public Response<List<RutaQueryDTO>> ConsultAllRutaByState(@PathVariable Boolean state){
		return this.iRutaService.findAllRutasBytState(state);
	}
}
