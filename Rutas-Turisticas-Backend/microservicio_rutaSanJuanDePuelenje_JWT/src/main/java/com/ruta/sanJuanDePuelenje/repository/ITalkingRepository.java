package com.ruta.sanJuanDePuelenje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ruta.sanJuanDePuelenje.models.Talking;

public interface ITalkingRepository extends JpaRepository<Talking, Integer> {

	// Query para listar las charlas por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM talking WHERE state=?1", nativeQuery = true)
	Page<Talking> LstTalkingByState(boolean state, Pageable pageable);

	// Query para listar las charlas por su estado y por la ruta a la cual se encuentran asociadas
	@Query(value = "SELECT t FROM Talking t WHERE t.state = ?1 AND t.finca.ruta.id = ?2", nativeQuery = false)
	List<Talking> findTalkingByStateByRuta(boolean state, Integer idRuta);

	// Query para consultar todas las charlas a partir de la ruta asociada
	@Query(value = "SELECT t FROM Talking t WHERE t.finca.ruta.id = ?1", nativeQuery = false)
	List<Talking> findAllTalkingByRuta(Integer idRuta);

}
