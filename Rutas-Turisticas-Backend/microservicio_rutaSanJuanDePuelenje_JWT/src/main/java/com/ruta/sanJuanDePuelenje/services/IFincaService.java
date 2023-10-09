package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.FincaDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;

public interface IFincaService {
	
	public Response<List<FincaDTO>> findAllFincas();
	
	public Response<FincaDTO> findByFincaId(Integer fincaId);
	
	public Response<FincaDTO> saveFinca(FincaDTO fincaDTO);
	
	public Response<FincaDTO> updateFinca(Integer fincaId, FincaDTO fincaDTO);
	
	public Response<Boolean> disableFinca(Integer fincaId);
	
	public Response<List<FincaDTO>> findAllFincaBytState(boolean state);
}
