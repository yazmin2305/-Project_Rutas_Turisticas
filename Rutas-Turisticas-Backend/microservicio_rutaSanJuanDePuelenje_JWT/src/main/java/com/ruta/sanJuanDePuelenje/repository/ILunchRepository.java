package com.ruta.sanJuanDePuelenje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ruta.sanJuanDePuelenje.models.Lunch;

public interface ILunchRepository extends JpaRepository<Lunch, Integer> {

	// Query para listar los hospedajes por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM lunch WHERE state=?1", nativeQuery = true)
	Page<Lunch> LstLunchByState(boolean state, Pageable pageable);

	// Query para listar los hospedajes por su estado, ya sea activado o desactivado y por la ruta asociada
	@Query(value = "SELECT l FROM Lunch l WHERE l.state = ?1 AND l.ruta.id = ?2", nativeQuery = false)
	List<Lunch> findLunchByStateByRuta(boolean state, Integer idRuta);

	// Query para consultar todos los almuerzos a partir de la ruta asociada
	@Query(value = "SELECT f FROM Lunch f WHERE f.ruta.id = ?1", nativeQuery = false)
	List<Lunch> findAllLunchByRuta(Integer idRuta);
}
