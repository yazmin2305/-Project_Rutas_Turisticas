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
	public Response<List<WorkshopCommandDTO>> ConsultAllWorkshop() {
		return this.iWorkshopService.findAllWorkshop();
	}

	// Consultar todos los talleres por ruta
	@Secured({ "ADMIN", "SUPER" })
	@GetMapping("/ConsultAllWorkshopByRuta/{rutaId}")
	public Response<List<WorkshopQueryDTO>> ConsultAllWorkshopByRuta(@PathVariable Integer rutaId) {
		return this.iWorkshopService.findAllWorkshopBytRuta(rutaId);
	}

	// Consultar un taller por su id
	@Secured({ "ADMIN", "SUPER" })
	@GetMapping("/ConsultById/{id}")
	public Response<WorkshopCommandDTO> ConsultWorkshopById(@PathVariable Integer id) {
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
	@Secured("SUPER")
	@GetMapping("ConsultAllWorkshopByState/{state}")
	public ResponseEntity<GenericPageableResponse> ConsultAllWorkshopByState(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order,
			@PathVariable Boolean state) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.iWorkshopService.findAllWorkshopBytState(state, pageable));
	}

	// Consultar todos los talleres por estado y ruta relacionadas
	@Secured({ "ADMIN", "USER" })
	@GetMapping("/ConsultAllWorkshopByRutaByState/{state}/{rutaId}")
	public Response<List<WorkshopQueryDTO>> ConsultAllWorkshopByStateRuta(@PathVariable Boolean state,
			@PathVariable Integer rutaId) {
		return this.iWorkshopService.findWorkshopByStateByRuta(false, rutaId);
	}
}
