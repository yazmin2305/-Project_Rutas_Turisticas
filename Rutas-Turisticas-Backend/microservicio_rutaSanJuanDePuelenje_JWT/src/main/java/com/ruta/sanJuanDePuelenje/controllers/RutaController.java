package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.RutaCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.RutaQueryDTO;
import com.ruta.sanJuanDePuelenje.services.IRutaService;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

@RestController
@RequestMapping("/ruta")
@CrossOrigin("*")
public class RutaController {
	
	@Autowired
	private IRutaService iRutaService;
	
	// Consultar todas las rutas
	@GetMapping("/ConsultAllRutas")
	public Response<List<RutaQueryDTO>> ConsultAllRecreation() {
		return this.iRutaService.findAllRutas();
	}
	
	// Consultar rutas por id
	@Secured("SUPER")
	@GetMapping("/ConsultRutaById/{id}")
	public Response<RutaQueryDTO> ConsultRutaById(@PathVariable Integer id){
		return this.iRutaService.findByRutaId(id);
	}
	
	// Guardar ruta
	@Secured("SUPER")
	@PostMapping("/SaveRuta")
	public Response<RutaCommandDTO> SaveRuta(@RequestBody RutaCommandDTO ruta){
		return this.iRutaService.saveRuta(ruta);
	}
	
	// Actualizar una ruta
	@Secured("SUPER")
	@PatchMapping("/UpdateRuta/{id}")
	public Response<RutaCommandDTO> UpdateRuta(@RequestBody RutaCommandDTO Ruta, @PathVariable Integer id) {
		return this.iRutaService.updateRuta(id, Ruta);
	}
	
	// Desabilitar una ruta registrada en el sistema
	@Secured("SUPER")
	@PatchMapping("/DisableRuta/{id}")
	public Response<Boolean> DisableRuta(@PathVariable Integer id) {
		return this.iRutaService.disableRuta(id);
	}
	
	// Habilitar una ruta registrada en el sistema
	@Secured("SUPER")
	@PatchMapping("/EnableRuta/{id}")
	public Response<Boolean> EnableRuta(@PathVariable Integer id) {
		return this.iRutaService.enableRuta(id);
	}
	
	// Consultar las rutas dependiento su estado: activado - desactivado
	@Secured("SUPER")
	@GetMapping("/ConsultAllRutasByState/{state}")
	public ResponseEntity<GenericPageableResponse> ConsultAllRutaByState(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order, @PathVariable Boolean state){
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK).body(this.iRutaService.findAllRutasBytState(state, pageable));
	}
}
