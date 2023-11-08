package com.ruta.sanJuanDePuelenje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.WorkshopCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.WorkshopQueryDTO;
import com.ruta.sanJuanDePuelenje.services.IWorkshopService;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

@RestController
@RequestMapping("/workshop")
@CrossOrigin("*")
public class WorkshopController {

	@Autowired
	private IWorkshopService iWorkshopService;

	// Consultar todos los talleres
	@GetMapping("/ConsultAllWorkshop")
	public ResponseEntity<GenericPageableResponse> ConsultAllWorkshop(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK).body(this.iWorkshopService.findAllWorkshop(pageable));
	}

	// Consultar un taller por su id
	@Secured({ "ADMIN", "USER" })
	@GetMapping("/ConsultById/{id}")
	public Response<WorkshopQueryDTO> ConsultWorkshopById(@PathVariable Integer id) {
		return this.iWorkshopService.findByWorkshopId(id);
	}

	// Guardar un taller
	@Secured("ADMIN")
	@PostMapping("/SaveWorkshop")
	public Response<WorkshopCommandDTO> SaveWorkshop(@RequestBody WorkshopCommandDTO workshop) {
		return this.iWorkshopService.saveWorkshop(workshop);
	}

	// Actualizar un taller
	@Secured("ADMIN")
	@PatchMapping("/UpdateWorkshop/{id}")
	public Response<WorkshopQueryDTO> UpdateWorkshop(@RequestBody WorkshopCommandDTO workshop,
			@PathVariable Integer id) {
		return this.iWorkshopService.updateWorkshop(id, workshop);
	}

	// Desabilitar un taller registrada en el sistema
	@Secured("ADMIN")
	@PatchMapping("/DisableWorkshop/{id}")
	public Response<Boolean> DisableWorkshop(@PathVariable Integer id) {
		return this.iWorkshopService.disableWorkshop(id);
	}

	// Habilitar un taller registrada en el sistema
	@Secured("ADMIN")
	@PatchMapping("/EnableWorkshop/{id}")
	public Response<Boolean> EnableWorkshop(@PathVariable Integer id) {
		return this.iWorkshopService.enableWorkshop(id);
	}

	// Consultar los talleres dependiento su estado: activado - desactivado
	@Secured({ "ADMIN", "USER" })
	@GetMapping("ConsultAllWorkshopByState/{state}")
	public ResponseEntity<GenericPageableResponse> ConsultAllWorkshopByState(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order,
			@PathVariable Boolean state) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.iWorkshopService.findAllWorkshopBytState(state, pageable));
	}
}
