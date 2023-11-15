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
import com.ruta.sanJuanDePuelenje.DTO.Command.LodgingCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.LodgingQueryDTO;
import com.ruta.sanJuanDePuelenje.services.ILodgingService;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

@RestController
@RequestMapping("/lodging")
@CrossOrigin("*")
public class LodgingController {
	@Autowired
	private ILodgingService iLodgingService;

	// Consultar todos los hospedajes
	@GetMapping("/ConsultAllLodging")
	public Response<List<LodgingCommandDTO>> ConsultAllLodging() {
		return this.iLodgingService.findAllLodging();
	}

	// Consultar todos los hospedajes por ruta
	@Secured({ "ADMIN", "SUPER" })
	@GetMapping("/ConsultAllLodgingByRuta/{rutaId}")
	public Response<List<LodgingQueryDTO>> ConsultAllLodgingByRuta(@PathVariable Integer rutaId){
		return this.iLodgingService.findAllLodgingBytRuta(rutaId);
	}

	// Consultar hospedaje por id
	@Secured({ "ADMIN", "SUPER" })
	@GetMapping("/ConsultById/{id}")
	public Response<LodgingCommandDTO> ConsultLodgingById(@PathVariable Integer id) {
		return this.iLodgingService.findByLodgingId(id);
	}

	// Guardar hospedaje
	@Secured("ADMIN")
	@PostMapping("/SaveLodging")
	public Response<LodgingCommandDTO> SaveLodging(@RequestBody LodgingCommandDTO lodging) {
		return this.iLodgingService.saveLodging(lodging);
	}

	// Actualizar un hospedaje
	@Secured("ADMIN")
	@PatchMapping("/UpdateLodging/{id}")
	public Response<LodgingQueryDTO> UpdateLodging(@RequestBody LodgingCommandDTO lodging, @PathVariable Integer id) {
		return this.iLodgingService.updateLodging(id, lodging);
	}

	// Desabilitar un hospedaje registrado en el sistema
	@Secured("ADMIN")
	@PatchMapping("/DisableLodging/{id}")
	public Response<Boolean> DisableLodging(@PathVariable Integer id) {
		return this.iLodgingService.disableLodging(id);
	}

	// Habilitar un hospedaje registrado en el sistema
	@Secured("ADMIN")
	@PatchMapping("/EnableLodging/{id}")
	public Response<Boolean> EnableLodging(@PathVariable Integer id) {
		return this.iLodgingService.enableLodging(id);
	}

	// Consultar los hospedajes dependiento su estado: activado - desactivado
	@Secured("SUPER")
	@GetMapping("ConsultAllLodgingByState/{state}")
	public ResponseEntity<GenericPageableResponse> ConsultAllLodgingByState(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order,
			@PathVariable Boolean state) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK).body(this.iLodgingService.findAllLodgingBytState(state, pageable));
	}
	
	@Secured({ "ADMIN", "SUPER" })
	@GetMapping("/ConsulLodgingByStateByRuta/{state}/{rutaId}")
	public Response<List<LodgingQueryDTO>> ConsultLodgingByStateByRuta(@PathVariable Boolean state, @PathVariable Integer rutaId){
		return this.iLodgingService.findLodgingByStateByRuta(state, rutaId);
	}
}
