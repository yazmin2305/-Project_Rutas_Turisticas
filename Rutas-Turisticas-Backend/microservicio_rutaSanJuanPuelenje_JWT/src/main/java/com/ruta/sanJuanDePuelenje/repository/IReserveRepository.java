package com.ruta.sanJuanDePuelenje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.Reserve;

public interface IReserveRepository extends JpaRepository<Reserve, Integer>{
	
	@Query(value = "SELECT * FROM reserve WHERE user_id =?1", nativeQuery = true )
	List<Reserve> reservasUsuario(Integer id);
}
