package com.ruta.sanJuanDePuelenje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.WorkshopType;

public interface IWorkshopTypeRepository extends JpaRepository<WorkshopType, Integer> {

	// query para listar los tipos de talleres por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM workshoptype WHERE state=?1", nativeQuery = true)
	Page<WorkshopType> LstWorkshopTypeByState(boolean state, Pageable pageable);
}
