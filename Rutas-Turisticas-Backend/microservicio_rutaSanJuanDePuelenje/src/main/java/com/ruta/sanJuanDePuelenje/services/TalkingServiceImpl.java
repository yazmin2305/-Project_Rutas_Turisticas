package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ruta.sanJuanDePuelenje.models.Talking;
import com.ruta.sanJuanDePuelenje.repository.ITalkingRepository;

public class TalkingServiceImpl implements ITalkingService{

	@Autowired
	private ITalkingRepository iTalkingRepository;
	
	@Override
	public List<Talking> findAllTalking() {
		return (List<Talking>) iTalkingRepository.findAll();
	}

	@Override
	public Talking findByTalkingId(Integer talkingId) {
		Talking talking = iTalkingRepository.findById(talkingId).orElse(null);
		return talking;
	}

	@Override
	public Talking saveTalking(Talking talking) {
		return iTalkingRepository.save(talking);
	}

	@Override
	public Talking updateTalking(Integer talkingId, Talking talking) {
		Talking talking1 = this.findByTalkingId(talkingId);
		talking1.setName(talking.getName());
		talking1.setDescription(talking.getDescription());
		talking1.setDuration(talking.getDuration());
		talking1.setAvailability(talking.getAvailability());
		talking1.setMaxAmountPerson(talking.getMaxAmountPerson());
		talking1.setUnitPrice(talking.getUnitPrice());
		talking1.setTotalPrice(talking.getTotalPrice());
		talking1.setState(talking.getState());
		talking1.setFinca(talking.getFinca());
		talking1.setLstReserve(talking.getLstReserve());
		return talking1;
	}

	@Override
	public void disableTalking(Integer talkingId) {
		Talking talking = this.findByTalkingId(talkingId);
		talking.setState(false);
	}

}
