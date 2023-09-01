package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruta.sanJuanDePuelenje.DTO.FestivalDTO;
import com.ruta.sanJuanDePuelenje.models.Festival;
import com.ruta.sanJuanDePuelenje.repository.IFestivalRepository;

public class FestivalServiceImpl implements IFestivalService{
	@Autowired
	private IFestivalRepository iFestivalRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<FestivalDTO> findAllFestival() {
		List<Festival> festivalEntity = this.iFestivalRepository.findAll();
		List<FestivalDTO> festivalDTO = this.modelMapper.map(festivalEntity, FestivalDTO);
				
		return festivalDTO;
	}

	@Override
	public FestivalDTO findByFestivalId(Integer festivalId) {
		Festival festival = iFestivalRepository.findById(festivalId).orElse(null);
		return festival;
	}

	@Override
	public FestivalDTO saveFestival(Festival festival) {
		return iFestivalRepository.save(festival);
	}

	@Override
	public FestivalDTO updateFestival(Integer festivalId, Festival festival) {
		Festival festival1 = this.findByFestivalId(festivalId);
		festival1.setName(festival.getName());
		festival1.setDescription(festival.getDescription());
		festival1.setDate(festival.getDate());
		festival1.setFinca(festival.getFinca());
		festival1.setLstReserve(festival.getLstReserve());
		festival.setState(festival.getState());
		return festival1;
	}

	@Override
	public Boolean disableFestival(Integer festivalId) {
		Festival festival = this.findByFestivalId(festivalId);
		if(festival != null) {
			festival.setState(false);
			return true;
		}
		return false;
	}

}
