package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Lunch;

public interface ILunchService {
	
	public List<Lunch> findAll();
	
	public Lunch findByLunchId(Integer idLunch);
	
	public Lunch saveLunch(Lunch lunch);
	
	public Lunch updateLunch(Integer idlunch, Lunch lunch);
	
	public Boolean deleteLunch(Integer idLunch);
	

}
