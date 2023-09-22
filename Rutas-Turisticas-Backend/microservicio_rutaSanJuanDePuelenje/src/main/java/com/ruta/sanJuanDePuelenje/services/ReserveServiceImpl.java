package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruta.sanJuanDePuelenje.DTO.ReserveDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.models.Reserve;
import com.ruta.sanJuanDePuelenje.repository.IReserveRepository;

@Service
public class ReserveServiceImpl implements IReserveService{

	@Autowired
	private IReserveRepository iReserveRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional(readOnly = true)
	public Response<List<ReserveDTO>> findAllReserve() {
		List<Reserve> reserveEntity = iReserveRepository.findAll();
		Response<List<ReserveDTO>> response = new Response<>();
		if(reserveEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("No se encontraron reservas");
			response.setMoreInfo("http://localhost:8080/reserve/ConsultAllReserve");
			response.setData(null);
		}else {
			List<ReserveDTO> reserveDTOs = reserveEntity.stream().map(reserve -> modelMapper.map(reserve, ReserveDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Reservas encontradas con éxito");
			response.setMoreInfo("http://localhost:8080/reserve/ConsultAllReserve");
			response.setData(reserveDTOs);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<ReserveDTO> findByReserveId(Integer reserveId) {
		Reserve reserve = iReserveRepository.findById(reserveId).orElse(null);
		Response<ReserveDTO> response = new Response<>();
		if(reserve == null) {
			response.setStatus(404);
			response.setUserMessage("No se encontró la reserva");
			response.setMoreInfo("http://localhost:8080/user/ConsultById/{id}");
			response.setData(null);
		}
		ReserveDTO reserveDTO = modelMapper.map(reserve, ReserveDTO.class);
		response.setStatus(200);
		response.setUserMessage("Reserva encontrada con éxito");
		response.setMoreInfo("http://localhost:8080/user/ConsultById/{id}");
		response.setData(reserveDTO);
		return response;
	}

	@Override
	@Transactional
	public Response<ReserveDTO> saveReserve(ReserveDTO reserve) {
		Response<ReserveDTO> response = new Response<>();
		if(reserve != null) {
			Reserve reserveEntity  = this.modelMapper.map(reserve, Reserve.class);
			reserveEntity.setState(true);
			double totalPrice = calculateTotalPrice2(reserve);
			reserveEntity.setTotalPrice(totalPrice);
			Reserve objReserve = this.iReserveRepository.save(reserveEntity);
			ReserveDTO reserveDTO = this.modelMapper.map(objReserve, ReserveDTO.class);
			response.setStatus(200);
			response.setUserMessage("Reserva creada con éxito");
			response.setMoreInfo("http://localhost:8080/reserve/SaveReserve");
			response.setData(reserveDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("Error al crear la reserva");
			response.setMoreInfo("http://localhost:8080/reserve/SaveReserve");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<ReserveDTO> updateReserve(Integer reserveId, ReserveDTO reserve) {
		Response<ReserveDTO> response = new Response<>();
		if(reserve != null && reserveId != null) {
			Reserve reserveEntity = this.modelMapper.map(reserve, Reserve.class);
			Reserve reserveEntity1 = this.iReserveRepository.findById(reserveId).get();
			reserveEntity1.setAmountPersons(reserveEntity.getAmountPersons());
			reserveEntity1.setTotalPrice(reserveEntity.getTotalPrice());
			reserveEntity1.setTotalPriceLodging(reserveEntity.getTotalPriceLodging());
			reserveEntity1.setTotalPriceLunch(reserveEntity.getTotalPriceLunch());
			reserveEntity1.setTotalPriceRecreation(reserveEntity.getTotalPriceRecreation());
			reserveEntity1.setTotalPriceTalking(reserveEntity.getTotalPriceTalking());
			reserveEntity1.setTotalPriceWorkshop(reserveEntity.getTotalPriceWorkshop());
			reserveEntity1.setState(reserveEntity.getState());
			reserveEntity1.setDate(reserveEntity.getDate());
			reserveEntity1.setUser(reserveEntity.getUser());
			reserveEntity1.setWorkshop(reserveEntity.getWorkshop());
			reserveEntity1.setTalking(reserveEntity.getTalking());
			reserveEntity1.setRecreation(reserveEntity.getRecreation());
			reserveEntity1.setLodging(reserveEntity.getLodging());
			reserveEntity1.setFestival(reserveEntity.getFestival());
			reserveEntity1.setLunch(reserveEntity.getLunch());
			this.iReserveRepository.save(reserveEntity1);
			ReserveDTO reserveDTO = this.modelMapper.map(reserveEntity1, ReserveDTO.class);
			response.setStatus(200);
			response.setUserMessage("Reserva actualizada con éxito");
			response.setMoreInfo("http://localhost:8080/reserve/UpdateReserve/{id}");
			response.setData(reserveDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("Error al actualizar la reserva");
			response.setMoreInfo("http://localhost:8080/reserve/UpdateReserve/{id}");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<Boolean> disableReserve(Integer reserveId) {
		Reserve reserveEntity = this.iReserveRepository.findById(reserveId).get();
		Response<Boolean> response = new Response<>();
		if(reserveEntity != null){
			if(reserveEntity.getState() == true){
				reserveEntity.setState(false);
				this.iReserveRepository.save(reserveEntity);
				response.setStatus(200);
				response.setUserMessage("Reserva deshabilitada con éxito");
				response.setMoreInfo("http://localhost:8080/reserve/DisableReserve/{id}");
				response.setData(true);
			}else {
				response.setStatus(500);
				response.setUserMessage("La reserva ya está deshabilitada");
				response.setMoreInfo("http://localhost:8080/reserve/DisableReserve/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	@Transactional
	public Response<Boolean> deleteReserve(Integer reserveId) {
		Reserve reserveEntity = this.iReserveRepository.findById(reserveId).get();
		Response<Boolean> response = new Response<>();
		if(reserveEntity != null){
			iReserveRepository.deleteById(reserveId);
			response.setStatus(200);
			response.setUserMessage("Reserva eliminada con éxito");
			response.setMoreInfo("http://localhost:8080/reserve/DeleteReserve/{id}");
			response.setData(true);
		}else {
			response.setStatus(500);
			response.setUserMessage("Error al eliminar la reserva");
			response.setMoreInfo("http://localhost:8080/reserve/DeleteReserve/{id}");
			response.setData(false);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<ReserveDTO>> findReservesByUser(Integer reserveId) {
		List<Reserve> reserveEntity = iReserveRepository.reservasUsuario(reserveId);
		Response<List<ReserveDTO>> response = new Response<>();
		if(!reserveEntity.isEmpty()) {
			List<ReserveDTO> reserveDTO = reserveEntity.stream().map(reserve -> modelMapper.map(reserve, ReserveDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Reservas encontradas con éxito");
			response.setMoreInfo("http://localhost:8080/reserve/ConsultAllReserveUser");
			response.setData(reserveDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("No se encuentran reservas relacionadas a este estado");
			response.setMoreInfo("http://localhost:8080/reserve/ConsultAllReserveUser");
			response.setData(null);
		}
		return response;
	}
	
	//se calcula el precio total de la reserva y el precio total por cada item que el usuario selecciona
	private Double calculateTotalPrice2(ReserveDTO reserve) {
		Reserve reserveEntity = this.modelMapper.map(reserve, Reserve.class);
		double totalPrice = 0;
		if(reserveEntity.getTalking() != null) {
			reserveEntity.setTotalPriceTalking(reserveEntity.getAmountPersons() * reserveEntity.getTalking().getUnitPrice());   
			totalPrice += reserveEntity.getTotalPriceTalking();
		}if(reserveEntity.getWorkshop() != null) {
			reserveEntity.setTotalPriceWorkshop(reserveEntity.getAmountPersons() * reserveEntity.getWorkshop().getUnitPrice()); 
			totalPrice += reserveEntity.getTotalPriceWorkshop();
		}if(reserveEntity.getLodging() != null) {
			reserveEntity.setTotalPriceLodging(reserveEntity.getAmountPersons() * reserveEntity.getLodging().getUnitPrice());
			totalPrice += reserveEntity.getTotalPriceLodging();
		}if(reserveEntity.getRecreation() != null) {
			reserveEntity.setTotalPriceRecreation(reserveEntity.getAmountPersons() * reserveEntity.getRecreation().getUnitPrice());
			totalPrice += reserveEntity.getTotalPriceRecreation();
		}if(reserveEntity.getLunch() != null) {
			reserveEntity.setTotalPriceLunch(reserveEntity.getAmountPersons() * reserveEntity.getLunch().getUnitPrice());
		}
		this.iReserveRepository.save(reserveEntity);
		return totalPrice;
	}

}
