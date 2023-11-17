package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.ReserveCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.ReserveQueryDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.UserQueryDTO;


public interface IReserveService {
	
	public Response<List<ReserveQueryDTO>> findAllReserveByRuta(Integer rutaId);
	
	public Response<ReserveQueryDTO> findReserveById(Integer reserveId);
	
	public Response<ReserveCommandDTO> saveReserve(ReserveCommandDTO reserve);
	
	public Response<ReserveQueryDTO> updateReserve(Integer reserveId, ReserveCommandDTO reserve);
	
	public Response<Boolean> disableReserve(Integer reserveId);
	
	public Response<Boolean> deleteReserve(Integer reserveId);
	
	public Response<List<ReserveQueryDTO>> findReservesByUser(Integer reserveId);
	
	public Response<List<UserQueryDTO>> findAllUsersByRuta(Integer rutaId);

}
