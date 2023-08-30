package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Talking;

public interface ITalkingService {
	
	public List<Talking> findAllTalking();
	
	public Talking findByTalkingId(Integer talkingId);
	
	public Talking saveTalking(Talking talking);
	
	public Talking updateTalking(Integer talkingId, Talking talking);
	
	public Talking disableTalking(Integer talkingId);
}
