package com.ruta.sanJuanDePuelenje.services;

import org.springframework.data.domain.Pageable;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.ReserveCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.ReserveQueryDTO;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;


public interface IReserveService {
	
	public GenericPageableResponse findAllReserveByRuta(Integer rutaId, Pageable pageable);
	
	public Response<ReserveQueryDTO> findReserveById(Integer reserveId);
	
	public Response<ReserveCommandDTO> saveReserve(ReserveCommandDTO reserve);
	
	public Response<ReserveQueryDTO> updateReserve(Integer reserveId, ReserveCommandDTO reserve);
	
	public Response<Boolean> disableReserve(Integer reserveId);
	
	public Response<Boolean> enableReserve(Integer reserveId);
	
	public Response<Boolean> deleteReserve(Integer reserveId);
	
	public GenericPageableResponse findReservesByUser(Integer userId, Pageable pageable);

}
