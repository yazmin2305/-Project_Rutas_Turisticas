package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.TalkingDTO;
import com.ruta.sanJuanDePuelenje.models.Talking;
import com.ruta.sanJuanDePuelenje.repository.ITalkingRepository;

@Service
public class TalkingServiceImpl implements ITalkingService{

	@Autowired
	private ITalkingRepository iTalkingRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional(readOnly = true)
	public Response<List<TalkingDTO>> findAllTalking() {
		List<Talking> talkingEntity = iTalkingRepository.findAll();
		Response<List<TalkingDTO>> response = new Response<>();
		if(talkingEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Charlas no encontradas");
			response.setMoreInfo("http://localhost:8080/talking/ConsultAllTalking");
			response.setData(null);
		}else {
			List<TalkingDTO> talkingDTOs = talkingEntity.stream().map(talking -> modelMapper.map(talking, TalkingDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Charlas encontradas con éxito");
			response.setMoreInfo("http://localhost:8080/talking/ConsultAllTalking");
			response.setData(talkingDTOs);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<TalkingDTO> findByTalkingId(Integer talkingId) {
		Talking talking = iTalkingRepository.findById(talkingId).orElse(null);
		Response<TalkingDTO> response = new Response<>();
		if(talking == null) {
			response.setStatus(404);
			response.setUserMessage("Charla no encontrada");
			response.setMoreInfo("http://localhost:8080/talking/ConsultById/{id}");
			response.setData(null);
		}else {
			TalkingDTO talkingDTO = modelMapper.map(talking, TalkingDTO.class);
			response.setStatus(200);
			response.setUserMessage("Charla encontrada con éxito");
			response.setMoreInfo("http://localhost:8080/talking/ConsultById/{id}");
			response.setData(talkingDTO);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<TalkingDTO> saveTalking(TalkingDTO talking) {
		Response<TalkingDTO> response = new Response<>();
		if(talking != null) {
			Talking talkingEntity  = this.modelMapper.map(talking, Talking.class);
			talkingEntity.setState(true);
			Talking objTalking = this.iTalkingRepository.save(talkingEntity);
			TalkingDTO talkingDTO = this.modelMapper.map(objTalking, TalkingDTO.class);
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
	public Response<TalkingDTO> updateTalking(Integer talkingId, TalkingDTO talking) {
		Response<TalkingDTO> response = new Response<>();
		if(talking != null && talkingId != null) {
			Talking talkingEntity = this.modelMapper.map(talking, Talking.class);
			Talking talkingEntity1 = this.iTalkingRepository.findById(talkingId).get();
			talkingEntity1.setName(talkingEntity.getName());
			talkingEntity1.setDescription(talkingEntity.getDescription());
			talkingEntity1.setDuration(talkingEntity.getDuration());
			talkingEntity1.setAvailability(talkingEntity.getAvailability());
			talkingEntity1.setMaxAmountPerson(talkingEntity.getMaxAmountPerson());
			talkingEntity1.setUnitPrice(talkingEntity.getUnitPrice());
			talkingEntity1.setState(talkingEntity.getState());
			talkingEntity1.setFinca(talkingEntity.getFinca());
			talkingEntity1.setLstReserve(talkingEntity.getLstReserve());
			TalkingDTO talkingDTO = this.modelMapper.map(talkingEntity1, TalkingDTO.class);
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
	@Transactional(readOnly = true)
	public Response<List<TalkingDTO>> findAllTalkingDisabled() {
		List<Talking> talkingEntity = this.iTalkingRepository.LstTalkingDisabled();
		Response<List<TalkingDTO>> response = new Response<>();
		if(!talkingEntity.isEmpty()) {
			List<TalkingDTO> talkingDTO = talkingEntity.stream().map(talking -> modelMapper.map(talking, TalkingDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Charlas encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/talking/ConsultAllTalkingDisabled");
			response.setData(talkingDTO);
		}else {
			response.setStatus(404);
			response.setUserMessage("No existen charlas deshabilitadas");
			response.setMoreInfo("http://localhost:8080/talking/ConsultAllTalkingDisabled");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<TalkingDTO>> findAllTalkingBytState(boolean state) {
		List<Talking> talkingEntity = this.iTalkingRepository.LstTalkingByState(state);
		Response<List<TalkingDTO>> response = new Response<>();
		if(!talkingEntity.isEmpty()) {
			List<TalkingDTO> talkingDTO = talkingEntity.stream().map(talking -> modelMapper.map(talking, TalkingDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Charlas encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/talking/ConsultAllTalkingByState");
			response.setData(talkingDTO);
		}else {
			response.setStatus(404);
			response.setUserMessage("No existen charlas relacionadas a este estado");
			response.setMoreInfo("http://localhost:8080/talking/ConsultAllTalkingByState");
			response.setData(null);
		}
		return response;
	}

}
