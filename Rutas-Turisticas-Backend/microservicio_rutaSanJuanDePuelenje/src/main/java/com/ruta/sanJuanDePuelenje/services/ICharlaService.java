package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Charla;

public interface ICharlaService {
	
	public List<Charla> findAll();
	
	public Charla findByCharlaId(Integer idCharla);
	
	public Charla saveCharla(Charla charla);
	
	public Charla updateCharla(Integer idCharla, Charla charla);
	
	public Boolean deleteCharla(Integer idCharla);
}
