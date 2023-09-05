package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.UserDTO;



public interface IUserService {
	
	public List<UserDTO> findAllTalking();
	
	public UserDTO findByUserId(Integer userId);
	
	public UserDTO saveUser(UserDTO user);
	
	public UserDTO updateUser(Integer userId, UserDTO user);
	
	public Boolean disableUser(Integer userId);
}
