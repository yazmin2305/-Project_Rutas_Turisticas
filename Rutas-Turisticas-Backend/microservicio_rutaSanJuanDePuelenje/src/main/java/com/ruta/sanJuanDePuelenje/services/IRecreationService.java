package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Recreation;

public interface IRecreationService {
	
	public List<Recreation> findAllLodging();
	
	public Recreation findByRecreationId(Integer recreationId);
	
	public Recreation saveRecreation(Recreation recreation);
	
	public Recreation updateRecreation(Integer recreationId, Recreation recreation);
	
	public void disableRecreation(Integer recreationId);
}
