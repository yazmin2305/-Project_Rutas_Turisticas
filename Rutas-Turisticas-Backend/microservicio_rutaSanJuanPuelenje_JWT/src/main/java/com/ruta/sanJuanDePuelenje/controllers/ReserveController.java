package com.ruta.sanJuanDePuelenje.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.ruta.sanJuanDePuelenje.DTO.ReserveDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.services.IReserveService;

@RestController
@RequestMapping("/reserve")
@CrossOrigin("*")
public class ReserveController {
	
	@Autowired
	private IReserveService iReserveService;

	// Consultar todos las reservas
	@Secured("ADMIN")
	@GetMapping("/ConsultAllReserve")
	public Response<List<ReserveDTO>> ConsultAllReserve() {
		return this.iReserveService.findAllReserve();
	}

	// Consultar una reserva por su id
	@Secured({"ADMIN", "USER"})
	@GetMapping("/ConsultById/{id}")
	public Response<ReserveDTO> ConsultReserveById(@PathVariable Integer id) {
		return this.iReserveService.findByReserveId(id);
	}

	// Guardar una reserva
	@Secured("ADMIN")
	@PostMapping("/SaveReserve")
	public Response<ReserveDTO> SaveReserve(@RequestBody ReserveDTO Reserve) {
		return this.iReserveService.saveReserve(Reserve);
	}

	// Actualizar una reserva
	@Secured("ADMIN")
	@PutMapping("/UpdateReserve/{id}")
	public Response<ReserveDTO> UpdateReserve(@RequestBody ReserveDTO Reserve, @PathVariable Integer id) {
		return this.iReserveService.updateReserve(id, Reserve);
	}

	// Desabilitar una reserva registrada en el sistema
	@Secured("ADMIN")
	@PutMapping("/DisableReserve/{id}")
	public Response<Boolean> DisableReserve(@PathVariable Integer id) {
		return this.iReserveService.disableReserve(id);
	}
	
	//Eliminar una reserva
	@Secured("ADMIN")
	@DeleteMapping("/DeleteReserve/{id}")
	public Response<Boolean> DeleteReserve(@PathVariable Integer id) {
		return this.iReserveService.deleteReserve(id);
	}
	
	//listado de reservas de un Usuario
	@Secured({"ADMIN", "USER"})
	@GetMapping("/ConsultAllReserveUser/{id}")
	public Response<List<ReserveDTO>> consulReserveDTOs(@PathVariable Integer id){
		return this.iReserveService.findReservesByUser(id);
	}
	
}
