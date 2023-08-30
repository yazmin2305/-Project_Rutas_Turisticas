package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Reserve;


public interface IReserveService {
	
	public List<Reserve> findAllLodging();
	
	public Reserve findByReserveId(Integer reserveId);
	
	public Reserve saveReserve(Reserve reserve);
	
	public Reserve updateReserve(Integer reserveId, Reserve reserve);
	
	public Reserve disableReserve(Integer reserveId);
	//se deshabilita o elimina ?

}
