package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.FincaCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.FincaQueryDTO;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

public interface IFincaService {
	
	public Response<List<FincaQueryDTO>> findAllFincas();
	
	public Response<FincaQueryDTO> findByFincaId(Integer fincaId);
	
	public Response<FincaCommandDTO> saveFinca(FincaCommandDTO fincaDTO);
	
	public Response<FincaQueryDTO> updateFinca(Integer fincaId, FincaCommandDTO fincaDTO);
	
	public Response<Boolean> disableFinca(Integer fincaId);
	
	public Response<Boolean> enableFinca(Integer fincaId);
	
	public GenericPageableResponse findAllFincaBytState(boolean state, Pageable pageable);
	
	public Response<List<FincaQueryDTO>> findAllFincasBytRuta(Integer rutaId);
}
