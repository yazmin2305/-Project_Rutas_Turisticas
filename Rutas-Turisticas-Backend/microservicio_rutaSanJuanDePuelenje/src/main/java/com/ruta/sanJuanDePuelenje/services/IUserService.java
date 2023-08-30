package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.User;


public interface IUserService {
	
	public List<User> findAllTalking();
	
	public User findByUserId(Integer userId);
	
	public User saveUser(User user);
	
	public User updateUser(Integer userId, User user);
	
	public User disableUser(Integer userId);
}
