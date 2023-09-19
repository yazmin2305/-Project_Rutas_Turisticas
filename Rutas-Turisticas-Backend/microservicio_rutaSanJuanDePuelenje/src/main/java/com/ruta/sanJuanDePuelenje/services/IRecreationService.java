package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.RecreationDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;

public interface IRecreationService {
	
	public Response<List<RecreationDTO>> findAllRecreation();
	
	public Response<RecreationDTO> findByRecreationId(Integer recreationId);
	
	public Response<RecreationDTO> saveRecreation(RecreationDTO recreation);
	
	public Response<RecreationDTO> updateRecreation(Integer recreationId, RecreationDTO recreation);
	
	public Response<Boolean> disableRecreation(Integer recreationId);
	
	public Response<List<RecreationDTO>> findAllRecreationBytState(boolean state);
}
