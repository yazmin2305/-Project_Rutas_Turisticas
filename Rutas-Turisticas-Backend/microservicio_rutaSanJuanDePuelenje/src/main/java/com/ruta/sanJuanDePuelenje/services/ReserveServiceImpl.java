package com.ruta.sanJuanDePuelenje.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruta.sanJuanDePuelenje.DTO.ReserveDTO;
import com.ruta.sanJuanDePuelenje.models.Reserve;
import com.ruta.sanJuanDePuelenje.repository.IReserveRepository;

@Service
public class ReserveServiceImpl implements IReserveService{

	@Autowired
	private IReserveRepository iReserveRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ReserveDTO> findAllReserve() {
		List<Reserve> reserveEntity = iReserveRepository.findAll();
		List<ReserveDTO> reserveDTOs = new ArrayList<>();
		reserveDTOs = reserveEntity.stream().map(reserve -> modelMapper.map(reserve, ReserveDTO.class)).collect(Collectors.toList());
		return reserveDTOs;
	}

	@Override
	public ReserveDTO findByReserveId(Integer reserveId) {
		Reserve reserve = iReserveRepository.findById(reserveId).orElse(null);
		ReserveDTO reserveDTO = modelMapper.map(reserve, ReserveDTO.class);
		return reserveDTO;
	}

	@Override
	public ReserveDTO saveReserve(ReserveDTO reserve) {
		Reserve reserveEntity  = this.modelMapper.map(reserve, Reserve.class);
		reserveEntity.setState(true);
		Reserve objReserve = this.iReserveRepository.save(reserveEntity);
		ReserveDTO reserveDTO = this.modelMapper.map(objReserve, ReserveDTO.class);
		return reserveDTO;
	}

	@Override
	public ReserveDTO updateReserve(Integer reserveId, ReserveDTO reserve) {
		Reserve reserveEntity = this.modelMapper.map(reserve, Reserve.class);
		ReserveDTO reserveDTO = this.findByReserveId(reserveId);
		Reserve reserveEntity1 = this.modelMapper.map(reserveDTO, Reserve.class);
		reserveEntity1.setAmountPersons(reserveEntity.getAmountPersons());
		reserveEntity1.setTotalPrice(reserveEntity.getTotalPrice());
		reserveEntity1.setState(reserveEntity.getState());
		reserveEntity1.setUser(reserveEntity.getUser());
		reserveEntity1.setWorkshop(reserveEntity.getWorkshop());
		reserveEntity1.setTalking(reserveEntity.getTalking());
		reserveEntity1.setRecreation(reserveEntity.getRecreation());
		reserveEntity1.setLodging(reserveEntity.getLodging());
		reserveEntity1.setFestival(reserveEntity.getFestival());
		reserveEntity1.setLunch(reserveEntity.getLunch());
		return reserveDTO;
	}

	@Override
	public Boolean disableReserve(Integer reserveId) {
		ReserveDTO reserveDTO = this.findByReserveId(reserveId);
		Reserve reserveEntity = this.modelMapper.map(reserveDTO, Reserve.class);
		if(reserveEntity != null){
			reserveEntity.setState(false);
			this.iReserveRepository.save(reserveEntity);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteReserve(Integer reserveId) {
		ReserveDTO reserveDTO = this.findByReserveId(reserveId);
		Reserve reserveEntity = this.modelMapper.map(reserveDTO, Reserve.class);
		if(reserveEntity != null){
			iReserveRepository.deleteById(reserveId);
			return true;
		}
		return false;
	}

	@Override
	public List<ReserveDTO> findReservesByUser(Integer reserveId) {
		List<Reserve> reserveEntity = iReserveRepository.reservasUsuario(reserveId);
		List<ReserveDTO> reserveDTOs = new ArrayList<>();
		reserveDTOs = reserveEntity.stream().map(reserve -> modelMapper.map(reserve, ReserveDTO.class)).collect(Collectors.toList());
		return reserveDTOs;
	}

}
