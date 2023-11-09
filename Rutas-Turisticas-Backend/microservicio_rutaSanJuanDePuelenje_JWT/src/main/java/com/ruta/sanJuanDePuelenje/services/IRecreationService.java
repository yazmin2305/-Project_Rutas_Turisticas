package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.RecreationCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.RecreationQueryDTO;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

public interface IRecreationService {
	
	public Response<List<RecreationQueryDTO>> findAllRecreation();
	
	public Response<RecreationQueryDTO> findByRecreationId(Integer recreationId);
	
	public Response<RecreationCommandDTO> saveRecreation(RecreationCommandDTO recreation);
	
	public Response<RecreationQueryDTO> updateRecreation(Integer recreationId, RecreationCommandDTO recreation);
	
	public Response<Boolean> disableRecreation(Integer recreationId);
	
	public Response<Boolean> enableRecreation(Integer recreationId);
	
	public GenericPageableResponse findAllRecreationBytState(boolean state, Pageable pageable);
}
