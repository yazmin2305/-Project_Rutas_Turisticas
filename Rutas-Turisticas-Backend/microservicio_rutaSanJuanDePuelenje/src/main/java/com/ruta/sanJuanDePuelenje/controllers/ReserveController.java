package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.ReserveDTO;
import com.ruta.sanJuanDePuelenje.services.IReserveService;

@RestController
@RequestMapping("/reserve")
@CrossOrigin("*")
public class ReserveController {
	
	@Autowired
	private IReserveService iReserveService;

	// Consultar todos las reservas
	@GetMapping("/ConsultAllReserve")
	public List<ReserveDTO> ConsultAllReserve() {
		return this.iReserveService.findAllReserve();
	}

	// Consultar una reserva por su id
	@GetMapping("/ConsultById/{id}")
	public ReserveDTO ConsultReserveById(@PathVariable Integer id) {
		return this.iReserveService.findByReserveId(id);
	}

	// Guardar una reserva
	@PostMapping("/SaveReserve")
	public ReserveDTO SaveReserve(@RequestBody ReserveDTO Reserve) {
		return this.iReserveService.saveReserve(Reserve);
	}

	// Actualizar una reserva
	@PutMapping("/UpdateReserve/{id}")
	public ReserveDTO UpdateReserve(@RequestBody ReserveDTO Reserve, @PathVariable Integer id) {
		return this.iReserveService.updateReserve(id, Reserve);
	}

	// Desabilitar una reserva registrada en el sistema
	@PutMapping("/DisableReserve/{id}")
	public Boolean DisableReserve(@PathVariable Integer id) {
		return this.iReserveService.disableReserve(id);
	}
	
	//Eliminar una reserva
	@DeleteMapping("/DeleteReserve/{id}")
	public Boolean DeleteReserve(@PathVariable Integer id) {
		return this.iReserveService.deleteReserve(id);
	}
	
	//listado de reservas de un Usuario
//	@GetMapping("/ConsultAllReserveUser/{id}")
//	public List<ReserveDTO> consulReserveDTOs(){
//		return this.
//	}
	
}
