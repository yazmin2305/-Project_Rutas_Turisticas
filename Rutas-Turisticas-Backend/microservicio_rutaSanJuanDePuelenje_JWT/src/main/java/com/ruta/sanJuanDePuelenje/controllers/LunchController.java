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
import com.ruta.sanJuanDePuelenje.DTO.Command.LunchCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.LunchQueryDTO;
import com.ruta.sanJuanDePuelenje.services.ILunchService;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

@RestController
@RequestMapping("/lunch")
@CrossOrigin("*")
public class LunchController {

	@Autowired
	private ILunchService iLunchService;

	// Consultar todos los almuerzos
	@GetMapping("/ConsultAllLunch")
	public Response<List<LunchCommandDTO>> ConsultAllLunch() {
		return this.iLunchService.findAllLunch();
	}

	// Consultar todos los almuerzos que esten asociados a una ruta en especifico
	@Secured("ADMIN")
	@GetMapping("/ConsultAllLunchByRuta/{rutaId}")
	public Response<List<LunchQueryDTO>> ConsultAllLunchByRuta(@PathVariable Integer rutaId) {
		return this.iLunchService.findAllLunchByRuta(rutaId);
	}

	// Consultar un almuerzo por id
	@Secured({ "ADMIN", "USER" })
	@GetMapping("/ConsultById/{id}")
	public Response<LunchCommandDTO> ConsultLunchById(@PathVariable Integer id) {
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

	// Habilitar un almuerzo registrado en el sistema
	@Secured("ADMIN")
	@PatchMapping("/EnableLunch/{id}")
	public Response<Boolean> EnableLunch(@PathVariable Integer id) {
		return this.iLunchService.enableLunch(id);
	}

	// Consultar los almuerzos dependiento su estado: activado - desactivado
	@Secured({ "SUPER" })
	@GetMapping("ConsultAllLunchByState/{state}")
	public ResponseEntity<GenericPageableResponse> ConsultAllLunchByState(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order,
			@PathVariable Boolean state) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK).body(this.iLunchService.findAllLunchBytState(state, pageable));
	}

	// Consultar todos los almuerzos
	@GetMapping("/ConsultAllLunchByStateByRuta/{state}/{rutaId}")
	public Response<List<LunchQueryDTO>> ConsultAllLunchByStateByRuta(@PathVariable Boolean state, @PathVariable Integer rutaId) {
		return this.iLunchService.findAllLunchBytStateByRuta(state, rutaId);
	}
}
