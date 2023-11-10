package com.ruta.sanJuanDePuelenje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.Festival;

public interface IFestivalRepository extends JpaRepository<Festival, Integer> {

	// query para listar los festivales por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM festival WHERE state=?1", nativeQuery = true)
	Page<Festival> LstFestivalByState(boolean state, Pageable pageable);

	// query para listar los festivales por su estado y por la ruta a la cual se encuentran asociados
	@Query(value = "SELECT * FROM festival f WHERE f.state=?1 AND f.ruta.ruta_id?2", nativeQuery = true)
	List<Festival> findFestivalByStateByRuta(boolean state, Integer idRuta);
	
	// query para consultar todos los festivales a partir de la ruta asociada
	@Query(value = "SELECT * FROM festival f WHERE f.ruta.ruta_id?1", nativeQuery = true)
	List<Festival> findAllFestivalesByRuta(Integer idRuta);
}
