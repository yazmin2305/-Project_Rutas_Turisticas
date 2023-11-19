package com.ruta.sanJuanDePuelenje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ruta.sanJuanDePuelenje.models.Finca;

public interface IFincaRepository extends JpaRepository<Finca, Integer> {

	// Query para listar las fincas por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM finca WHERE state=?1", nativeQuery = true)
	Page<Finca> LstFincaByState(boolean state, Pageable pageable);

	// Query para listar las fincas por su estado y por la ruta a la cual se encuentran asociadas
	@Query(value = "SELECT f FROM Finca f WHERE f.state = ?1 AND f.ruta.id=?2", nativeQuery = false)
	List<Finca> LstFincaByStateByRuta(boolean state, Integer idRuta);

	// Query para consultar todos los festivales a partir de la ruta asociada
	@Query(value = "SELECT f FROM Finca f WHERE f.ruta.id = ?1", nativeQuery = false)
	List<Finca> findAllFincasByRuta(Integer idRuta);

}
