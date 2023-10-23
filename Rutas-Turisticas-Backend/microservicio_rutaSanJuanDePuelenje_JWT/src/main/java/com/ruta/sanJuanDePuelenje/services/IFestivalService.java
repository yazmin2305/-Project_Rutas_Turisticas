package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Query.FestivalQueryDTO;
import com.ruta.sanJuanDePuelenje.DTO.Command.FestivalCommandDTO;
public interface IFestivalService {
	
	public Response<List<FestivalQueryDTO>> findAllFestival();
	
	public Response<FestivalQueryDTO> findByFestivalId(Integer festivalId);
	
	public Response<FestivalCommandDTO> saveFestival(FestivalCommandDTO festival);
	
	public Response<FestivalQueryDTO> updateFestival(Integer festivalId, FestivalCommandDTO festival);
	
	public Response<Boolean> disableFestival(Integer festivalId);
	
	public Response<List<FestivalQueryDTO>> findAllFestivalBytState(boolean state);

	//Response<FestivalDTO> saveFestival(FestivalDTO festival, MultipartFile imagen);
}
