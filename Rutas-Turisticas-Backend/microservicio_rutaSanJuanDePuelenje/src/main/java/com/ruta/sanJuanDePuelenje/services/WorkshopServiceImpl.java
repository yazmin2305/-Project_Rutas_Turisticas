package com.ruta.sanJuanDePuelenje.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruta.sanJuanDePuelenje.DTO.WorkshopDTO;
import com.ruta.sanJuanDePuelenje.models.Workshop;
import com.ruta.sanJuanDePuelenje.repository.IWorkshopRerpository;

public class WorkshopServiceImpl implements IWorkshopService{

	@Autowired
	private IWorkshopRerpository iWorkshopRerpository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<WorkshopDTO> findAllWorkshop() {
		List<Workshop> workshopEntity = iWorkshopRerpository.findAll();
		List<WorkshopDTO> workshopDTOs = new ArrayList<>();
		workshopDTOs = workshopEntity.stream().map(workshop -> modelMapper.map(workshop, WorkshopDTO.class)).collect(Collectors.toList());
		return workshopDTOs;
	}

	@Override
	public WorkshopDTO findByWorkshopId(Integer workshopId) {
		Workshop workshop = iWorkshopRerpository.findById(workshopId).orElse(null);
		WorkshopDTO workshopDTO = modelMapper.map(workshop, WorkshopDTO.class);
		return workshopDTO;
	}

	@Override
	public WorkshopDTO saveWorkshop(WorkshopDTO workshop) {
		Workshop workshopEntity  = this.modelMapper.map(workshop, Workshop.class);
		workshopEntity.setState(true);
		Workshop objWorkshop = this.iWorkshopRerpository.save(workshopEntity);
		WorkshopDTO workshopDTO = this.modelMapper.map(objWorkshop, WorkshopDTO.class);
		return workshopDTO;
	}

	@Override
	public WorkshopDTO updateWorkshop(Integer workshopId, WorkshopDTO workshop) {
		Workshop workshopEntity = this.modelMapper.map(workshop, Workshop.class);
		WorkshopDTO workshopDTO = this.findByWorkshopId(workshopId);
		Workshop workshopEntity1 = this.modelMapper.map(workshopDTO, Workshop.class);
		workshopEntity1.setName(workshopEntity.getName());
		workshopEntity1.setDescription(workshopEntity.getDescription());
		workshopEntity1.setDuration(workshopEntity.getDuration());
		workshopEntity1.setAvailability(workshopEntity.getAvailability());
		workshopEntity1.setMaxAmountPerson(workshopEntity.getMaxAmountPerson());
		workshopEntity1.setUnitPrice(workshopEntity.getUnitPrice());
		workshopEntity1.setTotalPrice(workshopEntity.getTotalPrice());
		workshopEntity1.setWorkshopType(workshopEntity.getWorkshopType());
		workshopEntity1.setState(workshopEntity.getState());
		workshopEntity1.setFinca(workshopEntity.getFinca());
		workshopEntity1.setLstReserve(workshopEntity.getLstReserve());
		return workshopDTO;
	}

	@Override
	public Boolean disableWorkshop(Integer workshopId) {
		WorkshopDTO workshopDTO = this.findByWorkshopId(workshopId);
		Workshop workshopEntity = this.modelMapper.map(workshopDTO, Workshop.class);
		if (workshopEntity != null) {
			workshopEntity.setState(false);
			return true;
		}
		return false;
	}


}
