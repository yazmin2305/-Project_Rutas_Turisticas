package com.ruta.sanJuanDePuelenje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.Talking;

public interface ITalkingRepository extends JpaRepository<Talking, Integer>{
	
	//query para listar las charlas desactivadas
	@Query(value = "SELECT * FROM talking WHERE state=false", nativeQuery = true)
	Page<Talking> LstTalkingDisabled(Pageable pageable);
	
	//query para listar las charlas por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM talking WHERE state=?1", nativeQuery = true)
	Page<Talking> LstTalkingByState(boolean state, Pageable pageable);
	
}
