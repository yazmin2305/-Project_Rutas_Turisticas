package com.ruta.sanJuanDePuelenje.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ruta.sanJuanDePuelenje.DTO.FestivalDTO;
import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.models.Festival;
import com.ruta.sanJuanDePuelenje.repository.IFestivalRepository;


@Service
public class FestivalServiceImpl implements IFestivalService{
	@Autowired
	private IFestivalRepository iFestivalRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional(readOnly = true)
	public Response<List<FestivalDTO>> findAllFestival() {
		List<Festival> festivalEntity = iFestivalRepository.findAll();
		Response<List<FestivalDTO>> response = new Response<>();
		if(festivalEntity.isEmpty()) {
			response.setStatus(404);
			response.setUserMessage("Festivales no encontrados");
			response.setMoreInfo("http://localhost:8080/festival/ConsultAllFestival");
			response.setData(null);
		}else {
			List<FestivalDTO> festivalDTO = festivalEntity.stream().map(festival -> modelMapper.map(festival, FestivalDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Festivales encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/festival/ConsultAllFestival");
			response.setData(festivalDTO);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<FestivalDTO> findByFestivalId(Integer festivalId) {
		Festival festival = iFestivalRepository.findById(festivalId).orElse(null);
		Response<FestivalDTO> response = new Response<>();
		if(festival == null) {
			response.setStatus(404);
			response.setUserMessage("Festival no encontrado");
			response.setMoreInfo("http://localhost:8080/festival/ConsultFestivalById/{id}");
			response.setData(null);
		}else {
			FestivalDTO festivalDTO = modelMapper.map(festival, FestivalDTO.class);
			response.setStatus(200);
			response.setUserMessage("Festival encontrado con éxito");
			response.setMoreInfo("http://localhost:8080/festival/ConsultFestivalById/{id}");
			response.setData(festivalDTO);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<FestivalDTO> saveFestival(FestivalDTO festival, MultipartFile imagen) {
		Response<FestivalDTO> response = new Response<>();
		if(festival != null) {
			Festival festivalEntity  = this.modelMapper.map(festival, Festival.class);
			festivalEntity.setState(true);
			Festival objFestival = this.iFestivalRepository.save(festivalEntity);
			FestivalDTO festivalDTO = this.modelMapper.map(objFestival, FestivalDTO.class);
			response.setStatus(200);
			response.setUserMessage("Festival creado con éxito");
			response.setMoreInfo("http://localhost:8080/festival/SaveFestival");
			response.setData(festivalDTO);
			if(!imagen.isEmpty()) {
				String rutaAbsoluta  = "C://Producto//imagenes";
				
				try {
					byte[] bytesImg = imagen.getBytes();
					Path rutaCompleta = Paths.get(rutaAbsoluta + "//"+ imagen.getOriginalFilename());
					Files.write(rutaCompleta, bytesImg);
					festivalEntity.setImagen(imagen.getOriginalFilename());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}else {
			response.setStatus(500);
			response.setUserMessage("Error al crear el festival");
			response.setMoreInfo("http://localhost:8080/festival/SaveFestival");
			response.setData(null);
		}
		
		return response;
	}

	@Override
	@Transactional
	public Response<FestivalDTO> updateFestival(Integer festivalId, FestivalDTO festival) {
		Response<FestivalDTO> response = new Response<>();
		if(festival != null && festivalId != null) {
			Festival festivalEntity = this.modelMapper.map(festival, Festival.class);
			Festival festivalEntity1 = this.iFestivalRepository.findById(festivalId).get();
			festivalEntity1.setName(festivalEntity.getName());
			festivalEntity1.setDescription(festivalEntity.getDescription());
			festivalEntity1.setDate(festivalEntity.getDate());
			festivalEntity1.setFinca(festivalEntity.getFinca());
			festivalEntity1.setState(festivalEntity.getState());
			this.iFestivalRepository.save(festivalEntity1);
			FestivalDTO festivalDTO = this.modelMapper.map(festivalEntity1, FestivalDTO.class);
			response.setStatus(200);
			response.setUserMessage("Festival actualizado con éxito");
			response.setMoreInfo("http://localhost:8080/festival/UpdateFestival/{id}");
			response.setData(festivalDTO);
		}else {
			response.setStatus(500);
			response.setUserMessage("El festival no se puede actualizar");
			response.setMoreInfo("http://localhost:8080/festival/UpdateFestival/{id}");
			response.setData(null);
		}
		return response;
	}

	@Override
	@Transactional
	public Response<Boolean> disableFestival(Integer festivalId) {
		Festival festivalEntity = this.iFestivalRepository.findById(festivalId).get();
		Response<Boolean> response = new Response<>();
		if(festivalEntity != null) {
			if(festivalEntity.getState() == true){
				festivalEntity.setState(false);
				this.iFestivalRepository.save(festivalEntity);
				response.setStatus(200);
				response.setUserMessage("Festival deshabilitado con éxito");
				response.setMoreInfo("http://localhost:8080/festival/DisableFestival/{id}");
				response.setData(true);
			}else {
				response.setStatus(500);
				response.setUserMessage("El festival ya esta deshabilitado");
				response.setMoreInfo("http://localhost:8080/user/DisableUser/{id}");
				response.setData(false);
			}
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<FestivalDTO>> findAllFestivalBytState(boolean state) {
		List<Festival> festivalEntity = this.iFestivalRepository.LstFestivalByState(state);
		Response<List<FestivalDTO>> response = new Response<>();
		if(!festivalEntity.isEmpty()) {
			List<FestivalDTO> festivalDTO = festivalEntity.stream().map(festival -> modelMapper.map(festival, FestivalDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Festivales encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/festival/ConsultAllFestivalByState");
			response.setData(festivalDTO);
		}else {
			response.setStatus(404);
			response.setUserMessage("No se encuentran festivales relacionados a este estado");
			response.setMoreInfo("http://localhost:8080/festival/ConsultAllFestivalByState");
			response.setData(null);
		}
		return response;
	}


}
