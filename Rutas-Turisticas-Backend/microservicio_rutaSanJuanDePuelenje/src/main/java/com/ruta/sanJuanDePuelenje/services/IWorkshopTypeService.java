package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.WorkshopTypeDTO;

public interface IWorkshopTypeService {
	
	public List<WorkshopTypeDTO> findAllWorkshopTypes();
	
	public WorkshopTypeDTO findByWorkshopTypeId(Integer WorkshopTypeId);
	
	public WorkshopTypeDTO saveWorkshopType(WorkshopTypeDTO workshopType);
	
	public WorkshopTypeDTO updateWorkshopType(Integer workshopTypeId, WorkshopTypeDTO workshopType);
	
	public Boolean disableWorkshopType(Integer workshopTypeId);
}
