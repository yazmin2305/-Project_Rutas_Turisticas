package com.ruta.sanJuanDePuelenje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.Workshop;

public interface IWorkshopRerpository extends JpaRepository<Workshop, Integer> {

	// query para listar los talleres por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM workshop WHERE state=?1", nativeQuery = true)
	Page<Workshop> LstWorkshopByState(boolean state, Pageable pageable);
}
