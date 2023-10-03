package com.ruta.sanJuanDePuelenje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
	
	// query para listar los usuarios por su estado, ya sea activado o desactivado
	@Query(value = "SELECT * FROM user WHERE state=?1", nativeQuery = true)
	List<User> LstUserByState(boolean state);
	
	public User findByEmail(String email);
}
