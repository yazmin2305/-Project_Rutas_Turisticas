package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.UserDTO;
import com.ruta.sanJuanDePuelenje.auth.service.JWTService;
import com.ruta.sanJuanDePuelenje.models.Role;
import com.ruta.sanJuanDePuelenje.models.User;
import com.ruta.sanJuanDePuelenje.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository iUserRepository;
	@Autowired
	private ModelMapper modelMapper;
	// añado esta dependencia para que me encripte la contraseña al momento de
	// guardar en la BD
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private JWTService jwtService;

	@Override
	@Transactional(readOnly = true)
	public Response<List<UserDTO>> findAllUsers() {
		List<User> userEntity = iUserRepository.findAll();
		Response<List<UserDTO>> response = new Response<>();
		if (userEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Usuarios no encontrados");
			response.setMoreInfo("http://localhost:8080/user/ConsultAllUsers");
			response.setData(null);
		} else {
			List<UserDTO> userDTOs = userEntity.stream().map(user -> modelMapper.map(user, UserDTO.class))
					.collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Usuarios encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/user/ConsultAllUsers");
			response.setData(userDTOs);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<UserDTO> findByUserId(Integer userId) {
		User user = iUserRepository.findById(userId).orElse(null);
		Response<UserDTO> response = new Response<>();
		if (user == null) {
			response.setStatus(404);
			response.setUserMessage("Usuario no encontrado");
			response.setMoreInfo("http://localhost:8080/user/ConsultById{id}");
			response.setData(null);
		} else {
			UserDTO userDTO = modelMapper.map(user, UserDTO.class);
			response.setStatus(200);
			response.setUserMessage("Usuario encontrado con éxito");
			response.setMoreInfo("http://localhost:8080/user/ConsultById/{id}");
			response.setData(userDTO);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<UserDTO> saveUser(UserDTO user) {
		Response<UserDTO> response = new Response<>();
		if (user != null) {
			User userEntity = this.modelMapper.map(user, User.class);
			/*Crear un rol por defecto USER, de tal forma que solo se guarden usuarios con este rol.
			 * Por defecto el id para el rol USER va a ser = 2*/
			Role rol = new Role(2, "USER");
			//se realiza la codificación de la contraseña antes de guardar en BD
			userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
			userEntity.setState(true);
			userEntity.setRole(rol);
			User objUser = this.iUserRepository.save(userEntity);
			UserDTO userDTO = this.modelMapper.map(objUser, UserDTO.class);
			response.setStatus(200);
			response.setUserMessage("Usuario creado con éxito");
			response.setMoreInfo("http://localhost:8080/user/SaveUser");
			response.setData(userDTO);
		} else {
			response.setStatus(500);
			response.setUserMessage("Error al crear el usuario");
			response.setMoreInfo("http://localhost:8080/user/SaveUser");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<UserDTO> updateUser(Integer userId, UserDTO user) {
		User userEntity = this.modelMapper.map(user, User.class);
		Response<UserDTO> response = new Response<>();
		if (user != null && userId != null) {
			User userEntity1 = this.iUserRepository.findById(userId).get();
			//Si la contraseña ha cambiado entra al condicional para encriptar la nueva contraseña
			if(!passwordEncoder.matches(userEntity.getPassword(), userEntity1.getPassword())) {
				userEntity1.setPassword(passwordEncoder.encode(userEntity.getPassword()));
			}
			userEntity1.setIdentification(userEntity.getIdentification());
			userEntity1.setName(userEntity.getName());
			userEntity1.setLastName(userEntity.getLastName());
			userEntity1.setPhone(userEntity.getPhone());
			userEntity1.setEmail(userEntity.getEmail());
			//solo la persona que tiene el rol superusuario puede cambiar el rol de un usuario....falta???
			userEntity1.setRole(userEntity.getRole());
			userEntity1.setLstReserve(userEntity.getLstReserve());
			userEntity1.setState(userEntity.getState());
			this.iUserRepository.save(userEntity1);
			UserDTO userDTO = this.modelMapper.map(userEntity1, UserDTO.class);
			response.setStatus(200);
			response.setUserMessage("Usuario actualizado con éxito");
			response.setMoreInfo("http://localhost:8080/user/UpdateUser/{id}");
			response.setData(userDTO);
		} else {
			response.setStatus(500);
			response.setUserMessage("No se pudo actualizar el usuario");
			response.setMoreInfo("http://localhost:8080/user/UpdateUser/{id}");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<Boolean> disableUser(Integer userId) {
		User userEntity = this.iUserRepository.findById(userId).get();
		Response<Boolean> response = new Response<>();
		if (userEntity != null) {
			if (userEntity.getState() == true) {
				// el usuario aun no esta deshabilitado
				userEntity.setState(false);
				this.iUserRepository.save(userEntity);
				response.setStatus(200);
				response.setUserMessage("Usuario deshabilitado con éxito");
				response.setMoreInfo("http://localhost:8080/user/DisableUser/{id}");
				response.setData(true);
			} else {
				// el usuario ya esta deshabilitado
				response.setStatus(500);
				response.setUserMessage("El usuario ya esta deshabilitado");
				response.setMoreInfo("http://localhost:8080/user/DisableUser/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<UserDTO>> findAllUserBytState(boolean state) {
		List<User> userEntity = this.iUserRepository.LstUserByState(state);
		Response<List<UserDTO>> response = new Response<>();
		if (!userEntity.isEmpty()) {
			List<UserDTO> userDTO = userEntity.stream().map(user -> modelMapper.map(user, UserDTO.class))
					.collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Usuarios encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/user/ConsultAllUsersByState");
			response.setData(userDTO);
		} else {
			response.setStatus(404);
			response.setUserMessage("No se encuentran usuarios relacionados a este estado");
			response.setMoreInfo("http://localhost:8080/user/ConsultAllUsersByState");
			response.setData(null);
		}
		return response;
	}

}
