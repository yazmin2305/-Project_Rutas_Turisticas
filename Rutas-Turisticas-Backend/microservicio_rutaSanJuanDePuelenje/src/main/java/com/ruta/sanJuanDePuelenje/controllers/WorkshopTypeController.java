package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.WorkshopTypeDTO;
import com.ruta.sanJuanDePuelenje.services.IWorkshopTypeService;

@RestController
@RequestMapping("/workshopType")
@CrossOrigin("*")
public class WorkshopTypeController {
	
	@Autowired
	private IWorkshopTypeService iWorkshopTypeTypeService;

	// Consultar todos los tipos de talleres
	@GetMapping("/ConsultAllWorkshopType")
	public List<WorkshopTypeDTO> ConsultAllWorkshopType() {
		return this.iWorkshopTypeTypeService.findAllWorkshopTypes();
	}

	// Consultar un tipo de taller por su id
	@GetMapping("/ConsultById/{id}")
	public WorkshopTypeDTO ConsultWorkshopTypeById(@PathVariable Integer id) {
		return this.iWorkshopTypeTypeService.findByWorkshopTypeId(id);
	}

	// Guardar un tipo de taller
	@PostMapping("/SaveWorkshopType")
	public WorkshopTypeDTO SaveWorkshopType(@RequestBody WorkshopTypeDTO workshopType) {
		return this.iWorkshopTypeTypeService.saveWorkshopType(workshopType);
	}

	// Actualizar un tipo de taller
	@PutMapping("/UpdateWorkshopType/{id}")
	public WorkshopTypeDTO UpdateWorkshopType(@RequestBody WorkshopTypeDTO workshopType, @PathVariable Integer id) {
		return this.iWorkshopTypeTypeService.updateWorkshopType(id, workshopType);
	}

	// Desabilitar un tipo de taller registrado en el sistema
	@PutMapping("/DisableWorkshopType/{id}")
	public Boolean DisableWorkshopType(@PathVariable Integer id) {
		return this.iWorkshopTypeTypeService.disableWorkshopType(id);
	}
}
