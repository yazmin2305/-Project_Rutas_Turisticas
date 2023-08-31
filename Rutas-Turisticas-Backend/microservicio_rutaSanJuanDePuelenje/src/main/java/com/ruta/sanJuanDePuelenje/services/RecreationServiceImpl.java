package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ruta.sanJuanDePuelenje.models.Recreation;
import com.ruta.sanJuanDePuelenje.repository.IRecreationRepository;

public class RecreationServiceImpl implements IRecreationService{

	@Autowired
	private IRecreationRepository iRecreationRepository;
	
	@Override
	public List<Recreation> findAllLodging() {
		return (List<Recreation>) iRecreationRepository.findAll();
	}

	@Override
	public Recreation findByRecreationId(Integer recreationId) {
		Recreation recreation = iRecreationRepository.findById(recreationId).orElse(null);
		return recreation;
	}

	@Override
	public Recreation saveRecreation(Recreation recreation) {
		return iRecreationRepository.save(recreation);
	}

	@Override
	public Recreation updateRecreation(Integer recreationId, Recreation recreation) {
		Recreation recreation1 = this.findByRecreationId(recreationId);
		recreation1.setName(recreation.getName());
		recreation1.setDescription(recreation.getDescription());
		recreation1.setDuration(recreation.getDuration());
		recreation1.setAvailability(recreation.getAvailability());
		recreation1.setMaxAmountPerson(recreation.getMaxAmountPerson());
		recreation1.setUnitPrice(recreation.getUnitPrice());
		recreation1.setTotalPrice(recreation.getTotalPrice());
		recreation1.setState(recreation.getState());
		recreation1.setFinca(recreation.getFinca());
		recreation1.setLstReserve(recreation.getLstReserve());
		return null;
	}

	@Override
	public void disableRecreation(Integer recreationId) {
		Recreation recreation = this.findByRecreationId(recreationId);
		recreation.setState(false);
	}

}
