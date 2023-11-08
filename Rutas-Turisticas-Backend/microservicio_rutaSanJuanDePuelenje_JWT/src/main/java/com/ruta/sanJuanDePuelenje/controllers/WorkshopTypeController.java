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
import com.ruta.sanJuanDePuelenje.DTO.WorkshopTypeDTO;
import com.ruta.sanJuanDePuelenje.services.IWorkshopTypeService;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

@RestController
@RequestMapping("/workshopType")
@CrossOrigin("*")
public class WorkshopTypeController {

	@Autowired
	private IWorkshopTypeService iWorkshopTypeTypeService;

	// Consultar todos los tipos de talleres
	@Secured({ "ADMIN", "USER" })
	@GetMapping("/ConsultAllWorkshopType")
	public ResponseEntity<GenericPageableResponse> ConsultAllWorkshopType(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK).body(this.iWorkshopTypeTypeService.findAllWorkshopTypes(pageable));
	}

	// Consultar un tipo de taller por su id
	@Secured({ "ADMIN", "USER" })
	@GetMapping("/ConsultById/{id}")
	public Response<WorkshopTypeDTO> ConsultWorkshopTypeById(@PathVariable Integer id) {
		return this.iWorkshopTypeTypeService.findByWorkshopTypeId(id);
	}

	// Guardar un tipo de taller
	@Secured("ADMIN")
	@PostMapping("/SaveWorkshopType")
	public Response<WorkshopTypeDTO> SaveWorkshopType(@RequestBody WorkshopTypeDTO workshopType) {
		return this.iWorkshopTypeTypeService.saveWorkshopType(workshopType);
	}

	// Actualizar un tipo de taller
	@Secured("ADMIN")
	@PatchMapping("/UpdateWorkshopType/{id}")
	public Response<WorkshopTypeDTO> UpdateWorkshopType(@RequestBody WorkshopTypeDTO workshopType,
			@PathVariable Integer id) {
		return this.iWorkshopTypeTypeService.updateWorkshopType(id, workshopType);
	}

	// Desabilitar un tipo de taller registrado en el sistema
	@Secured("ADMIN")
	@PatchMapping("/DisableWorkshopType/{id}")
	public Response<Boolean> DisableWorkshopType(@PathVariable Integer id) {
		return this.iWorkshopTypeTypeService.disableWorkshopType(id);
	}

	// Habilitar un tipo de taller registrado en el sistema
	@Secured("ADMIN")
	@PatchMapping("/EnableWorkshopType/{id}")
	public Response<Boolean> EnableWorkshopType(@PathVariable Integer id) {
		return this.iWorkshopTypeTypeService.enableWorkshopType(id);
	}

	// Consultar los tipos talleres dependiento su estado: activado - desactivado
	@Secured({ "ADMIN", "USER" })
	@GetMapping("ConsultAllWorkshopTypeByState/{state}")
	public ResponseEntity<GenericPageableResponse> ConsultAllWorkshopTypeByState(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order,
			@PathVariable Boolean state) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.iWorkshopTypeTypeService.findAllWorkshopTypeBytState(state, pageable));
	}
}
