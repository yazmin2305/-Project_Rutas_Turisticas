package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.TalkingDTO;


public interface ITalkingService {
	
	public Response<List<TalkingDTO>> findAllTalking();
	
	public Response<TalkingDTO> findByTalkingId(Integer talkingId);
	
	public Response<TalkingDTO> saveTalking(TalkingDTO talking);
	
	public Response<TalkingDTO> updateTalking(Integer talkingId, TalkingDTO talking);
	
	public Response<Boolean> disableTalking(Integer talkingId);
	
	public Response<List<TalkingDTO>> findAllTalkingDisabled();
	
	public Response<List<TalkingDTO>> findAllTalkingBytState(boolean state);
}
