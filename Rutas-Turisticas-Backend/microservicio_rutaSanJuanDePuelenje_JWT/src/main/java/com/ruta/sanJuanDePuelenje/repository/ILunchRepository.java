package com.ruta.sanJuanDePuelenje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.Lunch;

public interface ILunchRepository extends JpaRepository<Lunch, Integer> {

	// query para listar los hospedajes por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM lunch WHERE state=?1", nativeQuery = true)
	Page<Lunch> LstLunchByState(boolean state, Pageable pageable);
}
