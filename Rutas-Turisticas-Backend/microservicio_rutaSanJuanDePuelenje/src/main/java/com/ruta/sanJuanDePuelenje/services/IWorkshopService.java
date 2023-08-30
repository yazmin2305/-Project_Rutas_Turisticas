package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Workshop;


public interface IWorkshopService {
	
	public List<Workshop> findAllTalking();
	
	public Workshop findByTalkingId(Integer workshopId);
	
	public Workshop saveTalking(Workshop workshop);
	
	public Workshop updateTalking(Integer workshopId, Workshop workshop);
	
	public Workshop disableTalking(Integer workshopId);
}
