package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.ReserveDTO;


public interface IReserveService {
	
	public List<ReserveDTO> findAllReserve();
	
	public ReserveDTO findByReserveId(Integer reserveId);
	
	public ReserveDTO saveReserve(ReserveDTO reserve);
	
	public ReserveDTO updateReserve(Integer reserveId, ReserveDTO reserve);
	
	public Boolean disableReserve(Integer reserveId);
	
	public Boolean deleteReserve(Integer reserveId);
	
	public List<ReserveDTO> findReservesByUser(Integer reserveId);

}
