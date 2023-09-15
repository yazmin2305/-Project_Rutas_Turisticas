package com.ruta.sanJuanDePuelenje.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Response<List<FestivalDTO>> findAllFestival() {
		List<Festival> festivalEntity = iFestivalRepository.findAll();
		if(festivalEntity.isEmpty()) {
			
		}
		List<FestivalDTO> festivalDTO = festivalEntity.stream().map(festival -> modelMapper.map(festival, FestivalDTO.class)).collect(Collectors.toList());
		Response<List<FestivalDTO>> response = new Response<>();
		response.setStatus(200);
		response.setUserMessage("Festivales encontrados con éxito");
		response.setMoreInfo("http://localhost:8080/festival/ConsultAllFestival");
		response.setData(festivalDTO);
		return response;
	}

	@Override
	public Response<FestivalDTO> findByFestivalId(Integer festivalId) {
		Festival festival = iFestivalRepository.findById(festivalId).orElse(null);
		if(festival == null) {
			
		}
		FestivalDTO festivalDTO = modelMapper.map(festival, FestivalDTO.class);
		Response<FestivalDTO> response = new Response<>();
		response.setStatus(200);
		response.setUserMessage("Festival encontrado con éxito");
		response.setMoreInfo("http://localhost:8080/festival/ConsultFestivalById/{id}");
		response.setData(festivalDTO);
		return response;
	}

	@Override
	public Response<FestivalDTO> saveFestival(FestivalDTO festival) {
		Festival festivalEntity  = this.modelMapper.map(festival, Festival.class);
		Response<FestivalDTO> response = new Response<>();
		if(festival != null) {
			festivalEntity.setState(true);
			Festival objFestival = this.iFestivalRepository.save(festivalEntity);
			FestivalDTO festivalDTO = this.modelMapper.map(objFestival, FestivalDTO.class);
			response.setStatus(200);
			response.setUserMessage("Festival creado con éxito");
			response.setMoreInfo("http://localhost:8080/festival/SaveFestival");
			response.setData(festivalDTO);
		}
		return response;
	}

	@Override
	public Response<FestivalDTO> updateFestival(Integer festivalId, FestivalDTO festival) {
		Festival festivalEntity = this.modelMapper.map(festival, Festival.class);
		Response<FestivalDTO> response = new Response<>();
		if(festival != null && festivalId != null) {
			//FestivalDTO festivalDTO = this.findByFestivalId(festivalId);
			Festival festivalEntity1 = this.iFestivalRepository.findById(festivalId).get();
			festivalEntity1.setName(festivalEntity.getName());
			festivalEntity1.setDescription(festivalEntity.getDescription());
			festivalEntity1.setDate(festivalEntity.getDate());
			festivalEntity1.setFinca(festivalEntity.getFinca());
			festivalEntity1.setLstReserve(festivalEntity.getLstReserve());
			festivalEntity1.setState(festivalEntity.getState());
			this.iFestivalRepository.save(festivalEntity1);
			FestivalDTO festivalDTO = this.modelMapper.map(festivalEntity1, FestivalDTO.class);
			response.setStatus(200);
			response.setUserMessage("Festival actualizado con éxito");
			response.setMoreInfo("http://localhost:8080/festival/UpdateFestival/{id}");
			response.setData(festivalDTO);
		}
		return response;
	}

	@Override
	public Response<Boolean> disableFestival(Integer festivalId) {
		//FestivalDTO festivalDTO = this.findByFestivalId(festivalId);
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
	public Response<List<FestivalDTO>> findAllFestivalBytState(boolean state) {
		List<Festival> festivalEntity = this.iFestivalRepository.LstFestivalByState(state);
		Response<List<FestivalDTO>> response = new Response<>();
		if(!festivalEntity.isEmpty()) {
			List<FestivalDTO> festivalDTO = festivalEntity.stream().map(festival -> modelMapper.map(festival, FestivalDTO.class)).collect(Collectors.toList());
			response.setStatus(200);
			response.setUserMessage("Festivales encontrados con éxito");
			response.setMoreInfo("http://localhost:8080/festival/ConsultAllFestivalByState");
			response.setData(festivalDTO);
		}
		return response;
	}

}
