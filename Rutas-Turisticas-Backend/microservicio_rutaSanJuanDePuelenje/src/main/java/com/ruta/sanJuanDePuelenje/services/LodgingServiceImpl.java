package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.models.Lodging;
import com.ruta.sanJuanDePuelenje.repository.ILodgingRepository;

public final class LodgingServiceImpl implements ILodgingService{

	private ILodgingRepository iLodgingRepository;
	
	@Override
	public List<Lodging> findAllLodging() {
		return (List<Lodging>) iLodgingRepository.findAll();
	}

	@Override
	public Lodging findByLodgingId(Integer lodgingId) {
		Lodging lodging = iLodgingRepository.findById(lodgingId).orElse(null);
		return lodging;
	}

	@Override
	public Lodging saveLodging(Lodging lodging) {
		return iLodgingRepository.save(lodging);
	}

	@Override
	public Lodging updateLodging(Integer lodgingId, Lodging lodging) {
		Lodging lodging1 = this.findByLodgingId(lodgingId);
		lodging1.setName(lodging.getName());
		lodging1.setDescription(lodging.getDescription());
		lodging1.setBedsAvailable(lodging.getBedsAvailable());
		lodging1.setNumberNights(lodging.getNumberNights());
		lodging1.setMaxAmountPerson(lodging.getMaxAmountPerson());
		lodging1.setUnitPrice(lodging.getUnitPrice());
		lodging1.setTotalPrice(lodging.getTotalPrice());
		lodging1.setState(lodging.getState());
		lodging1.setFinca(lodging.getFinca());
		lodging1.setLstReserve(lodging.getLstReserve());
		return lodging1;
	}

	@Override
	public void disableLodging(Integer lodgingId) {
		Lodging lodging = this.findByLodgingId(lodgingId);
		lodging.setState(false);
	}

}
