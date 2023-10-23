package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.LodgingCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.LodgingQueryDTO;

public interface ILodgingService {
	
	public Response<List<LodgingQueryDTO>> findAllLodging();
	
	public Response<LodgingQueryDTO> findByLodgingId(Integer lodgingId);
	
	public Response<LodgingCommandDTO> saveLodging(LodgingCommandDTO lodging);
	
	public Response<LodgingQueryDTO> updateLodging(Integer lodgingId, LodgingCommandDTO lodging);
	
	public Response<Boolean> disableLodging(Integer lodgingId);
	
	public Response<List<LodgingQueryDTO>> findAllLodgingBytState(boolean state);
}
