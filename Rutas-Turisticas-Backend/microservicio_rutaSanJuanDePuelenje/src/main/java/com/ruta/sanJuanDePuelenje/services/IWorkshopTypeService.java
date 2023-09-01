package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.WorkshopType;

public interface IWorkshopTypeService {
	
	public List<WorkshopType> findAllWorkshopTypes();
	
	public WorkshopType saveWorkshopType(WorkshopType workshopType);
	
	public WorkshopType updateWorkshopType(Integer workshopTypeId, WorkshopType workshopType);
	
	public Boolean disableWorkshopType(Integer workshopTypeId);
}
