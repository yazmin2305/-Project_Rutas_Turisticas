package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.RecreationCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.RecreationQueryDTO;

public interface IRecreationService {
	
	public Response<List<RecreationQueryDTO>> findAllRecreation();
	
	public Response<RecreationQueryDTO> findByRecreationId(Integer recreationId);
	
	public Response<RecreationCommandDTO> saveRecreation(RecreationCommandDTO recreation);
	
	public Response<RecreationQueryDTO> updateRecreation(Integer recreationId, RecreationCommandDTO recreation);
	
	public Response<Boolean> disableRecreation(Integer recreationId);
	
	public Response<List<RecreationQueryDTO>> findAllRecreationBytState(boolean state);
}
