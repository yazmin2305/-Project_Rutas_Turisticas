package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.ReserveCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.ReserveQueryDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.UserQueryDTO;
import com.ruta.sanJuanDePuelenje.models.Lodging;
import com.ruta.sanJuanDePuelenje.models.Recreation;
import com.ruta.sanJuanDePuelenje.models.Reserve;
import com.ruta.sanJuanDePuelenje.models.Talking;
import com.ruta.sanJuanDePuelenje.models.User;
import com.ruta.sanJuanDePuelenje.models.Workshop;
import com.ruta.sanJuanDePuelenje.repository.IReserveRepository;

@Service
public class ReserveServiceImpl implements IReserveService{

	@Autowired
	private IReserveRepository iReserveRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional(readOnly = true)
	public Response<List<ReserveQueryDTO>> findAllReserve(Integer rutaId) {
		List<Reserve> reserveEntity = iReserveRepository.LstReserveByRuta(rutaId);
		Response<List<ReserveQueryDTO>> response = new Response<>();
		if(reserveEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("No se encontraron reservas");
			response.setMoreInfo("http://localhost:8080/reserve/ConsultAllReserve");
			response.setData(null);
		}else {
			List<ReserveQueryDTO> reserveDTOs = reserveEntity.stream().map(reserve -> modelMapper.map(reserve, ReserveQueryDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Reservas encontradas con éxito");
			response.setMoreInfo("http://localhost:8080/reserve/ConsultAllReserve");
			response.setData(reserveDTOs);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<ReserveQueryDTO> findByReserveId(Integer reserveId) {
		Reserve reserve = iReserveRepository.findById(reserveId).orElse(null);
		Response<ReserveQueryDTO> response = new Response<>();
		if(reserve == null) {
			response.setStatus(404);
			response.setUserMessage("No se encontró la reserva");
			response.setMoreInfo("http://localhost:8080/user/ConsultById/{id}");
			response.setData(null);
		}
		ReserveQueryDTO reserveDTO = modelMapper.map(reserve, ReserveQueryDTO.class);
		response.setStatus(200);
		response.setUserMessage("Reserva encontrada con éxito");
		response.setMoreInfo("http://localhost:8080/user/ConsultById/{id}");
		response.setData(reserveDTO);
		return response;
	}

	@Override
	@Transactional
	public Response<ReserveCommandDTO> saveReserve(ReserveCommandDTO reserve) {
		Response<ReserveCommandDTO> response = new Response<>();
		if(reserve != null) {
			Reserve reserveEntity  = this.modelMapper.map(reserve, Reserve.class);
			reserveEntity.setState(true);
			double totalPrice = calculateTotalPrice2(reserve);
			reserveEntity.setTotalPriceReserve(totalPrice);
			Reserve objReserve = this.iReserveRepository.save(reserveEntity);
			ReserveCommandDTO reserveDTO = this.modelMapper.map(objReserve, ReserveCommandDTO.class);
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
	public Response<ReserveQueryDTO> updateReserve(Integer reserveId, ReserveCommandDTO reserve) {
		Response<ReserveQueryDTO> response = new Response<>();
		if(reserve != null && reserveId != null) {
			Reserve reserveEntity = this.modelMapper.map(reserve, Reserve.class);
			Reserve reserveEntity1 = this.iReserveRepository.findById(reserveId).get();
			double totalPrice = calculateTotalPrice2(reserve);
			reserveEntity1.setAmountPersons(reserveEntity.getAmountPersons());
//			reserveEntity1.setFechaInicio(reserveEntity.getFechaInicio());
//			reserveEntity1.setFechaFin(reserveEntity.getFechaFin());
			reserveEntity1.setNumberNights(reserveEntity.getNumberNights());
			reserveEntity1.setTotalPriceLodging(reserveEntity.getTotalPriceLodging());
			reserveEntity1.setTotalPriceLunch(reserveEntity.getTotalPriceLunch());
			reserveEntity1.setTotalPriceRecreation(reserveEntity.getTotalPriceRecreation());
			reserveEntity1.setTotalPriceTalking(reserveEntity.getTotalPriceTalking());
			reserveEntity1.setTotalPriceWorkshop(reserveEntity.getTotalPriceWorkshop());
			reserveEntity1.setState(reserveEntity.getState());
			reserveEntity1.setDate(reserveEntity.getDate());
			reserveEntity1.setUser(reserveEntity.getUser());
			reserveEntity1.setLstWorkshop(reserveEntity.getLstWorkshop());
			reserveEntity1.setLstTalking(reserveEntity.getLstTalking());
			reserveEntity1.setLstRecreation(reserveEntity.getLstRecreation());
			reserveEntity1.setLstLodging(reserveEntity.getLstLodging());
			reserveEntity1.setLstLunch(reserveEntity.getLstLunch());
			reserveEntity1.setTotalPriceReserve(totalPrice);
			this.iReserveRepository.save(reserveEntity1);
			ReserveQueryDTO reserveDTO = this.modelMapper.map(reserveEntity1, ReserveQueryDTO.class);
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
	public Response<List<ReserveQueryDTO>> findReservesByUser(Integer reserveId) {
		List<Reserve> reserveEntity = iReserveRepository.reservasUsuario(reserveId);
		Response<List<ReserveQueryDTO>> response = new Response<>();
		if(!reserveEntity.isEmpty()) {
			List<ReserveQueryDTO> reserveDTO = reserveEntity.stream().map(reserve -> modelMapper.map(reserve, ReserveQueryDTO.class)).collect(Collectors.toList());
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
	
	@Transactional(readOnly = true)
	@Override
	public Response<List<UserQueryDTO>> findAllUsersByRuta(Integer rutaId) {
		List<User> userEntity = this.iReserveRepository.LstUserByRuta(rutaId);
		Response<List<UserQueryDTO>> response = new Response<>();
		if(userEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("No se encontraron usuarios");
			response.setMoreInfo("http://localhost:8080/reserve/ConsultAllUsersByRuta/{id}");
			response.setData(null);
		}else {
			List<UserQueryDTO> userDTOs = userEntity.stream().map(user -> modelMapper.map(user, UserQueryDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Usuarios encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/reserve/ConsultAllUsersByRuta/{id}");
			response.setData(userDTOs);
		}
		return response;
	}
	
	//se calcula el precio total de la reserva y el precio total por cada item que el usuario selecciona
	private Double calculateTotalPrice2(ReserveCommandDTO reserve) {
		Reserve reserveEntity = this.modelMapper.map(reserve, Reserve.class);
		double totalPrice = 0;
		if(reserveEntity.getLstTalking() != null) {
			double totalPriceTalking = 0;
			for(Talking talking : reserveEntity.getLstTalking()) {
				totalPriceTalking += reserveEntity.getAmountPersons() * talking.getUnitPrice();   
			}
			reserveEntity.setTotalPriceTalking(totalPriceTalking);
			totalPrice = reserveEntity.getTotalPriceTalking();
		}if(reserveEntity.getLstWorkshop() != null) {
			double totalPriceWorkshop = 0;
			for(Workshop workshop : reserveEntity.getLstWorkshop()) {
				totalPriceWorkshop += reserveEntity.getAmountPersons() * workshop.getUnitPrice(); 
			}
			reserveEntity.setTotalPriceWorkshop(totalPriceWorkshop);
			totalPrice = reserveEntity.getTotalPriceWorkshop();
		}if(reserveEntity.getLstLodging() != null) {
			double totalPriceLodging = 0;
			for(Lodging lodging : reserveEntity.getLstLodging()) {
				totalPriceLodging += reserveEntity.getAmountPersons() * lodging.getUnitPrice();
			}
			reserveEntity.setTotalPriceLodging(totalPriceLodging);
			totalPrice = reserveEntity.getTotalPriceLodging();
		}if(reserveEntity.getLstRecreation() != null) {
			double totalPriceRecreation = 0;
			for(Recreation recreation : reserveEntity.getLstRecreation()) {
				totalPriceRecreation += reserveEntity.getAmountPersons() * recreation.getUnitPrice();
			}
			reserveEntity.setTotalPriceRecreation(totalPriceRecreation);
			totalPrice = reserveEntity.getTotalPriceRecreation();
		}if(reserveEntity.getLstLunch() != null) {
			/* Con el fin de que se pueda reservar diferentes menus para un grupo de personas,
			 * se realiza el cálculo total y se envia directamente*/
			reserveEntity.setTotalPriceLunch(reserveEntity.getTotalPriceLunch());
			
		}
		return totalPrice;
	}

	

}
