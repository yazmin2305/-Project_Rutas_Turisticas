package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.RutaCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.RutaQueryDTO;

public interface IRutaService {
	public Response<List<RutaQueryDTO>> findAllRutas();
	
	public Response<RutaQueryDTO> findByRutaId(Integer RutaId);
	
	public Response<RutaCommandDTO> saveRuta(RutaCommandDTO ruta);
	
	public Response<RutaCommandDTO> updateRuta(Integer rutaId, RutaCommandDTO ruta);
	
	public Response<Boolean> disableRuta(Integer rutaId);
	
	public Response<List<RutaQueryDTO>> findAllRutasBytState(boolean state);
}
