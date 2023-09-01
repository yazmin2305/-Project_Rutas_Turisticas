package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Lunch;

public interface ILunchService {
	
	public List<Lunch> findAllLodging();
	
	public Lunch findByLunchId(Integer lunchId);
	
	public Lunch saveLunch(Lunch lunch);
	
	public Lunch updateLunch(Integer lunchId, Lunch lunch);
	
	public Boolean disableLunch(Integer lunchId);
}
