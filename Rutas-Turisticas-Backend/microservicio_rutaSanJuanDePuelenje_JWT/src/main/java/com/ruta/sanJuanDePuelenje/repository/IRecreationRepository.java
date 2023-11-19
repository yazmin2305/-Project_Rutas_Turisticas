package com.ruta.sanJuanDePuelenje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ruta.sanJuanDePuelenje.models.Recreation;

public interface IRecreationRepository extends JpaRepository<Recreation, Integer> {

	// Query para listar las actividades de recreacion por su estado, ya sea
	// activado o desactivado
	@Query(value = "SELECT * FROM recreation WHERE state=?1", nativeQuery = true)
	Page<Recreation> LstRecreationByState(boolean state, Pageable pageable);

	// Query para listar las actividades de recreacion por su estado y por la ruta a la cual se encuentran asociados
	@Query(value = "SELECT r FROM Recreation r WHERE r.state = ?1 AND r.finca.ruta.id = ?2", nativeQuery = false)
	List<Recreation> findRecreationByStateByRuta(boolean state, Integer idRuta);

	// Query para consultar todas las actividades de recreacion a partir de la ruta asociada
	@Query(value = "SELECT r FROM Recreation r WHERE r.finca.ruta.id = ?1", nativeQuery = false)
	List<Recreation> findAllRecreationByRuta(Integer idRuta);
}
