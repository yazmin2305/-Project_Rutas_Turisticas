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
import com.ruta.sanJuanDePuelenje.DTO.Command.RecreationCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.RecreationQueryDTO;
import com.ruta.sanJuanDePuelenje.models.Recreation;
import com.ruta.sanJuanDePuelenje.repository.IRecreationRepository;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;
import com.ruta.sanJuanDePuelenje.util.PageableUtils;

@Service
public class RecreationServiceImpl implements IRecreationService {

	@Autowired
	private IRecreationRepository iRecreationRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional(readOnly = true)
	public GenericPageableResponse findAllRecreation(Pageable pageable) {
		Page<Recreation> recreationPage = this.iRecreationRepository.findAll(pageable);
		if (recreationPage.isEmpty())
			return GenericPageableResponse.emptyResponse("Actividades de recreación no encontradas");
		return this.validatePageList(recreationPage);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<RecreationQueryDTO> findByRecreationId(Integer recreationId) {
		Recreation recreation = iRecreationRepository.findById(recreationId).orElse(null);
		Response<RecreationQueryDTO> response = new Response<>();
		if (recreation == null) {
			response.setStatus(404);
			response.setUserMessage("Actividad no encontrada");
			response.setMoreInfo("http://localhost:8080/recreation/ConsultById/{id}");
			response.setData(null);
		}
		RecreationQueryDTO recreationDTO = modelMapper.map(recreation, RecreationQueryDTO.class);
		response.setStatus(200);
		response.setUserMessage("Actividad encontrada con éxito");
		response.setMoreInfo("http://localhost:8080/recreation/ConsultById/{id}");
		response.setData(recreationDTO);
		return response;
	}

	@Override
	@Transactional
	public Response<RecreationCommandDTO> saveRecreation(RecreationCommandDTO recreation) {
		Response<RecreationCommandDTO> response = new Response<>();
		if(recreation != null) {
			Recreation recreationEntity = this.modelMapper.map(recreation, Recreation.class);
			recreationEntity.setState(true);
			Recreation objRecreation = this.iRecreationRepository.save(recreationEntity);
			RecreationCommandDTO recreationDTO = this.modelMapper.map(objRecreation, RecreationCommandDTO.class);
			response.setStatus(200);
			response.setUserMessage("Actividad de recreación creada con éxito");
			response.setMoreInfo("http://localhost:8080/recreation/SaveRecreation");
			response.setData(recreationDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("Error al crear la actividad de recreación");
			response.setMoreInfo("http://localhost:8080/recreation/SaveRecreation");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<RecreationQueryDTO> updateRecreation(Integer recreationId, RecreationCommandDTO recreation) {
		Response<RecreationQueryDTO> response = new Response<>();
		if(recreation != null && recreationId != null) {
			Recreation recreationEntity = this.modelMapper.map(recreation, Recreation.class);
			Recreation recreationEntity1 = this.iRecreationRepository.findById(recreationId).get();
			recreationEntity1.setName(recreationEntity.getName());
			recreationEntity1.setDescription(recreationEntity.getDescription());
			recreationEntity1.setDuration(recreationEntity.getDuration());
			recreationEntity1.setAvailability(recreationEntity.getAvailability());
			recreationEntity1.setMaxAmountPerson(recreationEntity.getMaxAmountPerson());
			recreationEntity1.setUnitPrice(recreationEntity.getUnitPrice());
			recreationEntity1.setState(recreationEntity.getState());
			recreationEntity1.setFinca(recreationEntity.getFinca());
			this.iRecreationRepository.save(recreationEntity1);
			RecreationQueryDTO recreationDTO = this.modelMapper.map(recreationEntity1, RecreationQueryDTO.class);
			response.setStatus(200);
			response.setUserMessage("Actividad de recreación actualizada con éxito");
			response.setMoreInfo("http://localhost:8080/recreation/UpdateRecreation/{id}");
			response.setData(recreationDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("Error al actualizar la actividad de recreación");
			response.setMoreInfo("http://localhost:8080/recreation/UpdateRecreation/{id}");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<Boolean> disableRecreation(Integer recreationId) {
		Recreation recreationEntity = this.iRecreationRepository.findById(recreationId).get();
		Response<Boolean> response = new Response<>();
		if (recreationEntity != null) {
			if(recreationEntity.getState() == true){
				recreationEntity.setState(false);
				this.iRecreationRepository.save(recreationEntity);
				response.setStatus(200);
				response.setUserMessage("Actividad de recreación deshabilitada con éxito");
				response.setMoreInfo("http://localhost:8080/recreation/DisableRecreation/{id}");
				response.setData(true);
			}else {
				response.setStatus(500);
				response.setUserMessage("La actividad de recreación ya esta deshabilitada");
				response.setMoreInfo("http://localhost:8080/recreation/DisableRecreation/{id}");
				response.setData(false);
			}
		}
		return response;
	}
	
	@Override
	@Transactional
	public Response<Boolean> enableRecreation(Integer recreationId) {
		Recreation recreationEntity = this.iRecreationRepository.findById(recreationId).get();
		Response<Boolean> response = new Response<>();
		if (recreationEntity != null) {
			if(recreationEntity.getState() == false){
				recreationEntity.setState(true);
				this.iRecreationRepository.save(recreationEntity);
				response.setStatus(200);
				response.setUserMessage("Actividad de recreación habilitada con éxito");
				response.setMoreInfo("http://localhost:8080/recreation/EnableRecreation/{id}");
				response.setData(true);
			}else {
				response.setStatus(500);
				response.setUserMessage("La actividad de recreación ya esta habilitada");
				response.setMoreInfo("http://localhost:8080/recreation/EnableRecreation/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public GenericPageableResponse findAllRecreationBytState(boolean state, Pageable pageable) {
		Page<Recreation> recreationPage = this.iRecreationRepository.LstRecreationByState(state, pageable);
		if (recreationPage.isEmpty())
			return GenericPageableResponse.emptyResponse("No se encuentran actividades de recreación relacionados a este estado");
		return this.validatePageList(recreationPage);
	}
	
	private GenericPageableResponse validatePageList(Page<Recreation> recreationPage){
        List<RecreationQueryDTO> recreationDTOS = recreationPage.stream().map(x->modelMapper.map(x, RecreationQueryDTO.class)).collect(Collectors.toList());
        return PageableUtils.createPageableResponse(recreationPage, recreationDTOS);
 }

}
