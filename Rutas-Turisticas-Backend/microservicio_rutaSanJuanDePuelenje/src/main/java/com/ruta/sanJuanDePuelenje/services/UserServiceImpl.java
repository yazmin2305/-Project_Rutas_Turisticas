package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruta.sanJuanDePuelenje.DTO.Response;
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
	public Response<List<UserDTO>> findAllUsers() {
		List<User> userEntity = iUserRepository.findAll();
		if(userEntity.isEmpty()) {
			System.out.println("falta esta parte");
		}
		List<UserDTO> userDTOs = userEntity.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
		Response<List<UserDTO>> response = new Response<>();
		response.setStatus(200);
		response.setUserMessage("Usuarios encontrados con éxito");
		response.setMoreInfo("http://localhost:8080/user/ConsultAllUsers");
		response.setData(userDTOs);
		return response;
	}

	@Override
	public Response<UserDTO> findByUserId(Integer userId) {
		User user = iUserRepository.findById(userId).orElse(null);
		if(user == null) {
			System.out.println("falta esta parte");
		}
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		Response<UserDTO> response = new Response<>();
		response.setStatus(200);
		response.setUserMessage("Usuario encontrado con éxito");
		response.setMoreInfo("http://localhost:8080/user/ConsultById/{id}");
		response.setData(userDTO);
		return response;
	}

	@Override
	public Response<UserDTO> saveUser(UserDTO user) {
		User userEntity  = this.modelMapper.map(user, User.class);
		Response<UserDTO> response = new Response<>();
		if(user != null) {
			userEntity.setState(true);
			User objUser = this.iUserRepository.save(userEntity);
			UserDTO userDTO = this.modelMapper.map(objUser, UserDTO.class);
			response.setStatus(200);
			response.setUserMessage("Usuario creado con éxito");
			response.setMoreInfo("http://localhost:8080/user/SaveUser");
			response.setData(userDTO);
		}
		return response;
	}

	@Override
	public Response<UserDTO> updateUser(Integer userId, UserDTO user) {
		User userEntity = this.modelMapper.map(user, User.class);
		Response<UserDTO> response = new Response<>();
		if(user != null && userId != null) {
			User userEntity1 = this.iUserRepository.findById(userId).get();
			//User userEntity1 = this.modelMapper.map(userDTO, User.class);
			//System.out.println("nombre modificado: "+userEntity.getName());
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
			UserDTO userDTO = this.modelMapper.map(userEntity1, UserDTO.class);
			response.setStatus(200);
			response.setUserMessage("Usuario actualizado con éxito");
			response.setMoreInfo("http://localhost:8080/user/UpdateUser/{id}");
			response.setData(userDTO);
		}

		return response;
	}

	@Override
	public Response<Boolean> disableUser(Integer userId) {
		User userEntity = this.iUserRepository.findById(userId).get();
		Response<Boolean> response = new Response<>();
		if (userEntity != null) {
			if(userEntity.getState() == true){
				//el usuario aun no esta deshabilitado
				userEntity.setState(false);
				this.iUserRepository.save(userEntity);
				response.setStatus(200);
				response.setUserMessage("Usuario deshabilitado con éxito");
				response.setMoreInfo("http://localhost:8080/user/DisableUser/{id}");
				response.setData(true);
			}else {
				//el usuario ya esta deshabilitado
				response.setStatus(500);
				response.setUserMessage("El usuario ya esta deshabilitado");
				response.setMoreInfo("http://localhost:8080/user/DisableUser/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	public Response<List<UserDTO>> findAllUserBytState(boolean state) {
		List<User> userEntity = this.iUserRepository.LstUserByState(state);
		Response<List<UserDTO>> response = new Response<>();
		if(!userEntity.isEmpty()) {
			List<UserDTO> userDTO = userEntity.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Usuarios encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/user/ConsultAllUsersByState");
			response.setData(userDTO);
		} 
		return response;
	}

}
