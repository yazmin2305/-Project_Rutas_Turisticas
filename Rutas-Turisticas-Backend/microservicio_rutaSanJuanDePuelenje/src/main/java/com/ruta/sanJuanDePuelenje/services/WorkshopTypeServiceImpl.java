package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.WorkshopTypeDTO;
import com.ruta.sanJuanDePuelenje.models.WorkshopType;
import com.ruta.sanJuanDePuelenje.repository.IWorkshopTypeRepository;

@Service
public class WorkshopTypeServiceImpl implements IWorkshopTypeService{

	@Autowired
	private IWorkshopTypeRepository iWorkshopTypeRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Response<List<WorkshopTypeDTO>> findAllWorkshopTypes() {
		List<WorkshopType> workshopTypeEntity = iWorkshopTypeRepository.findAll();
		Response<List<WorkshopTypeDTO>> response = new Response<>();
		if(workshopTypeEntity.isEmpty()) {
			response.setStatus(200);
			response.setUserMessage("Tipos de taller no encontrados");
			response.setMoreInfo("http://localhost:8080/workshopType/ConsultAllWorkshopType");
			response.setData(null);
		}else {
			List<WorkshopTypeDTO> workshopTypeDTO = workshopTypeEntity.stream().map(workshopType -> modelMapper.map(workshopType, WorkshopTypeDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Tipos de taller encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/workshopType/ConsultAllWorkshopType");
			response.setData(workshopTypeDTO);
		}
		return response;
	}
	
	@Override
	public Response<WorkshopTypeDTO> findByWorkshopTypeId(Integer WorkshopTypeId) {
		WorkshopType workshopType = iWorkshopTypeRepository.findById(WorkshopTypeId).orElse(null);
		Response<WorkshopTypeDTO> response = new Response<>();
		if(workshopType == null) {
			response.setStatus(404);
			response.setUserMessage("Tipo taller no encontrado");
			response.setMoreInfo("http://localhost:8080/workshopType/ConsultById/{id}");
			response.setData(null);
		}else {
			WorkshopTypeDTO workshopTypeDTO = modelMapper.map(workshopType, WorkshopTypeDTO.class);
			response.setStatus(200);
			response.setUserMessage("Tipo taller encontrado con éxito");
			response.setMoreInfo("http://localhost:8080/workshopType/ConsultById/{id}");
			response.setData(workshopTypeDTO);
		}
		return response;
	}

	@Override
	public Response<WorkshopTypeDTO> saveWorkshopType(WorkshopTypeDTO workshopType) {
		Response<WorkshopTypeDTO> response = new Response<>();
		if(workshopType != null) {
			WorkshopType WorkshopTypeEntity  = this.modelMapper.map(workshopType, WorkshopType.class);
			WorkshopTypeEntity.setState(true);
			WorkshopType objWorkshopType = this.iWorkshopTypeRepository.save(WorkshopTypeEntity);
			WorkshopTypeDTO workshopTypeDTO = this.modelMapper.map(objWorkshopType, WorkshopTypeDTO.class);
			response.setStatus(200);
			response.setUserMessage("Tipo taller creado con éxito");
			response.setMoreInfo("http://localhost:8080/workshopType/SaveWorkshopType");
			response.setData(workshopTypeDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("Tipo taller no ha sido creado");
			response.setMoreInfo("http://localhost:8080/workshopType/SaveWorkshopType");
			response.setData(null);
		}
		return response;
	}

	@Override
	public Response<WorkshopTypeDTO> updateWorkshopType(Integer workshopTypeId, WorkshopTypeDTO workshopType) {
		Response<WorkshopTypeDTO> response = new Response<>();
		if(workshopType != null && workshopTypeId != null) {
			WorkshopType WorkshopTypeEntity = this.modelMapper.map(workshopType, WorkshopType.class);
			WorkshopType workshopTypeEntity1 = this.iWorkshopTypeRepository.findById(workshopTypeId).get();
			workshopTypeEntity1.setName(WorkshopTypeEntity.getName());
			workshopTypeEntity1.setState(WorkshopTypeEntity.getState());
			workshopTypeEntity1.setLstWorkshops(WorkshopTypeEntity.getLstWorkshops());
			this.iWorkshopTypeRepository.save(workshopTypeEntity1);
			WorkshopTypeDTO workshopTypeDTO = this.modelMapper.map(workshopTypeEntity1, WorkshopTypeDTO.class);
			response.setStatus(200);
			response.setUserMessage("Tipo taller actualizado con éxito");
			response.setMoreInfo("http://localhost:8080/workshopType/UpdateWorkshopType/{id}");
			response.setData(workshopTypeDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("Tipo taller no ha sido actualizado");
			response.setMoreInfo("http://localhost:8080/workshopType/UpdateWorkshopType/{id}");
			response.setData(null);
		}
		return response;
	}

	@Override
	public Response<Boolean> disableWorkshopType(Integer workshopTypeId) {
		WorkshopType WorkshopTypeEntity = this.iWorkshopTypeRepository.findById(workshopTypeId).get();
		Response<Boolean> response = new Response<>();
		if (WorkshopTypeEntity != null) {
			if(WorkshopTypeEntity.getState() == true){
				WorkshopTypeEntity.setState(false);
				this.iWorkshopTypeRepository.save(WorkshopTypeEntity);
				response.setStatus(200);
				response.setUserMessage("Tipo Taller deshabilitado con éxito");
				response.setMoreInfo("http://localhost:8080/workshopType/DisableWorkshopType/{id}");
				response.setData(true);
			}else {
				response.setStatus(500);
				response.setUserMessage("Tipo taller ya esta deshabilitado");
				response.setMoreInfo("http://localhost:8080/workshopType/DisableWorkshopType/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	public Response<List<WorkshopTypeDTO>> findAllWorkshopTypeBytState(boolean state) {
		List<WorkshopType> workshopTypeEntity = this.iWorkshopTypeRepository.LstWorkshopTypeByState(state);
		Response<List<WorkshopTypeDTO>> response = new Response<>();
		if(!workshopTypeEntity.isEmpty()) {
			List<WorkshopTypeDTO> workshoTypepDTO = workshopTypeEntity.stream().map(workshopType -> modelMapper.map(workshopType, WorkshopTypeDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Tipos taller encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/workshopType/ConsultAllWorkshopTypeByState");
			response.setData(workshoTypepDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("No se encuentran tipos de taller relacionados a este estado");
			response.setMoreInfo("http://localhost:8080/workshopType/ConsultAllWorkshopTypeByState");
			response.setData(null);
		}
		return response;
	}

	

}
