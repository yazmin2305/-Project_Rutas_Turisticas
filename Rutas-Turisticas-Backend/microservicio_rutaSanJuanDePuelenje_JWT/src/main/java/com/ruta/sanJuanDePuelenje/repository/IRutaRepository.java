package com.ruta.sanJuanDePuelenje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.Ruta;

public interface IRutaRepository extends JpaRepository<Ruta, Integer>{
	//query para listar las charlas por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM ruta WHERE state=?1", nativeQuery = true)
	List<Ruta> LstRutasByState(boolean state);
	
}
