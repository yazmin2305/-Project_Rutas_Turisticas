package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ruta.sanJuanDePuelenje.models.User;
import com.ruta.sanJuanDePuelenje.repository.IUserRepository;

public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserRepository iUserRepository;
	
	@Override
	public List<User> findAllTalking() {
		return (List<User>) iUserRepository.findAll();
	}

	@Override
	public User findByUserId(Integer userId) {
		User user = iUserRepository.findById(userId).orElse(null);
		return user;
	}

	@Override
	public User saveUser(User user) {
		return iUserRepository.save(user);
	}

	@Override
	public User updateUser(Integer userId, User user) {
		User user1 = this.findByUserId(userId);
		user1.setIdentification(user.getIdentification());
		user1.setName(user.getName());
		user1.setLastName(user.getLastName());
		user1.setPhone(user.getPhone());
		user1.setEmail(user.getEmail());
		user1.setPasssword(user.getPasssword());
		user1.setRole(user.getRole());
		user1.setLstReserve(user.getLstReserve());
		user1.setState(user.getState());
		return user1;
	}

	@Override
	public void disableUser(Integer userId) {
		User user = this.findByUserId(userId);
		user.setState(false);
	}

}
