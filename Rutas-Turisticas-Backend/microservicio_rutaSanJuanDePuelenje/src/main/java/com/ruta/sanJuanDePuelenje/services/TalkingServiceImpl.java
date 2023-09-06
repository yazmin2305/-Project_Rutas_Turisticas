package com.ruta.sanJuanDePuelenje.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<TalkingDTO> findAllTalking() {
		List<Talking> talkingEntity = iTalkingRepository.findAll();
		List<TalkingDTO> talkingDTOs = new ArrayList<>();
		talkingDTOs = talkingEntity.stream().map(talking -> modelMapper.map(talking, TalkingDTO.class)).collect(Collectors.toList());
		return talkingDTOs;
	}

	@Override
	public TalkingDTO findByTalkingId(Integer talkingId) {
		Talking talking = iTalkingRepository.findById(talkingId).orElse(null);
		TalkingDTO talkingDTO = modelMapper.map(talking, TalkingDTO.class);
		return talkingDTO;
	}

	@Override
	public TalkingDTO saveTalking(TalkingDTO talking) {
		Talking talkingEntity  = this.modelMapper.map(talking, Talking.class);
		talkingEntity.setState(true);
		Talking objTalking = this.iTalkingRepository.save(talkingEntity);
		TalkingDTO talkingDTO = this.modelMapper.map(objTalking, TalkingDTO.class);
		return talkingDTO;
	}

	@Override
	public TalkingDTO updateTalking(Integer talkingId, TalkingDTO talking) {
		Talking talkingEntity = this.modelMapper.map(talking, Talking.class);
		TalkingDTO talkingDTO = this.findByTalkingId(talkingId);
		Talking talkingEntity1 = this.modelMapper.map(talkingDTO, Talking.class);
		talkingEntity1.setName(talkingEntity.getName());
		talkingEntity1.setDescription(talkingEntity.getDescription());
		talkingEntity1.setDuration(talkingEntity.getDuration());
		talkingEntity1.setAvailability(talkingEntity.getAvailability());
		talkingEntity1.setMaxAmountPerson(talkingEntity.getMaxAmountPerson());
		talkingEntity1.setUnitPrice(talkingEntity.getUnitPrice());
		talkingEntity1.setTotalPrice(talkingEntity.getTotalPrice());
		talkingEntity1.setState(talkingEntity.getState());
		talkingEntity1.setFinca(talkingEntity.getFinca());
		talkingEntity1.setLstReserve(talkingEntity.getLstReserve());
		return talkingDTO;
	}

	@Override
	public Boolean disableTalking(Integer talkingId) {
		TalkingDTO talkingDTO = this.findByTalkingId(talkingId);
		Talking talkingEntity = this.modelMapper.map(talkingDTO, Talking.class);
		if (talkingEntity != null) {
			talkingEntity.setState(false);
			this.iTalkingRepository.save(talkingEntity);
			return true;
		}
		return false;
	}

}
