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
import com.ruta.sanJuanDePuelenje.DTO.Command.ReserveCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.ReserveQueryDTO;
import com.ruta.sanJuanDePuelenje.services.IReserveService;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

@RestController
@RequestMapping("/reserve")
@CrossOrigin("*")
public class ReserveController {

	@Autowired
	private IReserveService iReserveService;

	// Consultar todos las reservas por ruta
	@Secured({ "ADMIN", "SUPER" })
	@GetMapping("/ConsultAllReserveByRuta/{rutaId}")
	public ResponseEntity<GenericPageableResponse> ConsultAllReserveByRuta(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order, @PathVariable Integer rutaId) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.iReserveService.findAllReserveByRuta(rutaId, pageable));
	}

	// Consultar una reserva por su id
	@Secured({ "ADMIN", "SUPER" })
	@GetMapping("/ConsultReserveById/{id}")
	public Response<ReserveQueryDTO> ConsultReserveById(@PathVariable Integer id) {
		return this.iReserveService.findReserveById(id);
	}

	// Guardar una reserva
	@Secured("USER")
	@PostMapping("/SaveReserve")
	public Response<ReserveCommandDTO> SaveReserve(@RequestBody ReserveCommandDTO Reserve) {
		return this.iReserveService.saveReserve(Reserve);
	}

	// Actualizar una reserva
	@Secured({ "ADMIN", "USER" })
	@PatchMapping("/UpdateReserve/{id}")
	public Response<ReserveQueryDTO> UpdateReserve(@RequestBody ReserveCommandDTO Reserve, @PathVariable Integer id) {
		return this.iReserveService.updateReserve(id, Reserve);
	}

	// Desabilitar una reserva registrada en el sistema
	@Secured("ADMIN")
	@PatchMapping("/DisableReserve/{id}")
	public Response<Boolean> DisableReserve(@PathVariable Integer id) {
		return this.iReserveService.disableReserve(id);
	}

	// Habilitar una reserva registrada en el sistema
	@Secured("ADMIN")
	@PatchMapping("/EnableReserve/{id}")
	public Response<Boolean> EnableReserve(@PathVariable Integer id) {
		return this.iReserveService.enableReserve(id);
	}

	// Eliminar una reserva
	@Secured("ADMIN")
	@DeleteMapping("/DeleteReserve/{id}")
	public Response<Boolean> DeleteReserve(@PathVariable Integer id) {
		return this.iReserveService.deleteReserve(id);
	}

	// Listado de reservas de ha realizado un Usuario
	@Secured({ "ADMIN", "USER" })
	@GetMapping("/ConsultAllReserveByUser/{id}")
	public ResponseEntity<GenericPageableResponse> consultReserveUser(@RequestParam Integer page,
			@RequestParam Integer size, @RequestParam String sort, @RequestParam String order, @PathVariable Integer id) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.iReserveService.findReservesByUser(id, pageable));
	}


}
