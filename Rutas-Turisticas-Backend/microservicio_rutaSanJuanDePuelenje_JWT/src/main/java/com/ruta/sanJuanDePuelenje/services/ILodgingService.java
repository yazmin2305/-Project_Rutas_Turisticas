package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.LodgingCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.LodgingQueryDTO;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

public interface ILodgingService {

	public Response<List<LodgingCommandDTO>> findAllLodging();

	public Response<LodgingCommandDTO> findByLodgingId(Integer lodgingId);

	public Response<LodgingCommandDTO> saveLodging(LodgingCommandDTO lodging);

	public Response<LodgingQueryDTO> updateLodging(Integer lodgingId, LodgingCommandDTO lodging);

	public Response<Boolean> disableLodging(Integer lodgingId);

	public Response<Boolean> enableLodging(Integer lodgingId);

	public GenericPageableResponse findAllLodgingBytState(boolean state, Pageable pageable);

	public Response<List<LodgingQueryDTO>> findLodgingByStateByRuta(boolean state, Integer rutaId);

	public Response<List<LodgingQueryDTO>> findAllLodgingBytRuta(Integer rutaId);
}
