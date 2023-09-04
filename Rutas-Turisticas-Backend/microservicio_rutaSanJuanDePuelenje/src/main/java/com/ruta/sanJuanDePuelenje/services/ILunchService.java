package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.LunchDTO;

public interface ILunchService {
	
	public List<LunchDTO> findAllLodging();
	
	public LunchDTO findByLunchId(Integer lunchId);
	
	public LunchDTO saveLunch(LunchDTO lunch);
	
	public LunchDTO updateLunch(Integer lunchId, LunchDTO lunch);
	
	public Boolean disableLunch(Integer lunchId);
}
