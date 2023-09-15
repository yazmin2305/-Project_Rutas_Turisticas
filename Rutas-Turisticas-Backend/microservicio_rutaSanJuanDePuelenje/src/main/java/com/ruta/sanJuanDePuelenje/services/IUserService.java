package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ruta.sanJuanDePuelenje.DTO.UserDTO;



public interface IUserService {
	
	public ResponseEntity<?> findAllUsers();
	
	public UserDTO findByUserId(Integer userId);
	
	public UserDTO saveUser(UserDTO user);
	
	public UserDTO updateUser(Integer userId, UserDTO user);
	
	public Boolean disableUser(Integer userId);
	
	public List<UserDTO> findAllUserBytState(boolean state);
}
