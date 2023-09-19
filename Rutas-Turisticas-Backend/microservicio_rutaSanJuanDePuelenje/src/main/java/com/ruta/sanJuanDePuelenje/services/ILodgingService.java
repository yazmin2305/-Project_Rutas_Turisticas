package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.LodgingDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;

public interface ILodgingService {
	
	public Response<List<LodgingDTO>> findAllLodging();
	
	public Response<LodgingDTO> findByLodgingId(Integer lodgingId);
	
	public Response<LodgingDTO> saveLodging(LodgingDTO lodging);
	
	public Response<LodgingDTO> updateLodging(Integer lodgingId, LodgingDTO lodging);
	
	public Response<Boolean> disableLodging(Integer lodgingId);
	
	public Response<List<LodgingDTO>> findAllLodgingBytState(boolean state);
}
