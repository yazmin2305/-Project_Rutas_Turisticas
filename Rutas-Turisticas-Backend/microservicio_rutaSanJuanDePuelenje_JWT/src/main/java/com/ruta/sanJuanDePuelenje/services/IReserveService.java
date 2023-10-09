package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.ReserveDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;


public interface IReserveService {
	
	public Response<List<ReserveDTO>> findAllReserve();
	
	public Response<ReserveDTO> findByReserveId(Integer reserveId);
	
	public Response<ReserveDTO> saveReserve(ReserveDTO reserve);
	
	public Response<ReserveDTO> updateReserve(Integer reserveId, ReserveDTO reserve);
	
	public Response<Boolean> disableReserve(Integer reserveId);
	
	public Response<Boolean> deleteReserve(Integer reserveId);
	
	public Response<List<ReserveDTO>> findReservesByUser(Integer reserveId);

}
