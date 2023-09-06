package com.ruta.sanJuanDePuelenje.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruta.sanJuanDePuelenje.DTO.UserDTO;
import com.ruta.sanJuanDePuelenje.models.User;
import com.ruta.sanJuanDePuelenje.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserRepository iUserRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<UserDTO> findAllUsers() {
		List<User> userEntity = iUserRepository.findAll();
		List<UserDTO> userDTOs = new ArrayList<>();
		userDTOs = userEntity.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
		return userDTOs;
	}

	@Override
	public UserDTO findByUserId(Integer userId) {
		User user = iUserRepository.findById(userId).orElse(null);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public UserDTO saveUser(UserDTO user) {
		User userEntity  = this.modelMapper.map(user, User.class);
		userEntity.setState(true);
		User objUser = this.iUserRepository.save(userEntity);
		UserDTO userDTO = this.modelMapper.map(objUser, UserDTO.class);
		return userDTO;
	}

	@Override
	public UserDTO updateUser(Integer userId, UserDTO user) {
		User userEntity = this.modelMapper.map(user, User.class);
		UserDTO userDTO = this.findByUserId(userId);
		User userEntity1 = this.modelMapper.map(userDTO, User.class);
		System.out.println("nombre modificado: "+userEntity.getName());
		userEntity1.setIdentification(userEntity.getIdentification());
		userEntity1.setName(userEntity.getName());
		userEntity1.setLastName(userEntity.getLastName());
		userEntity1.setPhone(userEntity.getPhone());
		userEntity1.setEmail(userEntity.getEmail());
		userEntity1.setPassword(userEntity.getPassword());
		userEntity1.setRole(userEntity.getRole());
		userEntity1.setLstReserve(userEntity.getLstReserve());
		userEntity1.setState(userEntity.getState());
		this.iUserRepository.save(userEntity1);
		userDTO = this.modelMapper.map(userEntity1, UserDTO.class);
		return userDTO;
	}

	@Override
	public Boolean disableUser(Integer userId) {
		UserDTO userDTO = this.findByUserId(userId);
		User userEntity = this.modelMapper.map(userDTO, User.class);
		if (userEntity != null) {
			userEntity.setState(false);
			this.iUserRepository.save(userEntity);
			//validar que si ya esta en falso se envie un mensaje que ya esta deshabilitado?
			return true;
		}
		return false;
	}

}
