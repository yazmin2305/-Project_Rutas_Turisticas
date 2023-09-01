package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Lodging;

public interface ILodgingService {
	
	public List<Lodging> findAllLodging();
	
	public Lodging findByLodgingId(Integer lodgingId);
	
	public Lodging saveLodging(Lodging lodging);
	
	public Lodging updateLodging(Integer lodgingId, Lodging lodging);
	
	public Boolean disableLodging(Integer lodgingId);
}
