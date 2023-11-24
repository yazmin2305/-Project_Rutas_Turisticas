package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.LodgingCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.LodgingQueryDTO;
import com.ruta.sanJuanDePuelenje.models.Lodging;
import com.ruta.sanJuanDePuelenje.repository.ILodgingRepository;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;
import com.ruta.sanJuanDePuelenje.util.PageableUtils;

@Service
public final class LodgingServiceImpl implements ILodgingService {

	@Autowired
	private ILodgingRepository iLodgingRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Response<List<LodgingCommandDTO>> findAllLodging() {
		List<Lodging> lodgingEntity = iLodgingRepository.findAll();
		Response<List<LodgingCommandDTO>> response = new Response<>();
		if (lodgingEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Hospedajes no encontrados");
			response.setMoreInfo("http://localhost:8080/lodging/ConsultAllLodging");
			response.setData(null);
		} else {
			List<LodgingCommandDTO> lodgingDTOs = lodgingEntity.stream()
					.map(lodging -> modelMapper.map(lodging, LodgingCommandDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Hospedajes encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/lodging/ConsultAllLodging");
			response.setData(lodgingDTOs);
		}
		return response;
	}

	@Override
	public Response<List<LodgingQueryDTO>> findAllLodgingBytRuta(Integer rutaId) {
		List<Lodging> lodgingEntity = iLodgingRepository.findAllLodgingByRuta(rutaId);
		Response<List<LodgingQueryDTO>> response = new Response<>();
		if (lodgingEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Hospedajes no encontrados");
			response.setMoreInfo("http://localhost:8080/lodging/ConsultAllLodgingByRuta/{rutaId}");
			response.setData(null);
		} else {
			List<LodgingQueryDTO> lodgingDTOs = lodgingEntity.stream()
					.map(lodging -> modelMapper.map(lodging, LodgingQueryDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Hospedajes encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/lodging/ConsultAllLodgingByRuta/{rutaId}");
			response.setData(lodgingDTOs);
		}
		return response;
	}

	@Override
	public Response<LodgingCommandDTO> findByLodgingId(Integer lodgingId) {
		Lodging lodging = iLodgingRepository.findById(lodgingId).orElse(null);
		Response<LodgingCommandDTO> response = new Response<>();
		if (lodging == null) {
			response.setStatus(404);
			response.setUserMessage("Hospedaje no encontrado");
			response.setMoreInfo("http://localhost:8080/lodging/ConsultById/{id}");
			response.setData(null);
		} else {
			LodgingCommandDTO lodgingDTO = modelMapper.map(lodging, LodgingCommandDTO.class);
			response.setStatus(200);
			response.setUserMessage("Hospedaje encontrado con éxito");
			response.setMoreInfo("http://localhost:8080/lodging/ConsultById/{id}");
			response.setData(lodgingDTO);
		}
		return response;
	}

	@Override
	public Response<LodgingCommandDTO> saveLodging(LodgingCommandDTO lodging) {
		Lodging lodgingEntity = this.modelMapper.map(lodging, Lodging.class);
		Response<LodgingCommandDTO> response = new Response<>();
		if (lodging != null) {
			lodgingEntity.setState(true);
			Lodging objLodging = this.iLodgingRepository.save(lodgingEntity);
			LodgingCommandDTO lodgingDTO = this.modelMapper.map(objLodging, LodgingCommandDTO.class);
			response.setStatus(200);
			response.setUserMessage("Hospedaje creado con éxito");
			response.setMoreInfo("http://localhost:8080/lodging/SaveLodging");
			response.setData(lodgingDTO);
		} else {
			response.setStatus(500);
			response.setUserMessage("Error al crear el hospedaje");
			response.setMoreInfo("http://localhost:8080/lodging/SaveLodging");
			response.setData(null);
		}
		return response;
	}

	@Override
	public Response<LodgingQueryDTO> updateLodging(Integer lodgingId, LodgingCommandDTO lodging) {
		Response<LodgingQueryDTO> response = new Response<>();
		Optional<Lodging> optionalLodging = this.iLodgingRepository.findById(lodgingId);
		if (optionalLodging.isPresent()) {
			Lodging lodgingEntity1 = optionalLodging.get();
			Lodging lodgingEntity = this.modelMapper.map(lodging, Lodging.class);
			lodgingEntity1.setName(lodgingEntity.getName());
			lodgingEntity1.setDescription(lodgingEntity.getDescription());
			lodgingEntity1.setQuantityAvailable(lodgingEntity.getQuantityAvailable());
			lodgingEntity1.setMaxAmountPerson(lodgingEntity.getMaxAmountPerson());
			lodgingEntity1.setUnitPrice(lodgingEntity.getUnitPrice());
			lodgingEntity1.setFinca(lodgingEntity.getFinca());
			this.iLodgingRepository.save(lodgingEntity1);
			LodgingQueryDTO lodgingDTO = this.modelMapper.map(lodgingEntity1, LodgingQueryDTO.class);
			response.setStatus(200);
			response.setUserMessage("Hospedaje actualizado con éxito");
			response.setMoreInfo("http://localhost:8080/lodging/UpdateLodging/{id}");
			response.setData(lodgingDTO);
		} else {
			response.setStatus(500);
			response.setUserMessage("Error al actualiar el hospedaje");
			response.setMoreInfo("http://localhost:8080/lodging/UpdateLodging/{id}");
			response.setData(null);
		}
		return response;
	}

	@Override
	public Response<Boolean> disableLodging(Integer lodgingId) {
		Lodging lodgingEntity = this.iLodgingRepository.findById(lodgingId).get();
		Response<Boolean> response = new Response<>();
		if (lodgingEntity != null) {
			if (lodgingEntity.getState() == true) {
				lodgingEntity.setState(false);
				this.iLodgingRepository.save(lodgingEntity);
				response.setStatus(200);
				response.setUserMessage("Hospedaje deshabilitado con éxito");
				response.setMoreInfo("http://localhost:8080/lodging/DisableLodging/{id}");
				response.setData(true);
			} else {
				response.setStatus(500);
				response.setUserMessage("El hospedaje ya esta deshabilitado");
				response.setMoreInfo("http://localhost:8080/lodging/DisableLodging/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	public Response<Boolean> enableLodging(Integer lodgingId) {
		Lodging lodgingEntity = this.iLodgingRepository.findById(lodgingId).get();
		Response<Boolean> response = new Response<>();
		if (lodgingEntity != null) {
			if (lodgingEntity.getState() == false) {
				lodgingEntity.setState(true);
				this.iLodgingRepository.save(lodgingEntity);
				response.setStatus(200);
				response.setUserMessage("Hospedaje habilitado con éxito");
				response.setMoreInfo("http://localhost:8080/lodging/EnableLodging/{id}");
				response.setData(true);
			} else {
				response.setStatus(500);
				response.setUserMessage("El hospedaje ya esta habilitado");
				response.setMoreInfo("http://localhost:8080/lodging/EnableLodging/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	public GenericPageableResponse findAllLodgingBytState(boolean state, Pageable pageable) {
		Page<Lodging> lodgingPage = this.iLodgingRepository.LstLodgingByState(state, pageable);
		if (lodgingPage.isEmpty())
			return GenericPageableResponse.emptyResponse("No se encuentran hospedajes relacionados a este estado");
		return this.validatePageList(lodgingPage);
	}

	@Override
	public Response<List<LodgingQueryDTO>> findLodgingByStateByRuta(boolean state, Integer rutaId) {
		List<Lodging> lodgingEntity = iLodgingRepository.findLodgingByStateByRuta(state, rutaId);
		Response<List<LodgingQueryDTO>> response = new Response<>();
		if (lodgingEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Hospedajes no encontrados");
			response.setMoreInfo("http://localhost:8080/lodging/ConsultLodgingByStateByRuta/{state}/{rutaId}");
			response.setData(null);
		} else {
			List<LodgingQueryDTO> lodgingDTOs = lodgingEntity.stream()
					.map(lodging -> modelMapper.map(lodging, LodgingQueryDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Hospedajes encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/lodging/ConsultLodgingByStateByRuta/{state}/{rutaId}");
			response.setData(lodgingDTOs);
		}
		return response;
	}

	private GenericPageableResponse validatePageList(Page<Lodging> lodgingPage) {
		List<LodgingCommandDTO> lodgingDTOS = lodgingPage.stream().map(x -> modelMapper.map(x, LodgingCommandDTO.class))
				.collect(Collectors.toList());
		return PageableUtils.createPageableResponse(lodgingPage, lodgingDTOS);
	}

}
