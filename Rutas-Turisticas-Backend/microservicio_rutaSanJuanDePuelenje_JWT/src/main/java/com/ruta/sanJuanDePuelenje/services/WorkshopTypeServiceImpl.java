package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.WorkshopTypeDTO;
import com.ruta.sanJuanDePuelenje.models.WorkshopType;
import com.ruta.sanJuanDePuelenje.repository.IWorkshopTypeRepository;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;
import com.ruta.sanJuanDePuelenje.util.PageableUtils;

@Service
public class WorkshopTypeServiceImpl implements IWorkshopTypeService {

	@Autowired
	private IWorkshopTypeRepository iWorkshopTypeRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional(readOnly = true)
	public GenericPageableResponse findAllWorkshopTypes(Pageable pageable) {
		Page<WorkshopType> workshopTypePage = this.iWorkshopTypeRepository.findAll(pageable);
		if (workshopTypePage.isEmpty())
			return GenericPageableResponse.emptyResponse("Tipos de talleres no encontrados");
		return this.validatePageList(workshopTypePage);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<WorkshopTypeDTO> findByWorkshopTypeId(Integer WorkshopTypeId) {
		WorkshopType workshopType = iWorkshopTypeRepository.findById(WorkshopTypeId).orElse(null);
		Response<WorkshopTypeDTO> response = new Response<>();
		if (workshopType == null) {
			response.setStatus(404);
			response.setUserMessage("Tipo taller no encontrado");
			response.setMoreInfo("http://localhost:8080/workshopType/ConsultById/{id}");
			response.setData(null);
		} else {
			WorkshopTypeDTO workshopTypeDTO = modelMapper.map(workshopType, WorkshopTypeDTO.class);
			response.setStatus(200);
			response.setUserMessage("Tipo taller encontrado con éxito");
			response.setMoreInfo("http://localhost:8080/workshopType/ConsultById/{id}");
			response.setData(workshopTypeDTO);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<WorkshopTypeDTO> saveWorkshopType(WorkshopTypeDTO workshopType) {
		Response<WorkshopTypeDTO> response = new Response<>();
		if (workshopType != null) {
			WorkshopType WorkshopTypeEntity = this.modelMapper.map(workshopType, WorkshopType.class);
			WorkshopTypeEntity.setState(true);
			WorkshopType objWorkshopType = this.iWorkshopTypeRepository.save(WorkshopTypeEntity);
			WorkshopTypeDTO workshopTypeDTO = this.modelMapper.map(objWorkshopType, WorkshopTypeDTO.class);
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
	public Response<WorkshopTypeDTO> updateWorkshopType(Integer workshopTypeId, WorkshopTypeDTO workshopType) {
		Response<WorkshopTypeDTO> response = new Response<>();
		if (workshopType != null && workshopTypeId != null) {
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
		} else {
			response.setStatus(500);
			response.setUserMessage("Tipo taller no ha sido actualizado");
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
	public GenericPageableResponse findAllWorkshopTypeBytState(boolean state, Pageable pageable) {
		Page<WorkshopType> workshopTypePage = this.iWorkshopTypeRepository.LstWorkshopTypeByState(state, pageable);
		if (workshopTypePage.isEmpty())
			return GenericPageableResponse
					.emptyResponse("No se encuentran tipos de talleres relacionados a este estado");
		return this.validatePageList(workshopTypePage);
	}

	private GenericPageableResponse validatePageList(Page<WorkshopType> workshopTypePage) {
		List<WorkshopTypeDTO> festivalDTOS = workshopTypePage.stream()
				.map(x -> modelMapper.map(x, WorkshopTypeDTO.class)).collect(Collectors.toList());
		return PageableUtils.createPageableResponse(workshopTypePage, festivalDTOS);
	}

}
