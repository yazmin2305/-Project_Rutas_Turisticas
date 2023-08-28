package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Lodging;


public interface ILodgingService {
	
	public List<Lodging> findAll();
	
	public Lodging findByLodgingId(Integer idLodging);
	
	public Lodging saveLodging(Lodging lodging);
	
	public Lodging updateLodging(Integer idLodging, Lodging lodging);
	
	public Boolean deleteLodging(Integer idLodging);

}
