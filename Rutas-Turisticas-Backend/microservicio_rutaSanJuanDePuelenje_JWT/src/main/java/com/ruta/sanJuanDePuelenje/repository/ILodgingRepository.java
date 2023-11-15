package com.ruta.sanJuanDePuelenje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ruta.sanJuanDePuelenje.models.Lodging;

public interface ILodgingRepository extends JpaRepository<Lodging, Integer> {

	// query para listar los hospedajes por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM lodging WHERE state=?1", nativeQuery = true)
	Page<Lodging> LstLodgingByState(boolean state, Pageable pageable);

	// query para listar los festivales por su estado y por la ruta a la cual se
	// encuentran asociados
	@Query(value = "SELECT l FROM Lodging l WHERE l.state = ?1 AND l.finca.ruta.id = ?2", nativeQuery = false)
	List<Lodging> findLodgingByStateByRuta(boolean state, Integer idRuta);

	// query para consultar todos los festivales a partir de la ruta asociada
	@Query(value = "SELECT l FROM Lodging l WHERE l.finca.ruta.id = ?1", nativeQuery = false)
	List<Lodging> findAllLodgingByRuta(Integer idRuta);
}
