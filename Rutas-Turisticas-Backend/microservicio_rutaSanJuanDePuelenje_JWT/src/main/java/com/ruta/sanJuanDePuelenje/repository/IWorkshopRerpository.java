package com.ruta.sanJuanDePuelenje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ruta.sanJuanDePuelenje.models.Workshop;

public interface IWorkshopRerpository extends JpaRepository<Workshop, Integer> {

	// Query para listar los talleres por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM workshop WHERE state=?1", nativeQuery = true)
	Page<Workshop> LstWorkshopByState(boolean state, Pageable pageable);

	// Query para listar los talleres por su estado y por la ruta a la cual se encuentran asociados
	@Query(value = "SELECT w FROM Workshop w WHERE w.state = ?1 AND w.finca.ruta.id = ?2", nativeQuery = false)
	List<Workshop> findWorkshopByStateByRuta(boolean state, Integer idRuta);

	// Query para consultar todos los talleres a partir de la ruta asociada
	@Query(value = "SELECT w FROM Workshop w WHERE w.finca.ruta.id = ?1", nativeQuery = false)
	List<Workshop> findAllWorkshopByRuta(Integer idRuta);
}
