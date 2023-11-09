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
import com.ruta.sanJuanDePuelenje.DTO.Command.RecreationCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.RecreationQueryDTO;
import com.ruta.sanJuanDePuelenje.services.IRecreationService;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

@RestController
@RequestMapping("/recreation")
@CrossOrigin("*")
public class RecreationController {

	@Autowired
	private IRecreationService iRecreationService;

	// Consultar todos las actividades de recreación
	@GetMapping("/ConsultAllRecreation")
	public Response<List<RecreationQueryDTO>> ConsultAllRecreation() {
		return this.iRecreationService.findAllRecreation();
	}

	// Consultar una actividad recreativa por su id
	@Secured({ "ADMIN", "USER" })
	@GetMapping("/ConsultById/{id}")
	public Response<RecreationQueryDTO> ConsultRecreationById(@PathVariable Integer id) {
		return this.iRecreationService.findByRecreationId(id);
	}

	// Guardar una actividad recreativa
	@Secured("ADMIN")
	@PostMapping("/SaveRecreation")
	public Response<RecreationCommandDTO> SaveRecreation(@RequestBody RecreationCommandDTO recreation) {
		return this.iRecreationService.saveRecreation(recreation);
	}

	// Actualizar una actividad recreativa
	@Secured("ADMIN")
	@PatchMapping("/UpdateRecreation/{id}")
	public Response<RecreationQueryDTO> UpdateRecreation(@RequestBody RecreationCommandDTO recreation,
			@PathVariable Integer id) {
		return this.iRecreationService.updateRecreation(id, recreation);
	}

	// Desabilitar una actividad recreativa registrada en el sistema
	@Secured("ADMIN")
	@PatchMapping("/DisableRecreation/{id}")
	public Response<Boolean> DisableRecreation(@PathVariable Integer id) {
		return this.iRecreationService.disableRecreation(id);
	}

	// Habilitar una actividad recreativa registrada en el sistema
	@Secured("ADMIN")
	@PatchMapping("/EnableRecreation/{id}")
	public Response<Boolean> EnableRecreation(@PathVariable Integer id) {
		return this.iRecreationService.enableRecreation(id);
	}

	// Consultar las actividades de recreacion dependiento su estado: activado -
	// desactivado
	@Secured({ "ADMIN", "USER" })
	@GetMapping("ConsultAllRecreationByState/{state}")
	public ResponseEntity<GenericPageableResponse> ConsultAllRecreationByState(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order,
			@PathVariable Boolean state) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.iRecreationService.findAllRecreationBytState(state, pageable));
	}
}
