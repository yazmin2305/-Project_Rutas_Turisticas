package com.ruta.sanJuanDePuelenje.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruta.sanJuanDePuelenje.DTO.FestivalDTO;
import com.ruta.sanJuanDePuelenje.models.Festival;
import com.ruta.sanJuanDePuelenje.repository.IFestivalRepository;

@Service
public class FestivalServiceImpl implements IFestivalService{
	@Autowired
	private IFestivalRepository iFestivalRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<FestivalDTO> findAllFestival() {
		List<Festival> festivalEntity = iFestivalRepository.findAll();
		List<FestivalDTO> festivalDTO = new ArrayList<>();
		festivalDTO = festivalEntity.stream().map(festival -> modelMapper.map(festival, FestivalDTO.class)).collect(Collectors.toList());
		return festivalDTO;
	}

	@Override
	public FestivalDTO findByFestivalId(Integer festivalId) {
		Festival festival = iFestivalRepository.findById(festivalId).orElse(null);
		FestivalDTO festivalDTO = modelMapper.map(festival, FestivalDTO.class);
		return festivalDTO;
	}

	@Override
	public FestivalDTO saveFestival(FestivalDTO festival) {
		Festival festivalEntity  = this.modelMapper.map(festival, Festival.class);
		festivalEntity.setState(true);
		Festival objFestival = this.iFestivalRepository.save(festivalEntity);
		FestivalDTO festivalDTO = this.modelMapper.map(objFestival, FestivalDTO.class);
		return festivalDTO;
	}

	@Override
	public FestivalDTO updateFestival(Integer festivalId, FestivalDTO festival) {
		Festival festivalEntity = this.modelMapper.map(festival, Festival.class);
		FestivalDTO festivalDTO = this.findByFestivalId(festivalId);
		Festival festivalEntity1 = this.modelMapper.map(festivalDTO, Festival.class);
		festivalEntity1.setName(festivalEntity.getName());
		festivalEntity1.setDescription(festivalEntity.getDescription());
		festivalEntity1.setDate(festivalEntity.getDate());
		festivalEntity1.setFinca(festivalEntity.getFinca());
		festivalEntity1.setLstReserve(festivalEntity.getLstReserve());
		festivalEntity1.setState(festivalEntity.getState());
		this.iFestivalRepository.save(festivalEntity1);
		festivalDTO = this.modelMapper.map(festivalEntity1, FestivalDTO.class);
		return festivalDTO;
	}

	@Override
	public Boolean disableFestival(Integer festivalId) {
		FestivalDTO festivalDTO = this.findByFestivalId(festivalId);
		Festival festivalEntity = this.modelMapper.map(festivalDTO, Festival.class);
		if(festivalEntity != null) {
			festivalEntity.setState(false);
			this.iFestivalRepository.save(festivalEntity);
			return true;
		}
		return false;
	}

}
