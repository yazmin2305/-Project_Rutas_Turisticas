package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.TalkingCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.TalkingQueryDTO;
import com.ruta.sanJuanDePuelenje.models.Talking;
import com.ruta.sanJuanDePuelenje.repository.ITalkingRepository;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;
import com.ruta.sanJuanDePuelenje.util.PageableUtils;

@Service
public class TalkingServiceImpl implements ITalkingService{

	@Autowired
	private ITalkingRepository iTalkingRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional(readOnly = true)
	public Response<List<TalkingQueryDTO>> findAllTalking() {
		List<Talking> talkingEntity = iTalkingRepository.findAll();
		Response<List<TalkingQueryDTO>> response = new Response<>();
		if(talkingEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Charlas no encontradas");
			response.setMoreInfo("http://localhost:8080/talking/ConsultAllTalking");
			response.setData(null);
		}else {
			List<TalkingQueryDTO> talkingDTOs = talkingEntity.stream().map(talking -> modelMapper.map(talking, TalkingQueryDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Charlas encontradas con éxito");
			response.setMoreInfo("http://localhost:8080/talking/ConsultAllTalking");
			response.setData(talkingDTOs);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<TalkingQueryDTO> findByTalkingId(Integer talkingId) {
		Talking talking = iTalkingRepository.findById(talkingId).orElse(null);
		Response<TalkingQueryDTO> response = new Response<>();
		if(talking == null) {
			response.setStatus(404);
			response.setUserMessage("Charla no encontrada");
			response.setMoreInfo("http://localhost:8080/talking/ConsultById/{id}");
			response.setData(null);
		}else {
			TalkingQueryDTO talkingDTO = modelMapper.map(talking, TalkingQueryDTO.class);
			response.setStatus(200);
			response.setUserMessage("Charla encontrada con éxito");
			response.setMoreInfo("http://localhost:8080/talking/ConsultById/{id}");
			response.setData(talkingDTO);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<TalkingCommandDTO> saveTalking(TalkingCommandDTO talking) {
		Response<TalkingCommandDTO> response = new Response<>();
		if(talking != null) {
			Talking talkingEntity  = this.modelMapper.map(talking, Talking.class);
			talkingEntity.setState(true);
			Talking objTalking = this.iTalkingRepository.save(talkingEntity);
			TalkingCommandDTO talkingDTO = this.modelMapper.map(objTalking, TalkingCommandDTO.class);
			response.setStatus(200);
			response.setUserMessage("Charla creada con éxito");
			response.setMoreInfo("http://localhost:8080/talking/SaveTalking");
			response.setData(talkingDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("Error al crear la charla");
			response.setMoreInfo("http://localhost:8080/talking/SaveTalking");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<TalkingQueryDTO> updateTalking(Integer talkingId, TalkingCommandDTO talking) {
		Response<TalkingQueryDTO> response = new Response<>();
		if(talking != null && talkingId != null) {
			Talking talkingEntity = this.modelMapper.map(talking, Talking.class);
			Talking talkingEntity1 = this.iTalkingRepository.findById(talkingId).get();
			talkingEntity1.setName(talkingEntity.getName());
			talkingEntity1.setDescription(talkingEntity.getDescription());
			talkingEntity1.setDuration(talkingEntity.getDuration());
			talkingEntity1.setAvailability(talkingEntity.getAvailability());
			talkingEntity1.setMaxAmountPerson(talkingEntity.getMaxAmountPerson());
			talkingEntity1.setState(talkingEntity.getState());
			talkingEntity1.setFinca(talkingEntity.getFinca());
//			talkingEntity1.setLstReserve(talkingEntity.getLstReserve());
			this.iTalkingRepository.save(talkingEntity1);
			TalkingQueryDTO talkingDTO = this.modelMapper.map(talkingEntity1, TalkingQueryDTO.class);
			response.setStatus(200);
			response.setUserMessage("Charla actualizada con éxito");
			response.setMoreInfo("http://localhost:8080/talking/UpdateTalking/{id}");
			response.setData(talkingDTO);		
		}else {
			response.setStatus(500);
			response.setUserMessage("Error al actualizar la charla");
			response.setMoreInfo("http://localhost:8080/talking/UpdateTalking/{id}");
			response.setData(null);		
		}
		return response;
	}

	@Override
	@Transactional
	public Response<Boolean> disableTalking(Integer talkingId) {
		Talking talkingEntity = this.iTalkingRepository.findById(talkingId).get();
		Response<Boolean> response = new Response<>();
		if (talkingEntity != null) {
			if(talkingEntity.getState() == true){
				talkingEntity.setState(false);
				this.iTalkingRepository.save(talkingEntity);
				response.setStatus(200);
				response.setUserMessage("Charla deshabilitada con éxito");
				response.setMoreInfo("http://localhost:8080/talking/DisableTalking/{id}");
				response.setData(true);
			}else {
				response.setStatus(500);
				response.setUserMessage("La charla ya esta deshabilitada");
				response.setMoreInfo("http://localhost:8080/talking/DisableTalking/{id}");
				response.setData(false);
			}
		}
		return response;
	}
	
	@Override
	@Transactional
	public Response<Boolean> enableTalking(Integer talkingId) {
		Talking talkingEntity = this.iTalkingRepository.findById(talkingId).get();
		Response<Boolean> response = new Response<>();
		if (talkingEntity != null) {
			if(talkingEntity.getState() == false){
				talkingEntity.setState(true);
				this.iTalkingRepository.save(talkingEntity);
				response.setStatus(200);
				response.setUserMessage("Charla habilitada con éxito");
				response.setMoreInfo("http://localhost:8080/talking/EnableTalking/{id}");
				response.setData(true);
			}else {
				response.setStatus(500);
				response.setUserMessage("La charla ya esta habilitada");
				response.setMoreInfo("http://localhost:8080/talking/EnableTalking/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public GenericPageableResponse findAllTalkingDisabled(Pageable pageable) {
		Page<Talking> talkingsPage = this.iTalkingRepository.LstTalkingDisabled(pageable);
		if (talkingsPage.isEmpty())
			return GenericPageableResponse.emptyResponse("No se encuentran charlas deshabilitadas");
		return this.validatePageList(talkingsPage);
	}

	@Override
	@Transactional(readOnly = true)
	public GenericPageableResponse findAllTalkingBytState(boolean state, Pageable pageable) {
		Page<Talking> talkingsPage = this.iTalkingRepository.LstTalkingByState(state, pageable);
		if (talkingsPage.isEmpty())
			return GenericPageableResponse.emptyResponse("No se encuentran charlas relacionadas a este estado");
		return this.validatePageList(talkingsPage);
	}
	
	private GenericPageableResponse validatePageList(Page<Talking> talkingPage){
        List<TalkingQueryDTO> talkingDTOS = talkingPage.stream().map(x->modelMapper.map(x, TalkingQueryDTO.class)).collect(Collectors.toList());
        return PageableUtils.createPageableResponse(talkingPage, talkingDTOS);
 }

}
