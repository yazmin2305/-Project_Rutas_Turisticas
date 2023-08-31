package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ruta.sanJuanDePuelenje.models.Finca;
import com.ruta.sanJuanDePuelenje.repository.IFincaRepository;

public class FincaServiceImpl implements IFincaService{
	
	@Autowired
	private IFincaRepository iFincaRepository;

	@Override
	public List<Finca> findAllFincas() {
		return (List<Finca>) iFincaRepository.findAll();
	}

	@Override
	public Finca findByFincaId(Integer fincaId) {
		Finca finca = iFincaRepository.findById(fincaId).orElse(null);
		return finca;
	}

	@Override
	public Finca saveFinca(Finca finca) {
		return iFincaRepository.save(finca);
	}

	@Override
	public Finca updateFinca(Integer fincaId, Finca finca) {
		Finca finca1 = this.findByFincaId(fincaId);
		finca1.setName(finca.getName());
		finca1.setDescription(finca.getDescription());
		finca1.setLocation(finca.getLocation());
		finca1.setState(finca.getState());
		finca1.setLstTalking(finca.getLstTalking());
		finca1.setLstWorkshop(finca.getLstWorkshop());
		finca1.setLstRecreation(finca.getLstRecreation());
		finca1.setLstLodging(finca.getLstLodging());
		finca1.setLstFestival(finca.getLstFestival());
		return finca1;
	}

	@Override
	public Boolean disableFinca(Integer fincaId) {
		Finca finca = this.findByFincaId(fincaId);
		if(finca != null) {
			finca.setState(false);
			return true;
		}
		return false;
	}
	
	
}
