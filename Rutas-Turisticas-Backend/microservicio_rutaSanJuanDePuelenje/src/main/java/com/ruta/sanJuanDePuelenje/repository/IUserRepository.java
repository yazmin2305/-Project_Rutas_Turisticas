package com.ruta.sanJuanDePuelenje.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruta.sanJuanDePuelenje.models.User;

public interface IUserRepository extends JpaRepository<User, Integer>{

}
