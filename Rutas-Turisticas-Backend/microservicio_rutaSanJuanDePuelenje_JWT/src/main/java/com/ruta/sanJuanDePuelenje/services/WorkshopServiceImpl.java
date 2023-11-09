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
import com.ruta.sanJuanDePuelenje.DTO.Command.WorkshopCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.WorkshopQueryDTO;
import com.ruta.sanJuanDePuelenje.models.Workshop;
import com.ruta.sanJuanDePuelenje.repository.IWorkshopRerpository;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;
import com.ruta.sanJuanDePuelenje.util.PageableUtils;

@Service
public class WorkshopServiceImpl implements IWorkshopService{

	@Autowired
	private IWorkshopRerpository iWorkshopRerpository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional(readOnly = true)
	public Response<List<WorkshopQueryDTO>> findAllWorkshop() {
		List<Workshop> workshopEntity = iWorkshopRerpository.findAll();
		Response<List<WorkshopQueryDTO>> response = new Response<>();
		if(workshopEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Talleres no encontrados");
			response.setMoreInfo("http://localhost:8080/workshop/ConsultAllWorkshop");
			response.setData(null);
		}else {
			List<WorkshopQueryDTO> workshopDTOs = workshopEntity.stream().map(workshop -> modelMapper.map(workshop, WorkshopQueryDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Talleres encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/workshop/ConsultAllWorkshop");
			response.setData(workshopDTOs);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<WorkshopQueryDTO> findByWorkshopId(Integer workshopId) {
		Workshop workshop = iWorkshopRerpository.findById(workshopId).orElse(null);
		Response<WorkshopQueryDTO> response = new Response<>();
		if(workshop == null) {
			response.setStatus(200);
			response.setUserMessage("Taller no se encuentra");
			response.setMoreInfo("http://localhost:8080/workshop/ConsultById/{id}");
			response.setData(null);
		}else {
			WorkshopQueryDTO workshopDTO = modelMapper.map(workshop, WorkshopQueryDTO.class);
			response.setStatus(200);
			response.setUserMessage("Taller encontrado con éxito");
			response.setMoreInfo("http://localhost:8080/workshop/ConsultById/{id}");
			response.setData(workshopDTO);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<WorkshopCommandDTO> saveWorkshop(WorkshopCommandDTO workshop) {
		Response<WorkshopCommandDTO> response = new Response<>();
		if(workshop != null) {
			Workshop workshopEntity  = this.modelMapper.map(workshop, Workshop.class);
			workshopEntity.setState(true);
			Workshop objWorkshop = this.iWorkshopRerpository.save(workshopEntity);
			WorkshopCommandDTO workshopDTO = this.modelMapper.map(objWorkshop, WorkshopCommandDTO.class);
			response.setStatus(200);
			response.setUserMessage("Taller creado con éxito");
			response.setMoreInfo("http://localhost:8080/workshop/SaveWorkshop");
			response.setData(workshopDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("Error al crear el taller");
			response.setMoreInfo("http://localhost:8080/workshop/SaveWorkshop");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<WorkshopQueryDTO> updateWorkshop(Integer workshopId, WorkshopCommandDTO workshop) {
		Response<WorkshopQueryDTO> response = new Response<>();
		if(workshop != null && workshopId != null) {
			Workshop workshopEntity = this.modelMapper.map(workshop, Workshop.class);
			Workshop workshopEntity1 = this.iWorkshopRerpository.findById(workshopId).get();
			workshopEntity1.setName(workshopEntity.getName());
			workshopEntity1.setDescription(workshopEntity.getDescription());
			workshopEntity1.setDuration(workshopEntity.getDuration());
			workshopEntity1.setAvailability(workshopEntity.getAvailability());
			workshopEntity1.setMaxAmountPerson(workshopEntity.getMaxAmountPerson());
			workshopEntity1.setUnitPrice(workshopEntity.getUnitPrice());
			workshopEntity1.setWorkshopType(workshopEntity.getWorkshopType());
			workshopEntity1.setState(workshopEntity.getState());
			workshopEntity1.setFinca(workshopEntity.getFinca());
//			workshopEntity1.setLstReserve(workshopEntity.getLstReserve());
			this.iWorkshopRerpository.save(workshopEntity1);
			WorkshopQueryDTO workshopDTO = this.modelMapper.map(workshopEntity1, WorkshopQueryDTO.class);
			response.setStatus(200);
			response.setUserMessage("Taller actualizado con éxito");
			response.setMoreInfo("http://localhost:8080/workshop/UpdateWorkshop/{id}");
			response.setData(workshopDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("No se pudo actualizar el taller");
			response.setMoreInfo("http://localhost:8080/workshop/UpdateWorkshop/{id}");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<Boolean> disableWorkshop(Integer workshopId) {
		Workshop workshopEntity = this.iWorkshopRerpository.findById(workshopId).get();
		Response<Boolean> response = new Response<>();
		if (workshopEntity != null) {
			if(workshopEntity.getState() == true){
				workshopEntity.setState(false);
				this.iWorkshopRerpository.save(workshopEntity);
				response.setStatus(200);
				response.setUserMessage("Taller deshabilitado con éxito");
				response.setMoreInfo("http://localhost:8080/workshop/DisableWorkshop/{id}");
				response.setData(true);
			}else {
				response.setStatus(500);
				response.setUserMessage("El Taller ya esta deshabilitado");
				response.setMoreInfo("http://localhost:8080/workshop/DisableWorkshop/{id}");
				response.setData(false);
			}
			
		}
		return response;
	}
	
	@Override
	@Transactional
	public Response<Boolean> enableWorkshop(Integer workshopId) {
		Workshop workshopEntity = this.iWorkshopRerpository.findById(workshopId).get();
		Response<Boolean> response = new Response<>();
		if (workshopEntity != null) {
			if(workshopEntity.getState() == false){
				workshopEntity.setState(true);
				this.iWorkshopRerpository.save(workshopEntity);
				response.setStatus(200);
				response.setUserMessage("Taller habilitado con éxito");
				response.setMoreInfo("http://localhost:8080/workshop/EnableWorkshop/{id}");
				response.setData(true);
			}else {
				response.setStatus(500);
				response.setUserMessage("El Taller ya esta habilitado");
				response.setMoreInfo("http://localhost:8080/workshop/EnableWorkshop/{id}");
				response.setData(false);
			}
			
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public GenericPageableResponse findAllWorkshopBytState(boolean state, Pageable pageable) {
		Page<Workshop> workshopPage = this.iWorkshopRerpository.LstWorkshopByState(state, pageable);
		if (workshopPage.isEmpty())
			return GenericPageableResponse.emptyResponse("No se encuentran talleres relacionados a este estado");
		return this.validatePageList(workshopPage);
	}
	
	private GenericPageableResponse validatePageList(Page<Workshop> workshopPage) {
		List<WorkshopQueryDTO> workshopDTOS = workshopPage.stream().map(x -> modelMapper.map(x, WorkshopQueryDTO.class))
				.collect(Collectors.toList());
		return PageableUtils.createPageableResponse(workshopPage, workshopDTOS);
	}

}
