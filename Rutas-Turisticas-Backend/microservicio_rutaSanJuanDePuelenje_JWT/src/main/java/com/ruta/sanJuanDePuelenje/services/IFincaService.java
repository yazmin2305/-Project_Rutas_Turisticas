package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.FincaCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.FincaQueryDTO;

public interface IFincaService {
	
	public Response<List<FincaQueryDTO>> findAllFincas();
	
	public Response<FincaQueryDTO> findByFincaId(Integer fincaId);
	
	public Response<FincaCommandDTO> saveFinca(FincaCommandDTO fincaDTO);
	
	public Response<FincaQueryDTO> updateFinca(Integer fincaId, FincaCommandDTO fincaDTO);
	
	public Response<Boolean> disableFinca(Integer fincaId);
	
	public Response<List<FincaQueryDTO>> findAllFincaBytState(boolean state);
}
