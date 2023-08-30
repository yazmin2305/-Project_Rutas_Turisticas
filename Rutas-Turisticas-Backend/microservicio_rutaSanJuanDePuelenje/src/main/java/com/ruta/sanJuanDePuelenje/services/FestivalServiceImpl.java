package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ruta.sanJuanDePuelenje.models.Festival;
import com.ruta.sanJuanDePuelenje.repository.IFestivalRepository;

public class FestivalServiceImpl implements IFestivalService{
	@Autowired
	private IFestivalRepository iFestivalRepository;

	@Override
	public List<Festival> findAllFestival() {
		return (List<Festival>) iFestivalRepository.findAll();
	}

	@Override
	public Festival findByFestivalId(Integer festivalId) {
		Festival festival = iFestivalRepository.findById(festivalId).orElse(null);
		return festival;
	}

	@Override
	public Festival saveFestival(Festival festival) {
		return iFestivalRepository.save(festival);
	}

	@Override
	public Festival updateFestival(Integer festivalId, Festival festival) {
		Festival festival1 = this.findByFestivalId(festivalId);
		festival1.setName(festival.getName());
		festival1.setDescription(festival.getDescription());
		return null;
	}

	@Override
	public Festival disableFestival(Integer festivalId) {
		return null;
	}

}
