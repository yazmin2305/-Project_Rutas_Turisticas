package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Reserve;


public interface IReserveService {
	
	public List<Reserve> findAll();
	
	public Reserve findByReserveId(Integer idReserve);
	
	public Reserve saveReserve(Reserve idReserve);
	
	public Reserve updateReserve(Integer idReserve, Reserve reserve);
	
	public Boolean deleteReserve(Integer idReserve);

}
