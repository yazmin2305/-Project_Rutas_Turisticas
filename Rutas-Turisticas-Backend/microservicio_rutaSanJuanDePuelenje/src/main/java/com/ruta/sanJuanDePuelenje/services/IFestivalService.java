package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.FestivalDTO;

public interface IFestivalService {
	
	public List<FestivalDTO> findAllFestival();
	
	public FestivalDTO findByFestivalId(Integer festivalId);
	
	public FestivalDTO saveFestival(FestivalDTO festival);
	
	public FestivalDTO updateFestival(Integer festivalId, FestivalDTO festival);
	
	public Boolean disableFestival(Integer festivalId);
}
