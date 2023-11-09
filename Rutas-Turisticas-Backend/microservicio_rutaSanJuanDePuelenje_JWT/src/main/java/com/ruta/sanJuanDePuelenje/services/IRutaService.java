package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.RutaCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.RutaQueryDTO;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

public interface IRutaService {
	
	public Response<List<RutaQueryDTO>> findAllRutas();
	
	public Response<RutaQueryDTO> findByRutaId(Integer RutaId);
	
	public Response<RutaCommandDTO> saveRuta(RutaCommandDTO ruta);
	
	public Response<RutaCommandDTO> updateRuta(Integer rutaId, RutaCommandDTO ruta);
	
	public Response<Boolean> disableRuta(Integer rutaId);
	
	public Response<Boolean> enableRuta(Integer rutaId);
	
	public GenericPageableResponse findAllRutasBytState(boolean state, Pageable pageable);
}
