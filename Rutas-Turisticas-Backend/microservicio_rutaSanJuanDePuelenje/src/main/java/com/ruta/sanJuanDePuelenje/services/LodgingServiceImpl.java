package com.ruta.sanJuanDePuelenje.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruta.sanJuanDePuelenje.DTO.LodgingDTO;
import com.ruta.sanJuanDePuelenje.models.Lodging;
import com.ruta.sanJuanDePuelenje.repository.ILodgingRepository;

@Service
public final class LodgingServiceImpl implements ILodgingService{
	
	@Autowired
	private ILodgingRepository iLodgingRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<LodgingDTO> findAllLodging() {
		List<Lodging> lodgingEntity = iLodgingRepository.findAll();
		List<LodgingDTO> lodgingDTOs = new ArrayList<>();
		lodgingDTOs = lodgingEntity.stream().map(lodging -> modelMapper.map(lodging, LodgingDTO.class)).collect(Collectors.toList());
		return lodgingDTOs;
	}

	@Override
	public LodgingDTO findByLodgingId(Integer lodgingId) {
		Lodging lodging = iLodgingRepository.findById(lodgingId).orElse(null);
		LodgingDTO lodgingDTO = modelMapper.map(lodging, LodgingDTO.class);
		return lodgingDTO;
	}

	@Override
	public LodgingDTO saveLodging(LodgingDTO lodging) {
		Lodging lodgingEntity  = this.modelMapper.map(lodging, Lodging.class);
		lodgingEntity.setState(true);
		Lodging objLodging = this.iLodgingRepository.save(lodgingEntity);
		LodgingDTO lodgingDTO = this.modelMapper.map(objLodging, LodgingDTO.class);
		return lodgingDTO;
	}

	@Override
	public LodgingDTO updateLodging(Integer lodgingId, LodgingDTO lodging) {
		Lodging lodgingEntity = this.modelMapper.map(lodging, Lodging.class);
		LodgingDTO lodgingDTO = this.findByLodgingId(lodgingId);
		Lodging lodgingEntity1 = this.modelMapper.map(lodgingDTO, Lodging.class);
		lodgingEntity1.setName(lodgingEntity.getName());
		lodgingEntity1.setDescription(lodgingEntity.getDescription());
		lodgingEntity1.setBedsAvailable(lodgingEntity.getBedsAvailable());
		lodgingEntity1.setNumberNights(lodgingEntity.getNumberNights());
		lodgingEntity1.setMaxAmountPerson(lodgingEntity.getMaxAmountPerson());
		lodgingEntity1.setUnitPrice(lodgingEntity.getUnitPrice());
		lodgingEntity1.setTotalPrice(lodgingEntity.getTotalPrice());
		lodgingEntity1.setState(lodgingEntity.getState());
		lodgingEntity1.setFinca(lodgingEntity.getFinca());
		lodgingEntity1.setLstReserve(lodgingEntity.getLstReserve());
		return lodgingDTO;
	}

	@Override
	public Boolean disableLodging(Integer lodgingId) {
		LodgingDTO lodgingDTO = this.findByLodgingId(lodgingId);
		Lodging lodgingEntity = this.modelMapper.map(lodgingDTO, Lodging.class);
		if(lodgingEntity != null) {
			lodgingEntity.setState(false);
			this.iLodgingRepository.save(lodgingEntity);
			return true;
		}
		return false;
	}

}
