package com.ruta.sanJuanDePuelenje.services;

import org.springframework.data.domain.Pageable;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.RoleCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Command.UserCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.UserQueryDTO;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

public interface IUserService {
	
	public GenericPageableResponse findAllUsers(Pageable pageable);
	
	public Response<UserQueryDTO> findByUserId(Integer userId);
	
	public Response<UserCommandDTO> saveUser(UserCommandDTO user);
	
	public Response<UserQueryDTO> updateUser(Integer userId, UserCommandDTO user);
	
	public Response<Boolean> disableUser(Integer userId);
	
	public Response<Boolean> enableUser(Integer userId);
	
	public GenericPageableResponse findAllUserBytState(boolean state, Pageable pageable);
	
	public Response<Boolean> changeRolUser(Integer userId, RoleCommandDTO role);
}
