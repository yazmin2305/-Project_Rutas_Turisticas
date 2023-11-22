package com.ruta.sanJuanDePuelenje.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruta.sanJuanDePuelenje.models.Reserve;
import com.ruta.sanJuanDePuelenje.models.ReserveLodging;

public interface IReserveLodgingRepository extends JpaRepository<ReserveLodging, Integer>{

	void deleteByReserve(Reserve reserve);
	
}
