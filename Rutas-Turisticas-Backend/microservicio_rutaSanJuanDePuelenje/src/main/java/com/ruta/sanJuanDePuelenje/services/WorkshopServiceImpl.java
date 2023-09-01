package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ruta.sanJuanDePuelenje.models.Workshop;
import com.ruta.sanJuanDePuelenje.repository.IWorkshopRerpository;

public class WorkshopServiceImpl implements IWorkshopService{

	@Autowired
	private IWorkshopRerpository iWorkshopRerpository;

	@Override
	public List<Workshop> findAllWorkshop() {
		return (List<Workshop>) iWorkshopRerpository.findAll();
	}

	@Override
	public Workshop findByWorkshopId(Integer workshopId) {
		Workshop workshop = iWorkshopRerpository.findById(workshopId).orElse(null);
		return workshop;
	}

	@Override
	public Workshop saveWorkshop(Workshop workshop) {
		return iWorkshopRerpository.save(workshop);
	}

	@Override
	public Workshop updateWorkshop(Integer workshopId, Workshop workshop) {
		Workshop workshop1 = this.findByWorkshopId(workshopId);
		workshop1.setName(workshop.getName());
		workshop1.setDescription(workshop.getDescription());
		workshop1.setDuration(workshop.getDuration());
		workshop1.setAvailability(workshop.getAvailability());
		workshop1.setMaxAmountPerson(workshop.getMaxAmountPerson());
		workshop1.setUnitPrice(workshop.getUnitPrice());
		workshop1.setTotalPrice(workshop.getTotalPrice());
		workshop1.setWorkshopType(workshop.getWorkshopType());
		workshop1.setState(workshop.getState());
		workshop1.setFinca(workshop.getFinca());
		workshop1.setLstReserve(workshop.getLstReserve());
		return null;
	}

	@Override
	public Boolean disableWorkshop(Integer workshopId) {
		Workshop workshop = this.findByWorkshopId(workshopId);
		if (workshop != null) {
			workshop.setState(false);
			return true;
		}
		return false;
	}


}
