package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Festival;

public interface IFestivalService {
	
	public List<Festival> findAllFestival();
	
	public Festival findByFestivalId(Integer festivalId);
	
	public Festival saveFestival(Festival festival);
	
	public Festival updateFestival(Integer festivalId, Festival festival);
	
	public Festival disableFestival(Integer festivalId);
}
