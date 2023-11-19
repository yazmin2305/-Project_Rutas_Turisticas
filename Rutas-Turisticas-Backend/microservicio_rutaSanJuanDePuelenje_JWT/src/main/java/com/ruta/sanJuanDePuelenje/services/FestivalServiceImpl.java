package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.FestivalCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.FestivalQueryDTO;
import com.ruta.sanJuanDePuelenje.models.Festival;
import com.ruta.sanJuanDePuelenje.repository.IFestivalRepository;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;
import com.ruta.sanJuanDePuelenje.util.PageableUtils;

@Service
public class FestivalServiceImpl implements IFestivalService {
	@Autowired
	private IFestivalRepository iFestivalRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional(readOnly = true)
	public Response<List<FestivalCommandDTO>> findAllFestival() {
		List<Festival> festivalEntity = iFestivalRepository.findAll();
		Response<List<FestivalCommandDTO>> response = new Response<>();
		if (festivalEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Festivales no encontrados");
			response.setMoreInfo("http://localhost:8080/festival/ConsultAllFestival");
			response.setData(null);
		} else {
			List<FestivalCommandDTO> festivalDTO = festivalEntity.stream()
					.map(festival -> modelMapper.map(festival, FestivalCommandDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Festivales encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/festival/ConsultAllFestival");
			response.setData(festivalDTO);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<FestivalQueryDTO>> findAllFestivalBytRuta(Integer rutaId) {
		List<Festival> festivalEntity = this.iFestivalRepository.findAllFestivalesByRuta(rutaId);
		Response<List<FestivalQueryDTO>> response = new Response<>();
		if (festivalEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Festivales no encontrados");
			response.setMoreInfo("http://localhost:8080/festival/ConsultAllFestival");
			response.setData(null);
		} else {
			List<FestivalQueryDTO> festivalDTO = festivalEntity.stream()
					.map(festival -> modelMapper.map(festival, FestivalQueryDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Festivales encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/festival/ConsultAllFestival");
			response.setData(festivalDTO);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<FestivalCommandDTO> findByFestivalId(Integer festivalId) {
		Festival festival = iFestivalRepository.findById(festivalId).orElse(null);
		Response<FestivalCommandDTO> response = new Response<>();
		if (festival == null) {
			response.setStatus(404);
			response.setUserMessage("Festival no encontrado");
			response.setMoreInfo("http://localhost:8080/festival/ConsultFestivalById/{id}");
			response.setData(null);
		} else {
			FestivalCommandDTO festivalDTO = modelMapper.map(festival, FestivalCommandDTO.class);
			response.setStatus(200);
			response.setUserMessage("Festival encontrado con éxito");
			response.setMoreInfo("http://localhost:8080/festival/ConsultFestivalById/{id}");
			response.setData(festivalDTO);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<FestivalCommandDTO> saveFestival(FestivalCommandDTO festival) {
		Response<FestivalCommandDTO> response = new Response<>();
		if (festival != null) {
			Festival festivalEntity = this.modelMapper.map(festival, Festival.class);
			festivalEntity.setState(true);
			Festival objFestival = this.iFestivalRepository.save(festivalEntity);
			FestivalCommandDTO festivalDTO = this.modelMapper.map(objFestival, FestivalCommandDTO.class);
			response.setStatus(200);
			response.setUserMessage("Festival creado con éxito");
			response.setMoreInfo("http://localhost:8080/festival/SaveFestival");
			response.setData(festivalDTO);
		} else {
			response.setStatus(500);
			response.setUserMessage("Error al crear el festival");
			response.setMoreInfo("http://localhost:8080/festival/SaveFestival");
			response.setData(null);
		}

		return response;
	}

	@Override
	@Transactional
	public Response<FestivalQueryDTO> updateFestival(Integer festivalId, FestivalCommandDTO festival) {
		Response<FestivalQueryDTO> response = new Response<>();
		Optional<Festival> optionalFestival = this.iFestivalRepository.findById(festivalId); 
		if (optionalFestival.isPresent()) {
			Festival festivalEntity1 = optionalFestival.get();
			Festival festivalEntity = this.modelMapper.map(festival, Festival.class);
			festivalEntity1.setName(festivalEntity.getName());
			festivalEntity1.setDescription(festivalEntity.getDescription());
			festivalEntity1.setDate(festivalEntity.getDate());
			festivalEntity1.setFinca(festivalEntity.getFinca());
			this.iFestivalRepository.save(festivalEntity1);
			FestivalQueryDTO festivalDTO = this.modelMapper.map(festivalEntity1, FestivalQueryDTO.class);
			response.setStatus(200);
			response.setUserMessage("Festival actualizado con éxito");
			response.setMoreInfo("http://localhost:8080/festival/UpdateFestival/{id}");
			response.setData(festivalDTO);
		} else {
			response.setStatus(500);
			response.setUserMessage("El festival no se puede actualizar");
			response.setMoreInfo("http://localhost:8080/festival/UpdateFestival/{id}");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<Boolean> disableFestival(Integer festivalId) {
		Festival festivalEntity = this.iFestivalRepository.findById(festivalId).get();
		Response<Boolean> response = new Response<>();
		if (festivalEntity != null) {
			if (festivalEntity.getState() == true) {
				festivalEntity.setState(false);
				this.iFestivalRepository.save(festivalEntity);
				response.setStatus(200);
				response.setUserMessage("Festival deshabilitado con éxito");
				response.setMoreInfo("http://localhost:8080/festival/DisableFestival/{id}");
				response.setData(true);
			} else {
				response.setStatus(500);
				response.setUserMessage("El festival ya esta deshabilitado");
				response.setMoreInfo("http://localhost:8080/user/DisableFestival/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	@Transactional
	public Response<Boolean> enableFestival(Integer festivalId) {
		Festival festivalEntity = this.iFestivalRepository.findById(festivalId).get();
		Response<Boolean> response = new Response<>();
		if (festivalEntity != null) {
			if (festivalEntity.getState() == false) {
				festivalEntity.setState(true);
				this.iFestivalRepository.save(festivalEntity);
				response.setStatus(200);
				response.setUserMessage("Festival habilitado con éxito");
				response.setMoreInfo("http://localhost:8080/festival/EnableFestival/{id}");
				response.setData(true);
			} else {
				response.setStatus(500);
				response.setUserMessage("El festival ya esta habilitado");
				response.setMoreInfo("http://localhost:8080/festival/EnableFestival/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public GenericPageableResponse findAllFestivalByState(boolean state, Pageable pageable) {
		Page<Festival> festivalesPage = this.iFestivalRepository.LstFestivalByState(state, pageable);
		if (festivalesPage.isEmpty())
			return GenericPageableResponse.emptyResponse("No se encuentran festivales relacionados a este estado");
		return this.validatePageList(festivalesPage);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Response<List<FestivalQueryDTO>> findFestivalByStateByRuta(boolean state, Integer rutaId) {
		List<Festival> festivalEntity = iFestivalRepository.findFestivalByStateByRuta(state, rutaId);
		Response<List<FestivalQueryDTO>> response = new Response<>();
		if (festivalEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Festivales no encontrados");
			response.setMoreInfo("http://localhost:8080/festival/ConsultAllFestivalByStateByRuta/{state}/{idRuta}");
			response.setData(null);
		} else {
			List<FestivalQueryDTO> festivalDTO = festivalEntity.stream()
					.map(festival -> modelMapper.map(festival, FestivalQueryDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Festivales encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/festival/ConsultAllFestivalByStateByRuta/{state}/{idRuta}");
			response.setData(festivalDTO);
		}
		return response;
	}

	private GenericPageableResponse validatePageList(Page<Festival> festivalPage) {
		List<FestivalCommandDTO> festivalDTOS = festivalPage.stream().map(x -> modelMapper.map(x, FestivalCommandDTO.class))
				.collect(Collectors.toList());
		return PageableUtils.createPageableResponse(festivalPage, festivalDTOS);
	}

}
