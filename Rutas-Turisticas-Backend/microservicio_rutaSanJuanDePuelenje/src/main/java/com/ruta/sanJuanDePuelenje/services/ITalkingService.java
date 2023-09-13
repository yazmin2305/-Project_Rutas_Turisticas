package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.TalkingDTO;


public interface ITalkingService {
	
	public List<TalkingDTO> findAllTalking();
	
	public TalkingDTO findByTalkingId(Integer talkingId);
	
	public TalkingDTO saveTalking(TalkingDTO talking);
	
	public TalkingDTO updateTalking(Integer talkingId, TalkingDTO talking);
	
	public Boolean disableTalking(Integer talkingId);
	
	public List<TalkingDTO> findAllTalkingDisabled();
	
	public List<TalkingDTO> findAllTalkingBytState(boolean state);
}
