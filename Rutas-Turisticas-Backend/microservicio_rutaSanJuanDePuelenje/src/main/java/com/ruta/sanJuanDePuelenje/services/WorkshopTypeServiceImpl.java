package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ruta.sanJuanDePuelenje.models.WorkshopType;
import com.ruta.sanJuanDePuelenje.repository.IWorkshopTypeRepository;

public class WorkshopTypeServiceImpl implements IWorkshopTypeService{

	@Autowired
	private IWorkshopTypeRepository iWorkshopTypeRepository;
	
	@Override
	public List<WorkshopType> findAllWorkshopTypes() {
		return (List<WorkshopType>) iWorkshopTypeRepository.findAll();
	}

	@Override
	public WorkshopType saveWorkshopType(WorkshopType workshopType) {
		return iWorkshopTypeRepository.save(workshopType);
	}

	@Override
	public WorkshopType updateWorkshopType(Integer workshopTypeId, WorkshopType workshopType) {
		WorkshopType workshopType1 =  iWorkshopTypeRepository.findById(workshopTypeId).orElse(null);
		workshopType1.setName(workshopType.getName());
		workshopType.setState(workshopType.getState());
		workshopType.setLstWorkshops(workshopType.getLstWorkshops());
		return workshopType1;
	}

	@Override
	public Boolean disableWorkshopType(Integer workshopTypeId) {
		WorkshopType workshopType =  iWorkshopTypeRepository.findById(workshopTypeId).orElse(null);
		if (workshopType != null) {
			workshopType.setState(false);
			return true;
		}
		return false;
	}

}
