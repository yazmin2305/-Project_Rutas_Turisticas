package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ruta.sanJuanDePuelenje.models.Lunch;
import com.ruta.sanJuanDePuelenje.repository.ILunchRepository;

public class LunchServiceImpl implements ILunchService{

	@Autowired
	private ILunchRepository iLunchRepository;
	
	@Override
	public List<Lunch> findAllLodging() {
		return (List<Lunch>) iLunchRepository.findAll();
	}

	@Override
	public Lunch findByLunchId(Integer lunchId) {
		Lunch lunch = iLunchRepository.findById(lunchId).orElse(null); 
		return lunch;
	}

	@Override
	public Lunch saveLunch(Lunch lunch) {
		return iLunchRepository.save(lunch);
	}

	@Override
	public Lunch updateLunch(Integer lunchId, Lunch lunch) {
		Lunch lunch1 = this.findByLunchId(lunchId);
		lunch1.setMenu(lunch.getMenu());
		lunch1.setUnitPrice(lunch.getUnitPrice());
		lunch1.setTotalPrice(lunch.getTotalPrice());
		lunch1.setState(lunch.getState());
		lunch1.setLstReserve(lunch.getLstReserve());
		return lunch1;
	}

	@Override
	public void disableLunch(Integer lunchId) {
		Lunch lunch = this.findByLunchId(lunchId);
		lunch.setState(false);
	}

}
