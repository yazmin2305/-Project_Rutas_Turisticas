package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.FincaDTO;

public interface IFincaService {
	
	public List<FincaDTO> findAllFincas();
	
	public FincaDTO findByFincaId(Integer fincaId);
	
	public FincaDTO saveFinca(FincaDTO fincaDTO);
	
	public FincaDTO updateFinca(Integer fincaId, FincaDTO fincaDTO);
	
	public Boolean disableFinca(Integer fincaId);
}
