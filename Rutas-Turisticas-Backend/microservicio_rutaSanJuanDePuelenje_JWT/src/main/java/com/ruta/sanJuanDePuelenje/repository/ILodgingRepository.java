package com.ruta.sanJuanDePuelenje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.Lodging;

public interface ILodgingRepository extends JpaRepository<Lodging, Integer>{
	
	//query para listar los hospedajes desactivados
	@Query(value = "SELECT * FROM lodging WHERE state=false", nativeQuery = true)
	List<Lodging> LstLodgingDisabled();
	
	//query para listar los hospedajes por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM lodging WHERE state=?1", nativeQuery = true)
	Page<Lodging> LstLodgingByState(boolean state, Pageable pageable);
}
