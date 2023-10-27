package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.WorkshopCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.WorkshopQueryDTO;
import com.ruta.sanJuanDePuelenje.services.IWorkshopService;

@RestController
@RequestMapping("/workshop")
@CrossOrigin("*")
public class WorkshopController {

	@Autowired
	private IWorkshopService iWorkshopService;

	// Consultar todos los talleres
	@GetMapping("/ConsultAllWorkshop")
	public Response<List<WorkshopQueryDTO>> ConsultAllWorkshop() {
		return this.iWorkshopService.findAllWorkshop();
	}

	// Consultar un taller por su id
	@Secured({"ADMIN", "USER"})
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
	public Response<WorkshopQueryDTO> UpdateWorkshop(@RequestBody WorkshopCommandDTO workshop, @PathVariable Integer id) {
		return this.iWorkshopService.updateWorkshop(id, workshop);
	}

	// Desabilitar un taller registrada en el sistema
	@Secured("ADMIN")
	@PatchMapping("/DisableWorkshop/{id}")
	public Response<Boolean> DisableWorkshop(@PathVariable Integer id) {
		return this.iWorkshopService.disableWorkshop(id);
	}

	// Consultar los talleres dependiento su estado: activado - desactivado
	@Secured({"ADMIN", "USER"})
	@GetMapping("ConsultAllWorkshopByState/{state}")
	public Response<List<WorkshopQueryDTO>> ConsultAllWorkshopByState(@PathVariable Boolean state) {
		return this.iWorkshopService.findAllWorkshopBytState(state);
	}
}
