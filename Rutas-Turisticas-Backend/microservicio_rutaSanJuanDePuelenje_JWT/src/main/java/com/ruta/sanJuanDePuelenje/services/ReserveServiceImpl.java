package com.ruta.sanJuanDePuelenje.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.ReserveCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Command.ReserveLodgingCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Command.ReserveLunchCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.ReserveQueryDTO;
import com.ruta.sanJuanDePuelenje.models.Lodging;
import com.ruta.sanJuanDePuelenje.models.Lunch;
import com.ruta.sanJuanDePuelenje.models.Recreation;
import com.ruta.sanJuanDePuelenje.models.Reserve;
import com.ruta.sanJuanDePuelenje.models.ReserveLodging;
import com.ruta.sanJuanDePuelenje.models.ReserveLunch;
import com.ruta.sanJuanDePuelenje.models.Talking;
import com.ruta.sanJuanDePuelenje.models.Workshop;
import com.ruta.sanJuanDePuelenje.repository.ILodgingRepository;
import com.ruta.sanJuanDePuelenje.repository.IReserveLodgingRepository;
import com.ruta.sanJuanDePuelenje.repository.IReserveLunchRepository;
import com.ruta.sanJuanDePuelenje.repository.IReserveRepository;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;
import com.ruta.sanJuanDePuelenje.util.PageableUtils;

@Service
public class ReserveServiceImpl implements IReserveService {

	@Autowired
	private IReserveRepository iReserveRepository;
	
	@Autowired
	private IReserveLunchRepository iReserveLunchRepository;
	
	@Autowired
	private IReserveLodgingRepository iReserveLodgingRepository;
	
