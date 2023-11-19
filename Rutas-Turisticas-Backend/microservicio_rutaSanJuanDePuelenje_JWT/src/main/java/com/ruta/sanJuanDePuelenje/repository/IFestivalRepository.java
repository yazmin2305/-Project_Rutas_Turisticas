package com.ruta.sanJuanDePuelenje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.Festival;

public interface IFestivalRepository extends JpaRepository<Festival, Integer> {

	// Query para listar los festivales por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM festival WHERE state=?1", nativeQuery = true)
	Page<Festival> LstFestivalByState(boolean state, Pageable pageable);

	// Query para listar los festivales por su estado y por la ruta a la cual se encuentran asociados
	@Query(value = "SELECT f FROM Festival f WHERE f.state = ?1 AND f.finca.ruta.id = ?2", nativeQuery = false)
	List<Festival> findFestivalByStateByRuta(boolean state, Integer idRuta);
	
	// Query para consultar todos los festivales a partir de la ruta asociada
	@Query(value = "SELECT f FROM Festival f WHERE f.finca.ruta.id = ?1", nativeQuery = false)
	List<Festival> findAllFestivalesByRuta(Integer idRuta);
}
