package com.ruta.sanJuanDePuelenje.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruta.sanJuanDePuelenje.DTO.RecreationDTO;
import com.ruta.sanJuanDePuelenje.models.Recreation;
import com.ruta.sanJuanDePuelenje.repository.IRecreationRepository;

@Service
public class RecreationServiceImpl implements IRecreationService{

	@Autowired
	private IRecreationRepository iRecreationRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<RecreationDTO> findAllRecreation() {
		List<Recreation> recreationEntity = iRecreationRepository.findAll();
		List<RecreationDTO> recreationDTOs = new ArrayList<>();
		recreationDTOs = recreationEntity.stream().map(recreation -> modelMapper.map(recreation, RecreationDTO.class)).collect(Collectors.toList());
		return recreationDTOs;
	}

	@Override
	public RecreationDTO findByRecreationId(Integer recreationId) {
		Recreation recreation = iRecreationRepository.findById(recreationId).orElse(null);
		RecreationDTO recreationDTO = modelMapper.map(recreation, RecreationDTO.class);
		return recreationDTO;
	}

	@Override
	public RecreationDTO saveRecreation(RecreationDTO recreation) {
		Recreation recreationEntity  = this.modelMapper.map(recreation, Recreation.class);
		recreationEntity.setState(true);
		Recreation objRecreation = this.iRecreationRepository.save(recreationEntity);
		RecreationDTO recreationDTO = this.modelMapper.map(objRecreation, RecreationDTO.class);
		return recreationDTO;
	}

	@Override
	public RecreationDTO updateRecreation(Integer recreationId, RecreationDTO recreation) {
		Recreation recreationEntity = this.modelMapper.map(recreation, Recreation.class);
		RecreationDTO recreationDTO = this.findByRecreationId(recreationId);
		Recreation recreationEntity1 = this.modelMapper.map(recreationDTO, Recreation.class);
		recreationEntity1.setName(recreationEntity.getName());
		recreationEntity1.setDescription(recreationEntity.getDescription());
		recreationEntity1.setDuration(recreationEntity.getDuration());
		recreationEntity1.setAvailability(recreationEntity.getAvailability());
		recreationEntity1.setMaxAmountPerson(recreationEntity.getMaxAmountPerson());
		recreationEntity1.setUnitPrice(recreationEntity.getUnitPrice());
		recreationEntity1.setTotalPrice(recreationEntity.getTotalPrice());
		recreationEntity1.setState(recreationEntity.getState());
		recreationEntity1.setFinca(recreationEntity.getFinca());
		recreationEntity1.setLstReserve(recreationEntity.getLstReserve());
		return recreationDTO;
	}

	@Override
	public Boolean disableRecreation(Integer recreationId) {
		RecreationDTO recreationDTO = this.findByRecreationId(recreationId);
		Recreation recreationEntity = this.modelMapper.map(recreationDTO, Recreation.class);
		if (recreationEntity != null) {
			recreationEntity.setState(false);
			this.iRecreationRepository.save(recreationEntity);
			return true;
		}
		return false;
	}

	@Override
	public List<RecreationDTO> findAllRecreationBytState(boolean state) {
		List<Recreation> recreationEntity = this.iRecreationRepository.LstRecreationByState(state);
		List<RecreationDTO> recreationDTO = recreationEntity.stream().map(recreation -> modelMapper.map(recreation, RecreationDTO.class)).collect(Collectors.toList());
		return recreationDTO;
	}

}
