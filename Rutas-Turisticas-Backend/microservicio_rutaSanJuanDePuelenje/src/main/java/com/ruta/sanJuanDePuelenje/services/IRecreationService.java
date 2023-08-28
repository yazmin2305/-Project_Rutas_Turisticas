package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Recreation;


public interface IRecreationService {
	
	public List<Recreation> findAll();
	
	public Recreation findByRecreationId(Integer idRecreation);
	
	public Recreation saveRecreation(Recreation recreation);
	
	public Recreation updateRecreation(Integer idRecreation, Recreation recreation);
	
	public Boolean deleteRecreation(Integer idRecreation);
}
