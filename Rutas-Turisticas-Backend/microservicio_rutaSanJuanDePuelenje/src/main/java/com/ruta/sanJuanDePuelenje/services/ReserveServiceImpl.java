package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ruta.sanJuanDePuelenje.models.Reserve;
import com.ruta.sanJuanDePuelenje.repository.IReserveRepository;

public class ReserveServiceImpl implements IReserveService{

	@Autowired
	private IReserveRepository iReserveRepository;
	
	@Override
	public List<Reserve> findAllLodging() {
		return (List<Reserve>) iReserveRepository.findAll();
	}

	@Override
	public Reserve findByReserveId(Integer reserveId) {
		Reserve reserve = iReserveRepository.findById(reserveId).orElse(null);
		return reserve;
	}

	@Override
	public Reserve saveReserve(Reserve reserve) {
		return iReserveRepository.save(reserve);
	}

	@Override
	public Reserve updateReserve(Integer reserveId, Reserve reserve) {
		Reserve reserve1 = this.findByReserveId(reserveId);
		reserve1.setAmountPersons(reserve.getAmountPersons());
		reserve1.setTotalPrice(reserve.getTotalPrice());
		reserve1.setState(reserve.getState());
		reserve1.setUser(reserve.getUser());
		reserve1.setWorkshop(reserve.getWorkshop());
		reserve1.setTalking(reserve.getTalking());
		reserve1.setRecreation(reserve.getRecreation());
		reserve1.setLodging(reserve.getLodging());
		reserve1.setFestival(reserve.getFestival());
		reserve1.setLunch(reserve.getLunch());
		return reserve1;
	}

	@Override
	public Boolean disableReserve(Integer reserveId) {
		Reserve reserve = this.findByReserveId(reserveId);
		if(reserve != null){
			reserve.setState(false);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteReserve(Integer reserveId) {
		Reserve reserve = this.findByReserveId(reserveId);
		if(reserve != null){
			iReserveRepository.deleteById(reserveId);;
			return true;
		}
		return false;
	}
	
	



}
