package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Festival;


public interface IFestivalService {
	
	public List<Festival> findAll();
	
	public Festival findByFestivalId(Integer idFestival);
	
	public Festival saveFestival(Festival festival);
	
	public Festival updateFestival(Integer idFestival, Festival festival);
	
	public Boolean deleteFestival(Integer idFestival);

}
