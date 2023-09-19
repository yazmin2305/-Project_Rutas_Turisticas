package com.ruta.sanJuanDePuelenje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.Lunch;

public interface ILunchRepository extends JpaRepository<Lunch, Integer> {

	// query para listar los hospedajes por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM lunch WHERE state=?1", nativeQuery = true)
	List<Lunch> LstLunchByState(boolean state);
}
