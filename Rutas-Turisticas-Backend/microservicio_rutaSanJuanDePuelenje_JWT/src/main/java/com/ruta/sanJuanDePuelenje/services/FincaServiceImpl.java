package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruta.sanJuanDePuelenje.DTO.FincaDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.models.Finca;
import com.ruta.sanJuanDePuelenje.repository.IFincaRepository;

@Service
public class FincaServiceImpl implements IFincaService{
	
	@Autowired
	private IFincaRepository iFincaRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional(readOnly = true)
	public Response<List<FincaDTO>> findAllFincas() {
		List<Finca> fincaEntity = iFincaRepository.findAll();
		Response<List<FincaDTO>> response = new Response<>();
		if(fincaEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Fincas no encontradas");
			response.setMoreInfo("http://localhost:8080/finca/ConsultAllFincas");
			response.setData(null);
		}else {
			List<FincaDTO> fincaDTO = fincaEntity.stream().map(finca -> modelMapper.map(finca, FincaDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Fincas encontradas con éxito");
			response.setMoreInfo("http://localhost:8080/finca/ConsultAllFincas");
			response.setData(fincaDTO);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<FincaDTO> findByFincaId(Integer fincaId) {
		Finca finca = iFincaRepository.findById(fincaId).orElse(null);
		Response<FincaDTO> response = new Response<>();
		if(finca == null) {
			response.setStatus(404);
			response.setUserMessage("Finca no encontrada");
			response.setMoreInfo("http://localhost:8080/finca/ConsultById/{id}");
			response.setData(null);
		}else {
			FincaDTO fincaDTO = modelMapper.map(finca, FincaDTO.class);
			response.setStatus(200);
			response.setUserMessage("Finca encontrada con éxito");
			response.setMoreInfo("http://localhost:8080/finca/ConsultById/{id}");
			response.setData(fincaDTO);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<FincaDTO> saveFinca(FincaDTO finca) {
		Response<FincaDTO> response = new Response<>();
		if(finca != null) {
			Finca fincaEntity  = this.modelMapper.map(finca, Finca.class);
			fincaEntity.setState(true);
			Finca objFinca = this.iFincaRepository.save(fincaEntity);
			FincaDTO fincaDTO = this.modelMapper.map(objFinca, FincaDTO.class);
			response.setStatus(200);
			response.setUserMessage("Finca creada con éxito");
			response.setMoreInfo("http://localhost:8080/finca/SaveFinca");
			response.setData(fincaDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("Error al guardar la finca");
			response.setMoreInfo("http://localhost:8080/finca/SaveFinca");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<FincaDTO> updateFinca(Integer fincaId, FincaDTO finca) {
		Response<FincaDTO> response = new Response<>();
		if(finca != null && fincaId != null) {
			Finca fincaEntity = this.modelMapper.map(finca, Finca.class);
			Finca fincaEntity1 = this.iFincaRepository.findById(fincaId).get();
			fincaEntity1.setName(fincaEntity.getName());
			fincaEntity1.setDescription(fincaEntity.getDescription());
			fincaEntity1.setLocation(fincaEntity.getLocation());
			fincaEntity1.setState(fincaEntity.getState());
			fincaEntity1.setLstTalking(fincaEntity.getLstTalking());
			fincaEntity1.setLstWorkshop(fincaEntity.getLstWorkshop());
			fincaEntity1.setLstRecreation(fincaEntity.getLstRecreation());
			fincaEntity1.setLstLodging(fincaEntity.getLstLodging());
			fincaEntity1.setLstFestival(fincaEntity.getLstFestival());
			this.iFincaRepository.save(fincaEntity1);
			FincaDTO fincaDTO = this.modelMapper.map(fincaEntity1, FincaDTO.class);
			response.setStatus(200);
			response.setUserMessage("Finca actualizada con éxito");
			response.setMoreInfo("http://localhost:8080/finca/UpdateFinca/{id}");
			response.setData(fincaDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("Error al actualizar la finca");
			response.setMoreInfo("http://localhost:8080/finca/UpdateFinca/{id}");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<Boolean> disableFinca(Integer fincaId) {
		Finca fincaEntity = this.iFincaRepository.findById(fincaId).get();
		Response<Boolean> response = new Response<>();
		if(fincaEntity != null) {
			if(fincaEntity.getState() == true){
				fincaEntity.setState(false);
				this.iFincaRepository.save(fincaEntity);
				response.setStatus(200);
				response.setUserMessage("Finca deshabilitada con éxito");
				response.setMoreInfo("http://localhost:8080/finca/DisableFinca/{id}");
				response.setData(true);
			}else {
				response.setStatus(500);
				response.setUserMessage("La finca ya esta deshabilitada");
				response.setMoreInfo("http://localhost:8080/finca/disableUser/{id}");
				response.setData(false);
			}
			
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<FincaDTO>> findAllFincaBytState(boolean state) {
		List<Finca> fincaEntity = this.iFincaRepository.LstFincaByState(state);
		Response<List<FincaDTO>> response = new Response<>();
		if(!fincaEntity.isEmpty()) {
			List<FincaDTO> fincaDTO = fincaEntity.stream().map(finca -> modelMapper.map(finca, FincaDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Fincas encontradas con éxito");
			response.setMoreInfo("http://localhost:8080/finca/ConsultAllFincaByState");
			response.setData(fincaDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("No se encuentran las fincas relacionadas a este estado");
			response.setMoreInfo("http://localhost:8080/finca/ConsultAllFincaByState");
			response.setData(null);
		}		
		return response;
	}
	
	
}
