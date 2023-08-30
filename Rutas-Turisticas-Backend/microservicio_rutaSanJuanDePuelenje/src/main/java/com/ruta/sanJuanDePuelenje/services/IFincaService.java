package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Finca;

public interface IFincaService {
	
	public List<Finca> findAllFincas();
	
	public Finca findByFincaId(Integer fincaId);
	
	public Finca saveFinca(Finca finca);
	
	public Finca updateFinca(Integer fincaId, Finca finca);
	
	public Finca disableFinca(Integer fincaId);
}
