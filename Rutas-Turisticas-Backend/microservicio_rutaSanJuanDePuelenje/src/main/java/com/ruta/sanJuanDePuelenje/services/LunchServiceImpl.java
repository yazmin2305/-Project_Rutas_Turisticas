package com.ruta.sanJuanDePuelenje.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruta.sanJuanDePuelenje.DTO.LunchDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.UserDTO;
import com.ruta.sanJuanDePuelenje.models.Lunch;
import com.ruta.sanJuanDePuelenje.repository.ILunchRepository;

@Service
public class LunchServiceImpl implements ILunchService{

	@Autowired
	private ILunchRepository iLunchRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Response<List<LunchDTO>> findAllLunch() {
		List<Lunch> lunchEntity = iLunchRepository.findAll();
		if(lunchEntity.isEmpty()) {
			
		}
		List<LunchDTO> lunchDTOs = lunchEntity.stream().map(lunch -> modelMapper.map(lunch, LunchDTO.class)).collect(Collectors.toList());
		Response<List<LunchDTO>> response = new Response<>();
		response.setStatus(200);
		response.setUserMessage("Menú encontrado con éxito");
		response.setMoreInfo("http://localhost:8080/lunch/ConsultAllLunch");
		response.setData(lunchDTOs);
		return response;
	}

	@Override
	public Response<LunchDTO> findByLunchId(Integer lunchId) {
		Lunch lunch = iLunchRepository.findById(lunchId).orElse(null);
		if(lunch == null) {
			
		}
		LunchDTO lunchDTO = modelMapper.map(lunch, LunchDTO.class);
		Response<LunchDTO> response = new Response<>();
		response.setStatus(200);
		response.setUserMessage("Menú encontrado con éxito");
		response.setMoreInfo("http://localhost:8080/lunch/ConsultById/{id}");
		response.setData(lunchDTO);
		return response;
	}

	@Override
	public Response<LunchDTO> saveLunch(LunchDTO lunch) {
		Lunch lunchEntity  = this.modelMapper.map(lunch, Lunch.class);
		Response<LunchDTO> response = new Response<>();
		if(lunch != null) {
			lunchEntity.setState(true);
			Lunch objLunch = this.iLunchRepository.save(lunchEntity);
			LunchDTO lunchDTO = this.modelMapper.map(objLunch, LunchDTO.class);
			response.setStatus(200);
			response.setUserMessage("Menú creado con éxito");
			response.setMoreInfo("http://localhost:8080/lunch/SaveLunch");
			response.setData(lunchDTO);
		}
		
		return response;
	}

	@Override
	public Response<LunchDTO> updateLunch(Integer lunchId, LunchDTO lunch) {
		Lunch lunchEntity = this.modelMapper.map(lunch, Lunch.class);
		Response<LunchDTO> response = new Response<>();
		if(lunch != null && lunchId != null) {
			Lunch lunchEntity1 = this.iLunchRepository.findById(lunchId).get();
			lunchEntity1.setMenu(lunchEntity.getMenu());
			lunchEntity1.setUnitPrice(lunchEntity.getUnitPrice());
			lunchEntity1.setTotalPrice(lunchEntity.getTotalPrice());
			lunchEntity1.setState(lunchEntity.getState());
			lunchEntity1.setLstReserve(lunchEntity.getLstReserve());
			this.iLunchRepository.save(lunchEntity1);
			LunchDTO lunchDTO = this.modelMapper.map(lunchEntity1, LunchDTO.class);
			response.setStatus(200);
			response.setUserMessage("Menú actualizado con éxito");
			response.setMoreInfo("http://localhost:8080/lunch/UpdateLunch/{id}");
			response.setData(lunchDTO);
		}
		
		return response;
	}

	@Override
	public Response<Boolean> disableLunch(Integer lunchId) {
		Lunch lunchEntity = this.iLunchRepository.findById(lunchId).get();
		Response<Boolean> response = new Response<>();
		if(lunchEntity != null) {
			if(lunchEntity.getState() == true){
				lunchEntity.setState(false);
				this.iLunchRepository.save(lunchEntity);
				response.setStatus(200);
				response.setUserMessage("Menú deshabilitado con éxito");
				response.setMoreInfo("http://localhost:8080/lunch/DisableLunch/{id}");
				response.setData(true);
			}else {
				response.setStatus(500);
				response.setUserMessage("El menú ya esta deshabilitado");
				response.setMoreInfo("http://localhost:8080/lunch/DisableLunch/{id}");
				response.setData(false);
			}
			
		}
		return response;
	}

	@Override
	public Response<List<LunchDTO>> findAllLunchBytState(boolean state) {
		List<Lunch> lunchEntity = this.iLunchRepository.LstLunchByState(state);
		List<LunchDTO> lunchDTO = lunchEntity.stream().map(lunch -> modelMapper.map(lunch, LunchDTO.class)).collect(Collectors.toList());
		return lunchDTO;
	}

}
