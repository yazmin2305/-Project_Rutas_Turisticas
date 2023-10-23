package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.FestivalDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;

public interface IFestivalService {
	
	public Response<List<FestivalDTO>> findAllFestival();
	
	public Response<FestivalDTO> findByFestivalId(Integer festivalId);
	
	public Response<FestivalDTO> saveFestival(FestivalDTO festival);
	
	public Response<FestivalDTO> updateFestival(Integer festivalId, FestivalDTO festival);
	
	public Response<Boolean> disableFestival(Integer festivalId);
	
	public Response<List<FestivalDTO>> findAllFestivalBytState(boolean state);

	//Response<FestivalDTO> saveFestival(FestivalDTO festival, MultipartFile imagen);
}
