package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.LunchCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.LunchQueryDTO;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

public interface ILunchService {
	
	public Response<List<LunchCommandDTO>> findAllLunch();
	
	public Response<LunchCommandDTO> findByLunchId(Integer lunchId);
	
	public Response<LunchCommandDTO> saveLunch(LunchCommandDTO lunch);
	
	public Response<LunchQueryDTO> updateLunch(Integer lunchId, LunchCommandDTO lunch);
	
	public Response<Boolean> disableLunch(Integer lunchId);
	
	public Response<Boolean> enableLunch(Integer lunchId);
	
	public GenericPageableResponse findAllLunchBytState(boolean state, Pageable pageable);
	
	public Response<List<LunchQueryDTO>> findAllLunchBytStateByRuta(boolean state, Integer rutaId);
	
	public Response<List<LunchQueryDTO>> findAllLunchByRuta(Integer rutaId);
	
}
