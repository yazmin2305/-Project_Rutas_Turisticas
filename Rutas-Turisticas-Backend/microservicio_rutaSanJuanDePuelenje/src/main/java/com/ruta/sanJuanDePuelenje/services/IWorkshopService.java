package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.WorkshopDTO;


public interface IWorkshopService {
	
	public List<WorkshopDTO> findAllWorkshop();
	
	public WorkshopDTO findByWorkshopId(Integer workshopId);
	
	public WorkshopDTO saveWorkshop(WorkshopDTO workshop);
	
	public WorkshopDTO updateWorkshop(Integer workshopId, WorkshopDTO workshop);
	
	public Boolean disableWorkshop(Integer workshopId);
}
