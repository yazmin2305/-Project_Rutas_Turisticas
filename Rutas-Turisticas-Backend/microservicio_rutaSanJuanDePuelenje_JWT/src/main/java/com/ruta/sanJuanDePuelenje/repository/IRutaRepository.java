package com.ruta.sanJuanDePuelenje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.Ruta;

public interface IRutaRepository extends JpaRepository<Ruta, Integer>{
	
	// Query para listar las charlas por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM ruta WHERE state=?1", nativeQuery = true)
	Page<Ruta> LstRutasByState(boolean state, Pageable pageable);
	
}
