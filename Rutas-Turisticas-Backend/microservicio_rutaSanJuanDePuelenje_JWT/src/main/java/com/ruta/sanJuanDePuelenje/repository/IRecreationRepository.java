package com.ruta.sanJuanDePuelenje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.Recreation;

public interface IRecreationRepository extends JpaRepository<Recreation, Integer> {
	
	// query para listar las actividades de recreacion por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM recreation WHERE state=?1", nativeQuery = true)
	Page<Recreation> LstRecreationByState(boolean state, Pageable pageable);
}
