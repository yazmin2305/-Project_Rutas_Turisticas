package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.RutaDTO;

public interface IRutaService {
	public Response<List<RutaDTO>> findAllRutas();
	
	public Response<RutaDTO> findByRutaId(Integer RutaId);
	
	public Response<RutaDTO> saveRuta(RutaDTO ruta);
	
	public Response<RutaDTO> updateRuta(Integer rutaId, RutaDTO ruta);
	
	public Response<Boolean> disableRuta(Integer rutaId);
	
	public Response<List<RutaDTO>> findAllRutasBytState(boolean state);
}
