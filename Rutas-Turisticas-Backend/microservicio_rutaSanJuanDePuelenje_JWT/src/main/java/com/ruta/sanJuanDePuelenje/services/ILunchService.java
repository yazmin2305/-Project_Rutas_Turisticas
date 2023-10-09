package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.LunchDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;

public interface ILunchService {
	
	public Response<List<LunchDTO>> findAllLunch();
	
	public Response<LunchDTO> findByLunchId(Integer lunchId);
	
	public Response<LunchDTO> saveLunch(LunchDTO lunch);
	
	public Response<LunchDTO> updateLunch(Integer lunchId, LunchDTO lunch);
	
	public Response<Boolean> disableLunch(Integer lunchId);
	
	public Response<List<LunchDTO>> findAllLunchBytState(boolean state);
	
}
