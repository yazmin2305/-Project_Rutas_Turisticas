package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.LodgingDTO;
import com.ruta.sanJuanDePuelenje.models.Lodging;

public interface ILodgingService {
	
	public List<LodgingDTO> findAllLodging();
	
	public LodgingDTO findByLodgingId(Integer lodgingId);
	
	public LodgingDTO saveLodging(LodgingDTO lodging);
	
	public LodgingDTO updateLodging(Integer lodgingId, LodgingDTO lodging);
	
	public Boolean disableLodging(Integer lodgingId);
}
