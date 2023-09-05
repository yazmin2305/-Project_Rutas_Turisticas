package com.ruta.sanJuanDePuelenje.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruta.sanJuanDePuelenje.DTO.FestivalDTO;
import com.ruta.sanJuanDePuelenje.DTO.WorkshopTypeDTO;
import com.ruta.sanJuanDePuelenje.models.Festival;
import com.ruta.sanJuanDePuelenje.models.WorkshopType;
import com.ruta.sanJuanDePuelenje.repository.IWorkshopTypeRepository;

public class WorkshopTypeServiceImpl implements IWorkshopTypeService{

	@Autowired
	private IWorkshopTypeRepository iWorkshopTypeRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<WorkshopTypeDTO> findAllWorkshopTypes() {
		List<WorkshopType> WorkshopTypeEntity = iWorkshopTypeRepository.findAll();
		List<WorkshopTypeDTO> workshopTypeDTO = new ArrayList<>();
		workshopTypeDTO = WorkshopTypeEntity.stream().map(workshopType -> modelMapper.map(workshopType, WorkshopTypeDTO.class)).collect(Collectors.toList());
		return workshopTypeDTO;
	}
	
	@Override
	public WorkshopTypeDTO findByWorkshopTypeId(Integer WorkshopTypeId) {
		WorkshopType workshopType = iWorkshopTypeRepository.findById(WorkshopTypeId).orElse(null);
		WorkshopTypeDTO workshopTypeDTO = modelMapper.map(workshopType, WorkshopTypeDTO.class);
		return workshopTypeDTO;
	}

	@Override
	public WorkshopTypeDTO saveWorkshopType(WorkshopTypeDTO workshopType) {
		WorkshopType WorkshopTypeEntity  = this.modelMapper.map(workshopType, WorkshopType.class);
		WorkshopTypeEntity.setState(true);
		WorkshopType objWorkshopType = this.iWorkshopTypeRepository.save(WorkshopTypeEntity);
		WorkshopTypeDTO workshopTypeDTO = this.modelMapper.map(objWorkshopType, WorkshopTypeDTO.class);
		return workshopTypeDTO;
	}

	@Override
	public WorkshopTypeDTO updateWorkshopType(Integer workshopTypeId, WorkshopTypeDTO workshopType) {
		WorkshopType WorkshopTypeEntity = this.modelMapper.map(workshopType, WorkshopType.class);
		WorkshopTypeDTO workshopTypeDTO = this.findByWorkshopTypeId(workshopTypeId);
		WorkshopType workshopTypeEntity1 = this.modelMapper.map(workshopTypeDTO, WorkshopType.class);
		workshopTypeEntity1.setName(WorkshopTypeEntity.getName());
		workshopTypeEntity1.setState(WorkshopTypeEntity.getState());
		workshopTypeEntity1.setLstWorkshops(WorkshopTypeEntity.getLstWorkshops());
		return workshopTypeDTO;
	}

	@Override
	public Boolean disableWorkshopType(Integer workshopTypeId) {
		WorkshopTypeDTO workshopTypeDTO = this.findByWorkshopTypeId(workshopTypeId);
		WorkshopType WorkshopTypeEntity = this.modelMapper.map(workshopTypeDTO, WorkshopType.class);
		if (WorkshopTypeEntity != null) {
			WorkshopTypeEntity.setState(false);
			return true;
		}
		return false;
	}

	

}
