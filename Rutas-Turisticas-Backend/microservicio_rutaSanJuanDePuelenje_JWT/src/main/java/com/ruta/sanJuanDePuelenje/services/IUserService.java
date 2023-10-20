package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.RoleDTO;
import com.ruta.sanJuanDePuelenje.DTO.UserDTO;

public interface IUserService {
	
	public Response<List<UserDTO>> findAllUsers();
	
	public Response<UserDTO> findByUserId(Integer userId);
	
	public Response<UserDTO> saveUser(UserDTO user);
	
	public Response<UserDTO> updateUser(Integer userId, UserDTO user);
	
	public Response<Boolean> disableUser(Integer userId);
	
	public Response<List<UserDTO>> findAllUserBytState(boolean state);
	
	public Response<Boolean> changeRolUser(Integer userId, RoleDTO role);
}
