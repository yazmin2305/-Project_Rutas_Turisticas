package com.ruta.sanJuanDePuelenje.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruta.sanJuanDePuelenje.DTO.LunchDTO;
import com.ruta.sanJuanDePuelenje.models.Lunch;
import com.ruta.sanJuanDePuelenje.repository.ILunchRepository;

@Service
public class LunchServiceImpl implements ILunchService{

	@Autowired
	private ILunchRepository iLunchRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<LunchDTO> findAllLunch() {
		List<Lunch> lunchEntity = iLunchRepository.findAll();
		List<LunchDTO> lunchDTOs = new ArrayList<>();
		lunchDTOs = lunchEntity.stream().map(lunch -> modelMapper.map(lunch, LunchDTO.class)).collect(Collectors.toList());
		return lunchDTOs;
	}

	@Override
	public LunchDTO findByLunchId(Integer lunchId) {
		Lunch lunch = iLunchRepository.findById(lunchId).orElse(null);
		LunchDTO lunchDTO = modelMapper.map(lunch, LunchDTO.class);
		return lunchDTO;
	}

	@Override
	public LunchDTO saveLunch(LunchDTO lunch) {
		Lunch lunchEntity  = this.modelMapper.map(lunch, Lunch.class);
		lunchEntity.setState(true);
		Lunch objLunch = this.iLunchRepository.save(lunchEntity);
		LunchDTO lunchDTO = this.modelMapper.map(objLunch, LunchDTO.class);
		return lunchDTO;
	}

	@Override
	public LunchDTO updateLunch(Integer lunchId, LunchDTO lunch) {
		Lunch lunchEntity = this.modelMapper.map(lunch, Lunch.class);
		LunchDTO lunchDTO = this.findByLunchId(lunchId);
		Lunch lunchEntity1 = this.modelMapper.map(lunchDTO, Lunch.class);
		lunchEntity1.setMenu(lunchEntity.getMenu());
		lunchEntity1.setUnitPrice(lunchEntity.getUnitPrice());
		lunchEntity1.setTotalPrice(lunchEntity.getTotalPrice());
		lunchEntity1.setState(lunchEntity.getState());
		lunchEntity1.setLstReserve(lunchEntity.getLstReserve());
		return lunchDTO;
	}

	@Override
	public Boolean disableLunch(Integer lunchId) {
		LunchDTO lunchDTO = this.findByLunchId(lunchId);
		Lunch lunchEntity = this.modelMapper.map(lunchDTO, Lunch.class);
		if(lunchEntity != null) {
			lunchEntity.setState(false);
			this.iLunchRepository.save(lunchEntity);
			return true;
		}
		return false;
	}

	@Override
	public List<LunchDTO> findAllLunchBytState(boolean state) {
		List<Lunch> lunchEntity = this.iLunchRepository.LstLunchByState(state);
		List<LunchDTO> lunchDTO = lunchEntity.stream().map(lunch -> modelMapper.map(lunch, LunchDTO.class)).collect(Collectors.toList());
		return lunchDTO;
	}

}
