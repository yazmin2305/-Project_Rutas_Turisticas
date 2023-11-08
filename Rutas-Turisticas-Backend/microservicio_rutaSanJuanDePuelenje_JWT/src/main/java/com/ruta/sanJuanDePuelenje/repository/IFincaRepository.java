package com.ruta.sanJuanDePuelenje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.Finca;

public interface IFincaRepository extends JpaRepository<Finca, Integer> {

	// query para listar las fincas por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM finca WHERE state=?1", nativeQuery = true)
	Page<Finca> LstFincaByState(boolean state, Pageable pageable);
}