	@Autowired
	private ILodgingRepository iLodgingRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	// Servicio que me retorna todas las reservas que se han realizado en determinada ruta
	@Override
	@Transactional(readOnly = true)
	public GenericPageableResponse findAllReserveByRuta(Integer rutaId, Pageable pageable) {
		Page<Reserve> reservePage = this.iReserveRepository.LstReserveByRuta(rutaId, pageable);
		if (reservePage.isEmpty())
			return GenericPageableResponse.emptyResponse("No se encuentran reservas relacionadas a esta ruta");
		return this.validatePageList(reservePage);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<ReserveQueryDTO> findReserveById(Integer reserveId) {
		Reserve reserve = iReserveRepository.findById(reserveId).orElse(null);
		Response<ReserveQueryDTO> response = new Response<>();
		if (reserve == null) {
			response.setStatus(404);
			response.setUserMessage("No se encontró la reserva");
			response.setMoreInfo("http://localhost:8080/user/ConsultById/{id}");
			response.setData(null);
		} else {
			ReserveQueryDTO reserveDTO = modelMapper.map(reserve, ReserveQueryDTO.class);
			response.setStatus(200);
			response.setUserMessage("Reserva encontrada con éxito");
			response.setMoreInfo("http://localhost:8080/user/ConsultById/{id}");
			response.setData(reserveDTO);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<ReserveCommandDTO> saveReserve(ReserveCommandDTO reserveDTO) {
		Response<ReserveCommandDTO> response = new Response<>();
		if (reserveDTO != null) {
			Reserve reserveEntity = this.modelMapper.map(reserveDTO, Reserve.class);
			reserveEntity = calculateTotalPrice(reserveDTO);
			reserveEntity.setState(true);
			Reserve savedReserve = this.iReserveRepository.save(reserveEntity);
			//if (reserveDTO.getReserveLunch() != null && !reserveDTO.getReserveLunch().isEmpty()) {
				System.out.println("ESNTRASAS");
				for (ReserveLunchCommandDTO reserveLunchDTO : reserveDTO.getReserveLunch()) {
					System.out.println("ESNTRASA222S");
					Lunch lunchEntity = this.modelMapper.map(reserveLunchDTO.getLunch(), Lunch.class);
					ReserveLunch reserveLunchEntity = new ReserveLunch();
					reserveLunchEntity.setCantidad(reserveLunchDTO.getCantidad());
					reserveLunchEntity.setLunch(lunchEntity);
					reserveLunchEntity.setReserve(savedReserve);
					// Guardar ReserveLunch(entidad intermedia)
					iReserveLunchRepository.save(reserveLunchEntity);
				}
			//}
			if (reserveDTO.getReserveLodging() != null && !reserveDTO.getReserveLodging().isEmpty()) {
				for (ReserveLodgingCommandDTO reserveLodgingDTO : reserveDTO.getReserveLodging()) {
					Lodging lodgingEntity = this.modelMapper.map(reserveLodgingDTO.getLodging(), Lodging.class);
					ReserveLodging reserveLodgingEntity = new ReserveLodging();
					reserveLodgingEntity.setCantidad(reserveLodgingDTO.getCantidad());
					reserveLodgingEntity.setLodging(lodgingEntity);
					reserveLodgingEntity.setReserve(savedReserve);
					// Guardar ReserveLodging(entidad intermedia)
					iReserveLodgingRepository.save(reserveLodgingEntity);
				}
			}
			ReserveCommandDTO savedreserveDTO = this.modelMapper.map(savedReserve, ReserveCommandDTO.class);
			response.setStatus(200);
			response.setUserMessage("Reserva creada con éxito");
			response.setMoreInfo("http://localhost:8080/reserve/SaveReserve");
			response.setData(savedreserveDTO);
		} else {
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
		if (reserve != null && reserveId != null) {
			Reserve reserveEntity = this.modelMapper.map(reserve, Reserve.class);
			Reserve reserveEntity1 = this.iReserveRepository.findById(reserveId).get();
			Reserve reserveEntityPrice = calculateTotalPrice(reserve);
			reserveEntity1.setAmountPersons(reserveEntity.getAmountPersons());
			reserveEntity1.setNumberNights(reserveEntity.getNumberNights());
			reserveEntity1.setTotalPriceLodging(reserveEntityPrice.getTotalPriceLodging());
			reserveEntity1.setTotalPriceLunch(reserveEntityPrice.getTotalPriceLunch());
			reserveEntity1.setTotalPriceRecreation(reserveEntityPrice.getTotalPriceRecreation());
			reserveEntity1.setTotalPriceTalking(reserveEntityPrice.getTotalPriceTalking());
			reserveEntity1.setTotalPriceWorkshop(reserveEntityPrice.getTotalPriceWorkshop());
			reserveEntity1.setTotalPriceReserve(reserveEntityPrice.getTotalPriceReserve());
			reserveEntity1.setDate(reserveEntity.getDate());
			reserveEntity1.setUser(reserveEntity.getUser());
			reserveEntity1.setLstWorkshop(reserveEntity.getLstWorkshop());
			reserveEntity1.setLstTalking(reserveEntity.getLstTalking());
			reserveEntity1.setLstRecreation(reserveEntity.getLstRecreation());			
			if (reserveEntity.getReserveLunch() != null) {
				UpdateReserveLunch(reserveEntity1, reserveEntity);
			}else {
				iReserveLunchRepository.deleteByReserve(reserveEntity1);
			}
			if (reserveEntity.getReserveLodging() != null) {
				UpdateReserveLodging(reserveEntity1, reserveEntity);
			}else {
				iReserveLodgingRepository.deleteByReserve(reserveEntity1);
			}
			this.iReserveRepository.save(reserveEntity1);
			ReserveQueryDTO reserveDTO = this.modelMapper.map(reserveEntity1, ReserveQueryDTO.class);
			response.setStatus(200);
			response.setUserMessage("Reserva actualizada con éxito");
			response.setMoreInfo("http://localhost:8080/reserve/UpdateReserve/{id}");
			response.setData(reserveDTO);
		} else {
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
		if (reserveEntity != null) {
			if (reserveEntity.getState() == true) {
				reserveEntity.setState(false);
				this.iReserveRepository.save(reserveEntity);
				response.setStatus(200);
				response.setUserMessage("Reserva deshabilitada con éxito");
				response.setMoreInfo("http://localhost:8080/reserve/DisableReserve/{id}");
				response.setData(true);
			} else {
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
	public Response<Boolean> enableReserve(Integer reserveId) {
		Reserve reserveEntity = this.iReserveRepository.findById(reserveId).get();
		Response<Boolean> response = new Response<>();
		if (reserveEntity != null) {
			if (reserveEntity.getState() == false) {
				reserveEntity.setState(true);
				this.iReserveRepository.save(reserveEntity);
				response.setStatus(200);
				response.setUserMessage("Reserva habilitada con éxito");
				response.setMoreInfo("http://localhost:8080/reserve/EnableReserve/{id}");
				response.setData(true);
			} else {
				response.setStatus(500);
				response.setUserMessage("La reserva ya está habilitada");
				response.setMoreInfo("http://localhost:8080/reserve/EnableReserve/{id}");
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
		if (reserveEntity != null) {
			iReserveRepository.deleteById(reserveId);
			response.setStatus(200);
			response.setUserMessage("Reserva eliminada con éxito");
			response.setMoreInfo("http://localhost:8080/reserve/DeleteReserve/{id}");
			response.setData(true);
		} else {
			response.setStatus(500);
			response.setUserMessage("Error al eliminar la reserva");
			response.setMoreInfo("http://localhost:8080/reserve/DeleteReserve/{id}");
			response.setData(false);
		}
		return response;
	}

	// Servicio que permite consultar las reservas que ha realizado determinado usuario
	@Override
	@Transactional(readOnly = true)
	public GenericPageableResponse findReservesByUser(Integer userId, Pageable pageable) {
		Page<Reserve> reservePage = this.iReserveRepository.reservasUsuario(userId, pageable);
		if (reservePage.isEmpty())
			return GenericPageableResponse.emptyResponse("No se encuentran reservas relacionadas a esta ruta");
		return this.validatePageList(reservePage);
	}

	// Función que calcula el precio total de la reserva y el precio total de cada una de las actividades reservadas
	private Reserve calculateTotalPrice(ReserveCommandDTO reserve) {
		Reserve reserveEntity = this.modelMapper.map(reserve, Reserve.class);
		double totalPriceTalking = 0;
		double totalPriceWorkshop = 0;
		double totalPriceLodging = 0;
		double totalPriceRecreation = 0;
		double totalPriceLunch = 0;
		double totalPrice = 0;
		if (reserveEntity.getLstTalking() != null) {
			for (Talking talking : reserveEntity.getLstTalking()) {
				totalPriceTalking += reserveEntity.getAmountPersons() * talking.getUnitPrice();
			}
			reserveEntity.setTotalPriceTalking(totalPriceTalking);
			totalPrice += reserveEntity.getTotalPriceTalking();
		}
		if (reserveEntity.getLstWorkshop() != null) {
			for (Workshop workshop : reserveEntity.getLstWorkshop()) {
				totalPriceWorkshop += reserveEntity.getAmountPersons() * workshop.getUnitPrice();
			}
			reserveEntity.setTotalPriceWorkshop(totalPriceWorkshop);
			totalPrice += reserveEntity.getTotalPriceWorkshop();
		}
		if (reserveEntity.getReserveLodging() != null) {
			for (ReserveLodging lodging : reserveEntity.getReserveLodging()) {
				// Esta operacion se realiza teniendo en cuenta que el precio del cada hospedaje es por dia
				totalPriceLodging += lodging.getCantidad() * lodging.getLodging().getUnitPrice()
						* reserveEntity.getNumberNights();
			}
			reserveEntity.setTotalPriceLodging(totalPriceLodging);
			totalPrice += reserveEntity.getTotalPriceLodging();
		}
		if (reserveEntity.getLstRecreation() != null) {
			for (Recreation recreation : reserveEntity.getLstRecreation()) {
				totalPriceRecreation += reserveEntity.getAmountPersons() * recreation.getUnitPrice();
			}
			reserveEntity.setTotalPriceRecreation(totalPriceRecreation);
			totalPrice += reserveEntity.getTotalPriceRecreation();
		}
		if (reserveEntity.getReserveLunch() != null) {
			for (ReserveLunch lunch : reserveEntity.getReserveLunch()) {
				totalPriceLunch += lunch.getCantidad() * lunch.getLunch().getUnitPrice();
			}
			reserveEntity.setTotalPriceLunch(totalPriceLunch);
			totalPrice += reserveEntity.getTotalPriceLunch();
		}
		reserveEntity.setTotalPriceReserve(totalPrice);
		return reserveEntity;
	}

	private GenericPageableResponse validatePageList(Page<Reserve> reservePage) {
		List<ReserveQueryDTO> reserveDTOS = reservePage.stream().map(x -> modelMapper.map(x, ReserveQueryDTO.class))
				.collect(Collectors.toList());
		return PageableUtils.createPageableResponse(reservePage, reserveDTOS);
	}
	
	private void SaveReserveLunch(ReserveCommandDTO reserveDTO, Reserve savedReserve) {
		if (reserveDTO.getReserveLunch() != null && !reserveDTO.getReserveLunch().isEmpty()) {
			for (ReserveLunchCommandDTO reserveLunchDTO : reserveDTO.getReserveLunch()) {
				Lunch lunchEntity = this.modelMapper.map(reserveLunchDTO.getLunch(), Lunch.class);
				ReserveLunch reserveLunchEntity = new ReserveLunch();
				reserveLunchEntity.setCantidad(reserveLunchDTO.getCantidad());
				reserveLunchEntity.setLunch(lunchEntity);
				reserveLunchEntity.setReserve(savedReserve);
				// Guardar ReserveLunch(entidad intermedia)
				iReserveLunchRepository.save(reserveLunchEntity);
			}
		}
	}
	
	private void SaveReserveLodging(ReserveCommandDTO reserveDTO, Reserve savedReserve) {
		if (reserveDTO.getReserveLodging() != null && !reserveDTO.getReserveLodging().isEmpty()) {
			for (ReserveLodgingCommandDTO reserveLodgingDTO : reserveDTO.getReserveLodging()) {
				Lodging lodgingEntity = this.modelMapper.map(reserveLodgingDTO.getLodging(), Lodging.class);
				ReserveLodging reserveLodgingEntity = new ReserveLodging();
				reserveLodgingEntity.setCantidad(reserveLodgingDTO.getCantidad());
				reserveLodgingEntity.setLodging(lodgingEntity);
				reserveLodgingEntity.setReserve(savedReserve);
				// Guardar ReserveLodging(entidad intermedia)
				iReserveLodgingRepository.save(reserveLodgingEntity);
//				for(int i = 0; i < reserveDTO.getReserveLodging().size(); i++ ) {
//					 //lodging = new Lodging();
//					 Optional<Lodging> lodging = this.iLodgingRepository.findById(reserveLodgingDTO.getLodging().getLodgingId());
//					 Lodging lodgingEntity1 = lodging.get();
//					 lodgingEntity1.setQuantityAvailable(lodgingEntity1.getQuantityAvailable() - reserveLodgingDTO.getCantidad());
//				}
				
				Optional<Lodging> lodging = this.iLodgingRepository.findById(reserveLodgingEntity.getLodging().getId());
			    lodging.ifPresent(lodgingEnt -> lodgingEnt.setQuantityAvailable(
			            lodgingEntity.getQuantityAvailable() - reserveLodgingEntity.getCantidad()
			    ));
			}
		}
	}
	
	// Método para actualizar la tabla intermedia entre las entidades Lunch y Reserve 
	private void UpdateReserveLunch(Reserve reserveEntityExisting, Reserve reserveEntityUpdate) {
		// Crear una copia de la lista actual de ReserveLunch para evitar la ConcurrentModificationException
		List<ReserveLunch> currentReserveLunchList = new ArrayList<>(reserveEntityExisting.getReserveLunch());
		for (ReserveLunch existingReserveLunch : currentReserveLunchList) {
			boolean isExistingLunchPresent = reserveEntityUpdate.getReserveLunch().stream()
					.anyMatch(rl -> Objects.equals(rl.getId(), existingReserveLunch.getId()));

			if (!isExistingLunchPresent) {
				reserveEntityExisting.getReserveLunch().remove(existingReserveLunch);
				// Eliminar las instancias antiguas de la tabla intermedia
				iReserveLunchRepository.delete(existingReserveLunch);
			}
		}
		// Iterar sobre la nueva lista y asociar las instancias con la reserva actualizada
		for (ReserveLunch newReserveLunch : reserveEntityUpdate.getReserveLunch()) {
			ReserveLunch existingReserveLunch = currentReserveLunchList.stream()
					.filter(rl -> Objects.equals(rl.getId(), newReserveLunch.getId())).findFirst().orElse(null);
			if (existingReserveLunch != null) {
				// Actualizar las propiedades si es necesario
				existingReserveLunch.setLunch(newReserveLunch.getLunch());
				existingReserveLunch.setCantidad(newReserveLunch.getCantidad());
			} else {
				// Crear una nueva instancia si no existe
				ReserveLunch reserveLunchEntity = new ReserveLunch();
				reserveLunchEntity.setReserve(reserveEntityExisting);
				reserveLunchEntity.setLunch(newReserveLunch.getLunch());
				reserveLunchEntity.setCantidad(newReserveLunch.getCantidad());
				// Guardar la nueva instancia de ReserveLunch en la base de datos
				iReserveLunchRepository.save(reserveLunchEntity);
			}
		}
	}
	
	// Método para actualizar la tabla intermedia entre las entidades Lodging y Reserve 
	private void UpdateReserveLodging(Reserve reserveEntityExisting, Reserve reserveEntityUpdate) {
		// Crear una copia de la lista actual de ReserveLodging para evitar la ConcurrentModificationException
		List<ReserveLodging> currentReserveLodgingList = new ArrayList<>(reserveEntityExisting.getReserveLodging());
		for (ReserveLodging existingReserveLodging : currentReserveLodgingList) {
			boolean isExistingLodgingPresent = reserveEntityUpdate.getReserveLodging().stream()
					.anyMatch(rl -> Objects.equals(rl.getId(), existingReserveLodging.getId()));
			if (!isExistingLodgingPresent) {
				reserveEntityExisting.getReserveLodging().remove(existingReserveLodging);
				// Eliminar las instancias antiguas de la tabla intermedia
				iReserveLodgingRepository.delete(existingReserveLodging);
			}
		}
		// Iterar sobre la nueva lista y asociar las instancias con la reserva
		// actualizada
		for (ReserveLodging newReserveLodging : reserveEntityUpdate.getReserveLodging()) {
			ReserveLodging existingReserveLodging = currentReserveLodgingList.stream()
					.filter(rl -> Objects.equals(rl.getId(), newReserveLodging.getId())).findFirst().orElse(null);
			if (existingReserveLodging != null) {
				// Ya que se va a reservar una cantidad determinada de hospedajes, se deben
				// restar los hospedajes que se estan reservando para al momento de realizar una
				// nueva reserva me muestre los que realmente hay
				int QuantityAvailable = existingReserveLodging.getCantidad();
				int newQuantityAvailable = newReserveLodging.getCantidad();
				System.out.println("viejo: " + QuantityAvailable);
				System.out.println("nuevo: " + newQuantityAvailable);
				if (QuantityAvailable != newQuantityAvailable) {
					System.out.println("entra: ");
					this.iLodgingRepository.findById(newReserveLodging.getLodging().getId()).ifPresent(lodgingEntityLodging -> {
						lodgingEntityLodging.setQuantityAvailable(lodgingEntityLodging.getQuantityAvailable() - newReserveLodging.getCantidad());
						iLodgingRepository.save(lodgingEntityLodging);
					});
				}
				// Actualizar las propiedades si es necesario
				existingReserveLodging.setLodging(newReserveLodging.getLodging());
				existingReserveLodging.setCantidad(newReserveLodging.getCantidad());
				iReserveLodgingRepository.save(existingReserveLodging);
			} else {
				// Crear una nueva instancia si no existe
				ReserveLodging reserveLodgingEntity = new ReserveLodging();
				reserveLodgingEntity.setReserve(reserveEntityExisting);
				reserveLodgingEntity.setLodging(newReserveLodging.getLodging());
				reserveLodgingEntity.setCantidad(newReserveLodging.getCantidad());
				// Guardar la nueva instancia de ReserveLodging en la base de datos
				iReserveLodgingRepository.save(reserveLodgingEntity);
			}
		}
	}

}
