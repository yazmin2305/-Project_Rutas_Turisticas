package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Workshop;


public interface IWorkshopService {
	
	public List<Workshop> findAllWorkshop();
	
	public Workshop findByWorkshopId(Integer workshopId);
	
	public Workshop saveWorkshop(Workshop workshop);
	
	public Workshop updateWorkshop(Integer workshopId, Workshop workshop);
	
	public void disableWorkshop(Integer workshopId);
}
