package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.WorkshopTypeCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.WorkshopTypeQueryDTO;
import com.ruta.sanJuanDePuelenje.models.WorkshopType;
import com.ruta.sanJuanDePuelenje.repository.IWorkshopTypeRepository;

@Service
public class WorkshopTypeServiceImpl implements IWorkshopTypeService {

	@Autowired
	private IWorkshopTypeRepository iWorkshopTypeRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional(readOnly = true)
	public Response<List<WorkshopTypeQueryDTO>> findAllWorkshopTypes() {
		List<WorkshopType> workshopTypeEntity = iWorkshopTypeRepository.findAll();
		Response<List<WorkshopTypeQueryDTO>> response = new Response<>();
		if(workshopTypeEntity.isEmpty()) {
			response.setStatus(200);
			response.setUserMessage("Tipos de taller no encontrados");
			response.setMoreInfo("http://localhost:8080/workshopType/ConsultAllWorkshopType");
			response.setData(null);
		}else {
			List<WorkshopTypeQueryDTO> workshopTypeDTO = workshopTypeEntity.stream().map(workshopType -> modelMapper.map(workshopType, WorkshopTypeQueryDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Tipos de taller encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/workshopType/ConsultAllWorkshopType");
			response.setData(workshopTypeDTO);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<WorkshopTypeQueryDTO> findByWorkshopTypeId(Integer WorkshopTypeId) {
		WorkshopType workshopType = iWorkshopTypeRepository.findById(WorkshopTypeId).orElse(null);
		Response<WorkshopTypeQueryDTO> response = new Response<>();
		if (workshopType == null) {
			response.setStatus(404);
			response.setUserMessage("Tipo taller no encontrado");
			response.setMoreInfo("http://localhost:8080/workshopType/ConsultById/{id}");
			response.setData(null);
		} else {
			WorkshopTypeQueryDTO workshopTypeDTO = modelMapper.map(workshopType, WorkshopTypeQueryDTO.class);
			response.setStatus(200);
			response.setUserMessage("Tipo taller encontrado con éxito");
			response.setMoreInfo("http://localhost:8080/workshopType/ConsultById/{id}");
			response.setData(workshopTypeDTO);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<WorkshopTypeCommandDTO> saveWorkshopType(WorkshopTypeCommandDTO workshopType) {
		Response<WorkshopTypeCommandDTO> response = new Response<>();
		if (workshopType != null) {
			WorkshopType WorkshopTypeEntity = this.modelMapper.map(workshopType, WorkshopType.class);
			WorkshopTypeEntity.setState(true);
			WorkshopType objWorkshopType = this.iWorkshopTypeRepository.save(WorkshopTypeEntity);
			WorkshopTypeCommandDTO workshopTypeDTO = this.modelMapper.map(objWorkshopType, WorkshopTypeCommandDTO.class);
			response.setStatus(200);
			response.setUserMessage("Tipo taller creado con éxito");
			response.setMoreInfo("http://localhost:8080/workshopType/SaveWorkshopType");
			response.setData(workshopTypeDTO);
		} else {
			response.setStatus(500);
			response.setUserMessage("Tipo taller no ha sido creado");
			response.setMoreInfo("http://localhost:8080/workshopType/SaveWorkshopType");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<WorkshopTypeQueryDTO> updateWorkshopType(Integer workshopTypeId, WorkshopTypeCommandDTO workshopType) {
		Response<WorkshopTypeQueryDTO> response = new Response<>();
		Optional<WorkshopType> optionalWorkshopType = this.iWorkshopTypeRepository.findById(workshopTypeId);
		if (optionalWorkshopType.isPresent()) {
			WorkshopType workshopTypeEntity1 = optionalWorkshopType.get();
			WorkshopType WorkshopTypeEntity = this.modelMapper.map(workshopType, WorkshopType.class);
			workshopTypeEntity1.setName(WorkshopTypeEntity.getName());
			this.iWorkshopTypeRepository.save(workshopTypeEntity1);
			WorkshopTypeQueryDTO workshopTypeDTO = this.modelMapper.map(workshopTypeEntity1, WorkshopTypeQueryDTO.class);
			response.setStatus(200);
			response.setUserMessage("Tipo taller actualizado con éxito");
			response.setMoreInfo("http://localhost:8080/workshopType/UpdateWorkshopType/{id}");
			response.setData(workshopTypeDTO);
		} else {
			response.setStatus(500);
			response.setUserMessage("Tipo taller que desea actualizar no se encuentra");
			response.setMoreInfo("http://localhost:8080/workshopType/UpdateWorkshopType/{id}");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<Boolean> disableWorkshopType(Integer workshopTypeId) {
		WorkshopType WorkshopTypeEntity = this.iWorkshopTypeRepository.findById(workshopTypeId).get();
		Response<Boolean> response = new Response<>();
		if (WorkshopTypeEntity != null) {
			if (WorkshopTypeEntity.getState() == true) {
				WorkshopTypeEntity.setState(false);
				this.iWorkshopTypeRepository.save(WorkshopTypeEntity);
				response.setStatus(200);
				response.setUserMessage("Tipo Taller deshabilitado con éxito");
				response.setMoreInfo("http://localhost:8080/workshopType/DisableWorkshopType/{id}");
				response.setData(true);
			} else {
				response.setStatus(500);
				response.setUserMessage("Tipo taller ya esta deshabilitado");
				response.setMoreInfo("http://localhost:8080/workshopType/DisableWorkshopType/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	@Transactional
	public Response<Boolean> enableWorkshopType(Integer workshopTypeId) {
		WorkshopType WorkshopTypeEntity = this.iWorkshopTypeRepository.findById(workshopTypeId).get();
		Response<Boolean> response = new Response<>();
		if (WorkshopTypeEntity != null) {
			if (WorkshopTypeEntity.getState() == false) {
				WorkshopTypeEntity.setState(true);
				this.iWorkshopTypeRepository.save(WorkshopTypeEntity);
				response.setStatus(200);
				response.setUserMessage("Tipo Taller habilitado con éxito");
				response.setMoreInfo("http://localhost:8080/workshopType/EnableWorkshopType/{id}");
				response.setData(true);
			} else {
				response.setStatus(500);
				response.setUserMessage("Tipo taller ya esta habilitado");
				response.setMoreInfo("http://localhost:8080/workshopType/EnableWorkshopType/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<WorkshopTypeQueryDTO>> findAllWorkshopTypeBytState(boolean state) {
		List<WorkshopType> workshopTypeEntity = iWorkshopTypeRepository.LstWorkshopTypeByState(state);
		Response<List<WorkshopTypeQueryDTO>> response = new Response<>();
		if(workshopTypeEntity.isEmpty()) {
			response.setStatus(200);
			response.setUserMessage("Tipos de taller no encontrados");
			response.setMoreInfo("http://localhost:8080/workshopType/ConsultAllWorkshopType");
			response.setData(null);
		}else {
			List<WorkshopTypeQueryDTO> workshopTypeDTO = workshopTypeEntity.stream().map(workshopType -> modelMapper.map(workshopType, WorkshopTypeQueryDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Tipos de taller encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/workshopType/ConsultAllWorkshopType");
			response.setData(workshopTypeDTO);
		}
		return response;
	}

//	private GenericPageableResponse validatePageList(Page<WorkshopType> workshopTypePage) {
//		List<WorkshopTypeQueryDTO> festivalDTOS = workshopTypePage.stream()
//				.map(x -> modelMapper.map(x, WorkshopTypeQueryDTO.class)).collect(Collectors.toList());
//		return PageableUtils.createPageableResponse(workshopTypePage, festivalDTOS);
//	}

}
