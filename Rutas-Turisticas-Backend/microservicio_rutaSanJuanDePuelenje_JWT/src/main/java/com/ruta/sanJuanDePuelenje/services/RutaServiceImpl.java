package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.RutaDTO;
import com.ruta.sanJuanDePuelenje.models.Ruta;
import com.ruta.sanJuanDePuelenje.repository.IRutaRepository;

@Service
public class RutaServiceImpl implements IRutaService{

	@Autowired
	private IRutaRepository iRutaRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional(readOnly = true)
	public Response<List<RutaDTO>> findAllRutas() {
		List<Ruta> rutaEntity = iRutaRepository.findAll();
		Response<List<RutaDTO>> response = new Response<>();
		if(rutaEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Rutas no encontradas");
			response.setMoreInfo("http://localhost:8080/ruta/ConsultAllRutas");
			response.setData(null);
		}else {
			List<RutaDTO> rutaDTOs = rutaEntity.stream().map(ruta -> modelMapper.map(ruta, RutaDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Rutas encontradas con éxito");
			response.setMoreInfo("http://localhost:8080/ruta/ConsultAllRutas");
			response.setData(rutaDTOs);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<RutaDTO> findByRutaId(Integer RutaId) {
		Ruta ruta = iRutaRepository.findById(RutaId).orElse(null);
		Response<RutaDTO> response = new Response<>();
		if(ruta == null) {
			response.setStatus(404);
			response.setUserMessage("Ruta no encontrada");
			response.setMoreInfo("http://localhost:8080/ruta/ConsultById/{id}");
			response.setData(null);
		}else {
			RutaDTO rutaDTO = modelMapper.map(ruta, RutaDTO.class);
			response.setStatus(200);
			response.setUserMessage("Ruta encontrada con éxito");
			response.setMoreInfo("http://localhost:8080/ruta/ConsultById/{id}");
			response.setData(rutaDTO);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<RutaDTO> saveRuta(RutaDTO ruta) {
		Response<RutaDTO> response = new Response<>();
		if(ruta != null) {
			Ruta rutaEntity  = this.modelMapper.map(ruta, Ruta.class);
			rutaEntity.setState(true);
			Ruta objRuta = this.iRutaRepository.save(rutaEntity);
			RutaDTO rutaDTO = this.modelMapper.map(objRuta, RutaDTO.class);
			response.setStatus(200);
			response.setUserMessage("Ruta creada con éxito");
			response.setMoreInfo("http://localhost:8080/ruta/SaveRuta");
			response.setData(rutaDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("Error al crear la ruta");
			response.setMoreInfo("http://localhost:8080/ruta/SaveRuta");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<RutaDTO> updateRuta(Integer rutaId, RutaDTO ruta) {
		Response<RutaDTO> response = new Response<>();
		if(ruta != null && rutaId != null) {
			Ruta rutaEntity = this.modelMapper.map(ruta, Ruta.class);
			Ruta rutaEntity1 = this.iRutaRepository.findById(rutaId).get();
			rutaEntity1.setName(rutaEntity.getName());
			rutaEntity1.setState(rutaEntity.getState());
			rutaEntity1.setLstTalking(rutaEntity.getLstTalking());
			rutaEntity1.setLstWorkshop(rutaEntity.getLstWorkshop());
			rutaEntity1.setLstRecreation(rutaEntity.getLstRecreation());
			rutaEntity1.setLstLodging(rutaEntity.getLstLodging());
			rutaEntity1.setLstFestival(rutaEntity.getLstFestival());
			rutaEntity1.setLstFinca(rutaEntity.getLstFinca());
			rutaEntity1.setLstLunch(rutaEntity.getLstLunch());
			rutaEntity1.setLstReserve(rutaEntity.getLstReserve());
			rutaEntity1.setLstUser(rutaEntity.getLstUser());
			RutaDTO rutaDTO = this.modelMapper.map(rutaEntity1, RutaDTO.class);
			response.setStatus(200);
			response.setUserMessage("Ruta actualizada con éxito");
			response.setMoreInfo("http://localhost:8080/ruta/UpdateRuta/{id}");
			response.setData(rutaDTO);		
		}else {
			response.setStatus(500);
			response.setUserMessage("Error al actualizar la ruta");
			response.setMoreInfo("http://localhost:8080/ruta/UpdateRuta/{id}");
			response.setData(null);		
		}
		return response;
	}

	@Override
	@Transactional
	public Response<Boolean> disableRuta(Integer rutaId) {
		Ruta rutaEntity = this.iRutaRepository.findById(rutaId).get();
		Response<Boolean> response = new Response<>();
		if (rutaEntity != null) {
			if(rutaEntity.getState() == true){
				rutaEntity.setState(false);
				this.iRutaRepository.save(rutaEntity);
				response.setStatus(200);
				response.setUserMessage("Ruta deshabilitada con éxito");
				response.setMoreInfo("http://localhost:8080/ruta/DisableRuta/{id}");
				response.setData(true);
			}else {
				response.setStatus(500);
				response.setUserMessage("La ruta ya está deshabilitada");
				response.setMoreInfo("http://localhost:8080/ruta/DisableRuta/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	public Response<List<RutaDTO>> findAllRutasBytState(boolean state) {
		List<Ruta> rutaEntity = this.iRutaRepository.LstRutasByState(state);
		Response<List<RutaDTO>> response = new Response<>();
		if(!rutaEntity.isEmpty()) {
			List<RutaDTO> rutaDTO = rutaEntity.stream().map(ruta -> modelMapper.map(ruta, RutaDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Rutas encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/ruta/ConsultAllRutasByState");
			response.setData(rutaDTO);
		}else {
			response.setStatus(404);
			response.setUserMessage("No existen rutas relacionadas a este estado");
			response.setMoreInfo("http://localhost:8080/ruta/ConsultAllRutasByState");
			response.setData(null);
		}
		return response;
	}


}
