package com.ruta.sanJuanDePuelenje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.Festival;

public interface IFestivalRepository extends JpaRepository<Festival, Integer> {
	
	// query para listar los festivales por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM festival WHERE state=?1", nativeQuery = true)
	Page<Festival> LstFestivalByState(boolean state, Pageable pageable);
}
