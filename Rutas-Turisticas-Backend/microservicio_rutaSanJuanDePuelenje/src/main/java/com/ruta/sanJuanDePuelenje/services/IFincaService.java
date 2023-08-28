package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Finca;

public interface IFincaService {
	
public List<Finca> findAll();
	
	public Finca findByFincaId(Integer idFinca);
	
	public Finca saveFinca(Finca finca);
	
	public Finca updateFinca(Integer idFinca, Finca charla);
	
	public Boolean deleteFinca(Integer idFinca);

}
