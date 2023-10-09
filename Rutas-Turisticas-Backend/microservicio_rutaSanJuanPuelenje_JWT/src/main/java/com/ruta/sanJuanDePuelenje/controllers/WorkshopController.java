package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.WorkshopDTO;
import com.ruta.sanJuanDePuelenje.services.IWorkshopService;

import jakarta.annotation.security.PermitAll;

@RestController
@RequestMapping("/workshop")
@CrossOrigin("*")
public class WorkshopController {

	@Autowired
	private IWorkshopService iWorkshopService;

	// Consultar todos los talleres
	@PermitAll
	@GetMapping("/ConsultAllWorkshop")
	public Response<List<WorkshopDTO>> ConsultAllWorkshop() {
		return this.iWorkshopService.findAllWorkshop();
	}

	// Consultar un taller por su id
	@Secured({"ADMIN", "USER"})
	@GetMapping("/ConsultById/{id}")
	public Response<WorkshopDTO> ConsultWorkshopById(@PathVariable Integer id) {
		return this.iWorkshopService.findByWorkshopId(id);
	}

	// Guardar un taller
	@Secured("ADMIN")
	@PostMapping("/SaveWorkshop")
	public Response<WorkshopDTO> SaveWorkshop(@RequestBody WorkshopDTO workshop) {
		return this.iWorkshopService.saveWorkshop(workshop);
	}

	// Actualizar un taller
	@Secured("ADMIN")
	@PutMapping("/UpdateWorkshop/{id}")
	public Response<WorkshopDTO> UpdateWorkshop(@RequestBody WorkshopDTO workshop, @PathVariable Integer id) {
		return this.iWorkshopService.updateWorkshop(id, workshop);
	}

	// Desabilitar un taller registrada en el sistema
	@Secured("ADMIN")
	@PutMapping("/DisableWorkshop/{id}")
	public Response<Boolean> DisableWorkshop(@PathVariable Integer id) {
		return this.iWorkshopService.disableWorkshop(id);
	}

	// Consultar los talleres dependiento su estado: activado - desactivado
	@Secured({"ADMIN", "USER"})
	@GetMapping("ConsultAllWorkshopByState/{state}")
	public Response<List<WorkshopDTO>> ConsultAllWorkshopByState(@PathVariable Boolean state) {
		return this.iWorkshopService.findAllWorkshopBytState(state);
	}
}
