package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.UserCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Command.UserPermissionsDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.UserQueryDTO;
import com.ruta.sanJuanDePuelenje.models.Role;
import com.ruta.sanJuanDePuelenje.models.Ruta;
import com.ruta.sanJuanDePuelenje.models.User;
import com.ruta.sanJuanDePuelenje.repository.IUserRepository;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;
import com.ruta.sanJuanDePuelenje.util.PageableUtils;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository iUserRepository;

	@Autowired
	private ModelMapper modelMapper;

	// añado esta dependencia para que me encripte la contraseña al momento de guardar en la BD
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public GenericPageableResponse findAllUsers(Pageable pageable) {
		Page<User> usersPage = this.iUserRepository.findAll(pageable);
		if (usersPage.isEmpty())
			return GenericPageableResponse.emptyResponse("Usuarios no encontrados");
		return this.validatePageList(usersPage);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<UserQueryDTO> findByUserId(Integer userId) {
		User user = iUserRepository.findById(userId).orElse(null);
		Response<UserQueryDTO> response = new Response<>();
		if (user == null) {
			response.setStatus(404);
			response.setUserMessage("Usuario no encontrado");
			response.setMoreInfo("http://localhost:8080/user/ConsultById{id}");
			response.setData(null);
		} else {
			UserQueryDTO userDTO = modelMapper.map(user, UserQueryDTO.class);
			response.setStatus(200);
			response.setUserMessage("Usuario encontrado con éxito");
			response.setMoreInfo("http://localhost:8080/user/ConsultById/{id}");
			response.setData(userDTO);
		}
		return response;
	}
	
	@Override
	public Response<UserCommandDTO> findUserByEmail(String email) {
		User user = iUserRepository.findByEmail(email);
		Response<UserCommandDTO> response = new Response<>();
		if (user == null) {
			response.setStatus(404);
			response.setUserMessage("Usuario no encontrado");
			response.setMoreInfo("http://localhost:8080/user/ConsultByEmail{id}");
			response.setData(null);
		} else {
			UserCommandDTO userDTO = modelMapper.map(user, UserCommandDTO.class);
			response.setStatus(200);
			response.setUserMessage("Usuario encontrado con éxito");
			response.setMoreInfo("http://localhost:8080/user/ConsultByEmail/{id}");
			response.setData(userDTO);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<UserCommandDTO> saveUser(UserCommandDTO user) {
		Response<UserCommandDTO> response = new Response<>();
		if (user != null) {
			User userEntity = this.modelMapper.map(user, User.class);
			/*
			 * Crear un rol por defecto USER, de tal forma que solo se guarden usuarios con
			 * este rol. Por defecto el id para el rol USER va a ser = 2
			 */
			Role rol = new Role(2, "USER");
			// se realiza la codificación de la contraseña antes de guardar en BD
			userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
			userEntity.setState(true);
			userEntity.setRole(rol);
			User objUser = this.iUserRepository.save(userEntity);
			UserCommandDTO userDTO = this.modelMapper.map(objUser, UserCommandDTO.class);
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
	public Response<UserQueryDTO> updateUser(Integer userId, UserCommandDTO user) {
		Response<UserQueryDTO> response = new Response<>();
		Optional<User> optionalUser = this.iUserRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User userEntity1 = optionalUser.get();
			User userEntity = this.modelMapper.map(user, User.class);
			// Si la contraseña ha cambiado entra al condicional para encriptar la nueva contraseña
			if (!passwordEncoder.matches(userEntity.getPassword(), userEntity1.getPassword())) {
				userEntity1.setPassword(passwordEncoder.encode(userEntity.getPassword()));
			}
			userEntity1.setIdentification(userEntity.getIdentification());
			userEntity1.setName(userEntity.getName());
			userEntity1.setLastName(userEntity.getLastName());
			userEntity1.setPhone(userEntity.getPhone());
			userEntity1.setEmail(userEntity.getEmail());
			this.iUserRepository.save(userEntity1);
			UserQueryDTO userDTO = this.modelMapper.map(userEntity1, UserQueryDTO.class);
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
				userEntity.setState(false);
				this.iUserRepository.save(userEntity);
				response.setStatus(200);
				response.setUserMessage("Usuario deshabilitado con éxito");
				response.setMoreInfo("http://localhost:8080/user/DisableUser/{id}");
				response.setData(true);
			} else {
				response.setStatus(500);
				response.setUserMessage("El usuario ya esta deshabilitado");
				response.setMoreInfo("http://localhost:8080/user/DisableUser/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	@Transactional
	public Response<Boolean> enableUser(Integer userId) {
		User userEntity = this.iUserRepository.findById(userId).get();
		Response<Boolean> response = new Response<>();
		if (userEntity != null) {
			if (userEntity.getState() == false) {
				userEntity.setState(true);
				this.iUserRepository.save(userEntity);
				response.setStatus(200);
				response.setUserMessage("Usuario habilitado con éxito");
				response.setMoreInfo("http://localhost:8080/user/EnableUser/{id}");
				response.setData(true);
			} else {
				response.setStatus(500);
				response.setUserMessage("El usuario ya esta deshabilitado");
				response.setMoreInfo("http://localhost:8080/user/EnableUser/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public GenericPageableResponse findAllUserBytState(boolean state, Pageable pageable) {
		Page<User> usersPage = this.iUserRepository.LstUserByState(state, pageable);
		if (usersPage.isEmpty())
			return GenericPageableResponse.emptyResponse("No se encuentran usuarios relacionados a este estado");
		return this.validatePageList(usersPage);
	}

	@Override
	@Transactional
	public Response<Boolean> changePermissions(UserPermissionsDTO userPermissions) {
		User userEntity = this.iUserRepository.findById(userPermissions.getIdUser()).get();
		Response<Boolean> response = new Response<>();
		if (userEntity != null) {
			Role role = this.modelMapper.map(userPermissions.getRole(), Role.class);
			if (userEntity.getRole().getName().equals("USER")) {
				userEntity.setRole(role);
				userEntity.setRuta(this.modelMapper.map(userPermissions.getRuta(), Ruta.class));
				this.iUserRepository.save(userEntity);
				response.setStatus(200);
				response.setUserMessage("Cambio de permisos exitoso");
				response.setMoreInfo("http://localhost:8080/user/changePermissions/{id}");
				response.setData(true);
			} else if (userEntity.getRole().getName().equals("ADMIN")) {
				response.setStatus(404);
				response.setUserMessage("El usuario ya tiene permisos asignados");
				response.setMoreInfo("http://localhost:8080/user/changePermissions/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	// Servicio que permite consultar todos los usuarios que han realizado reserva en una ruta
	@Transactional(readOnly = true)
	@Override
	public GenericPageableResponse findAllUsersByRuta(Integer rutaId, Pageable pageable) {
		Page<User> userPage = this.iUserRepository.LstUserByRuta(rutaId, pageable);
		if (userPage.isEmpty())
			return GenericPageableResponse.emptyResponse("No se han identificado usuarios que hayan realizado reservas en esta ruta ");
		return this.validatePageList(userPage);
	}

	private GenericPageableResponse validatePageList(Page<User> userPage) {
		List<UserQueryDTO> userDTOS = userPage.stream().map(x -> modelMapper.map(x, UserQueryDTO.class))
				.collect(Collectors.toList());
		return PageableUtils.createPageableResponse(userPage, userDTOS);
	}

}
