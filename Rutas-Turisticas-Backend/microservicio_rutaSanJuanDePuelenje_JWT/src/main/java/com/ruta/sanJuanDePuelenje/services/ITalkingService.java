package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.TalkingCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.TalkingQueryDTO;


public interface ITalkingService {
	
	public Response<List<TalkingQueryDTO>> findAllTalking();
	
	public Response<TalkingQueryDTO> findByTalkingId(Integer talkingId);
	
	public Response<TalkingCommandDTO> saveTalking(TalkingCommandDTO talking);
	
	public Response<TalkingQueryDTO> updateTalking(Integer talkingId, TalkingCommandDTO talking);
	
	public Response<Boolean> disableTalking(Integer talkingId);
	
	public Response<List<TalkingQueryDTO>> findAllTalkingDisabled();
	
	public Response<List<TalkingQueryDTO>> findAllTalkingBytState(boolean state);
}
