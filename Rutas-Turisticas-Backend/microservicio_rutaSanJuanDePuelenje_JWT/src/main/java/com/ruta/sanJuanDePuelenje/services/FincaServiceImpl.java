package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.FincaCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.FincaQueryDTO;
import com.ruta.sanJuanDePuelenje.models.Finca;
import com.ruta.sanJuanDePuelenje.repository.IFincaRepository;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;
import com.ruta.sanJuanDePuelenje.util.PageableUtils;

@Service
public class FincaServiceImpl implements IFincaService{
	
	@Autowired
	private IFincaRepository iFincaRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional(readOnly = true)
	public Response<List<FincaQueryDTO>> findAllFincas() {
		List<Finca> fincaEntity = iFincaRepository.findAll();
		Response<List<FincaQueryDTO>> response = new Response<>();
		if(fincaEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Fincas no encontradas");
			response.setMoreInfo("http://localhost:8080/finca/ConsultAllFincas");
			response.setData(null);
		}else {
			List<FincaQueryDTO> fincaDTO = fincaEntity.stream().map(finca -> modelMapper.map(finca, FincaQueryDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Fincas encontradas con éxito");
			response.setMoreInfo("http://localhost:8080/finca/ConsultAllFincas");
			response.setData(fincaDTO);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<FincaQueryDTO>> findAllFincasBytRuta(Integer rutaId) {
		List<Finca> fincaEntity = iFincaRepository.findAllFincasByRuta(rutaId);
		Response<List<FincaQueryDTO>> response = new Response<>();
		if(fincaEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Fincas no encontradas");
			response.setMoreInfo("http://localhost:8080/finca/ConsultAllFincaByRuta/{rutaId}");
			response.setData(null);
		}else {
			List<FincaQueryDTO> fincaDTO = fincaEntity.stream().map(finca -> modelMapper.map(finca, FincaQueryDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Fincas encontradas con éxito");
			response.setMoreInfo("http://localhost:8080/finca/ConsultAllFincaByRuta/{rutaId}");
			response.setData(fincaDTO);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<FincaQueryDTO> findByFincaId(Integer fincaId) {
		Finca finca = iFincaRepository.findById(fincaId).orElse(null);
		Response<FincaQueryDTO> response = new Response<>();
		if(finca == null) {
			response.setStatus(404);
			response.setUserMessage("Finca no encontrada");
			response.setMoreInfo("http://localhost:8080/finca/ConsultById/{id}");
			response.setData(null);
		}else {
			FincaQueryDTO fincaDTO = modelMapper.map(finca, FincaQueryDTO.class);
			response.setStatus(200);
			response.setUserMessage("Finca encontrada con éxito");
			response.setMoreInfo("http://localhost:8080/finca/ConsultById/{id}");
			response.setData(fincaDTO);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<FincaCommandDTO> saveFinca(FincaCommandDTO finca) {
		Response<FincaCommandDTO> response = new Response<>();
		if(finca != null) {
			Finca fincaEntity  = this.modelMapper.map(finca, Finca.class);
			fincaEntity.setState(true);
			Finca objFinca = this.iFincaRepository.save(fincaEntity);
			FincaCommandDTO fincaDTO = this.modelMapper.map(objFinca, FincaCommandDTO.class);
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
	public Response<FincaQueryDTO> updateFinca(Integer fincaId, FincaCommandDTO finca) {
		Response<FincaQueryDTO> response = new Response<>();
		Optional<Finca> optionalFinca = this.iFincaRepository.findById(fincaId);
		if(optionalFinca.isPresent()) {
			Finca fincaEntity1 = optionalFinca.get();
			Finca fincaEntity = this.modelMapper.map(finca, Finca.class);
			fincaEntity1.setName(fincaEntity.getName());
			fincaEntity1.setDescription(fincaEntity.getDescription());
			fincaEntity1.setLocation(fincaEntity.getLocation());
			fincaEntity1.setState(fincaEntity.getState());
			this.iFincaRepository.save(fincaEntity1);
			FincaQueryDTO fincaDTO = this.modelMapper.map(fincaEntity1, FincaQueryDTO.class);
			response.setStatus(200);
			response.setUserMessage("Finca actualizada con éxito");
			response.setMoreInfo("http://localhost:8080/finca/UpdateFinca/{id}");
			response.setData(fincaDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("La finca que desea actualizar no se encuentra");
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
	@Transactional
	public Response<Boolean> enableFinca(Integer fincaId) {
		Finca fincaEntity = this.iFincaRepository.findById(fincaId).get();
		Response<Boolean> response = new Response<>();
		if (fincaEntity != null) {
			if (fincaEntity.getState() == false) {
				fincaEntity.setState(true);
				this.iFincaRepository.save(fincaEntity);
				response.setStatus(200);
				response.setUserMessage("Finca habilitada con éxito");
				response.setMoreInfo("http://localhost:8080/finca/EnableFinca/{id}");
				response.setData(true);
			} else {
				response.setStatus(500);
				response.setUserMessage("La finca ya esta habilitada");
				response.setMoreInfo("http://localhost:8080/finca/EnableFinca/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public GenericPageableResponse findAllFincaBytState(boolean state, Pageable pageable) {
		Page<Finca> fincaPage = this.iFincaRepository.LstFincaByState(state, pageable);
		if (fincaPage.isEmpty())
			return GenericPageableResponse.emptyResponse("No se encuentran fincas relacionadas a este estado");
		return this.validatePageList(fincaPage);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Response<List<FincaQueryDTO>> findAllFincaBytStateByRuta(boolean state, Integer rutaId) {
		List<Finca> fincaEntity = this.iFincaRepository.LstFincaByStateByRuta(state, rutaId);
		Response<List<FincaQueryDTO>> response = new Response<>();
		if(fincaEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Fincas no encontradas");
			response.setMoreInfo("http://localhost:8080/finca/ConsultAllFincaByStateByRuta/{state}/{idRuta}");
			response.setData(null);
		}else {
			List<FincaQueryDTO> fincaDTO = fincaEntity.stream().map(finca -> modelMapper.map(finca, FincaQueryDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Fincas encontradas con éxito");
			response.setMoreInfo("http://localhost:8080/finca/ConsultAllFincaByStateByRuta/{state}/{idRuta}");
			response.setData(fincaDTO);
		}
		return response;
	}
	
	private GenericPageableResponse validatePageList(Page<Finca> fincaPage){
        List<FincaQueryDTO> fincaDTOS = fincaPage.stream().map(x->modelMapper.map(x, FincaQueryDTO.class)).collect(Collectors.toList());
        return PageableUtils.createPageableResponse(fincaPage, fincaDTOS);
	}
	
	
}
