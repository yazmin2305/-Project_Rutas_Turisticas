package com.ruta.sanJuanDePuelenje.services;

import org.springframework.data.domain.Pageable;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.TalkingCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.TalkingQueryDTO;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;


public interface ITalkingService {
	
	public GenericPageableResponse findAllTalking(Pageable pageable);
	
	public Response<TalkingQueryDTO> findByTalkingId(Integer talkingId);
	
	public Response<TalkingCommandDTO> saveTalking(TalkingCommandDTO talking);
	
	public Response<TalkingQueryDTO> updateTalking(Integer talkingId, TalkingCommandDTO talking);
	
	public Response<Boolean> disableTalking(Integer talkingId);
	
	public Response<Boolean> enableTalking(Integer talkingId);
	
	public GenericPageableResponse findAllTalkingDisabled(Pageable pageable);
	
	public GenericPageableResponse findAllTalkingBytState(boolean state, Pageable pageable);
}
