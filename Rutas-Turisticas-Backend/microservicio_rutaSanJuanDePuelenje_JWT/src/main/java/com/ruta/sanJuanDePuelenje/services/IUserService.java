package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.RoleDTO;
import com.ruta.sanJuanDePuelenje.DTO.Command.UserCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.UserQueryDTO;

public interface IUserService {
	
	public Response<List<UserQueryDTO>> findAllUsers();
	
	public Response<UserQueryDTO> findByUserId(Integer userId);
	
	public Response<UserCommandDTO> saveUser(UserCommandDTO user);
	
	public Response<UserQueryDTO> updateUser(Integer userId, UserCommandDTO user);
	
	public Response<Boolean> disableUser(Integer userId);
	
	public Response<List<UserQueryDTO>> findAllUserBytState(boolean state);
	
	public Response<Boolean> changeRolUser(Integer userId, RoleDTO role);
}
