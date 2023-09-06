package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.RecreationDTO;

public interface IRecreationService {
	
	public List<RecreationDTO> findAllRecreation();
	
	public RecreationDTO findByRecreationId(Integer recreationId);
	
	public RecreationDTO saveRecreation(RecreationDTO recreation);
	
	public RecreationDTO updateRecreation(Integer recreationId, RecreationDTO recreation);
	
	public Boolean disableRecreation(Integer recreationId);
}
