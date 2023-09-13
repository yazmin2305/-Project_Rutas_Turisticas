package com.ruta.sanJuanDePuelenje.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruta.sanJuanDePuelenje.DTO.FincaDTO;
import com.ruta.sanJuanDePuelenje.models.Finca;
import com.ruta.sanJuanDePuelenje.repository.IFincaRepository;

@Service
public class FincaServiceImpl implements IFincaService{
	
	@Autowired
	private IFincaRepository iFincaRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<FincaDTO> findAllFincas() {
		List<Finca> fincaEntity = iFincaRepository.findAll();
		List<FincaDTO> fincaDTO = new ArrayList<>();
		fincaDTO = fincaEntity.stream().map(finca -> modelMapper.map(finca, FincaDTO.class)).collect(Collectors.toList());
		return fincaDTO;
	}

	@Override
	public FincaDTO findByFincaId(Integer fincaId) {
		Finca finca = iFincaRepository.findById(fincaId).orElse(null);
		FincaDTO fincaDTO = modelMapper.map(finca, FincaDTO.class);
		return fincaDTO;
	}

	@Override
	public FincaDTO saveFinca(FincaDTO finca) {
		Finca fincaEntity  = this.modelMapper.map(finca, Finca.class);
		fincaEntity.setState(true);
		Finca objFinca = this.iFincaRepository.save(fincaEntity);
		FincaDTO fincaDTO = this.modelMapper.map(objFinca, FincaDTO.class);
		return fincaDTO;
	}

	@Override
	public FincaDTO updateFinca(Integer fincaId, FincaDTO finca) {
		Finca fincaEntity = this.modelMapper.map(finca, Finca.class);
		FincaDTO fincaDTO = this.findByFincaId(fincaId);
		Finca fincaEntity1 = this.modelMapper.map(fincaDTO, Finca.class);
		fincaEntity1.setName(fincaEntity.getName());
		fincaEntity1.setDescription(fincaEntity.getDescription());
		fincaEntity1.setLocation(fincaEntity.getLocation());
		fincaEntity1.setState(fincaEntity.getState());
		fincaEntity1.setLstTalking(fincaEntity.getLstTalking());
		fincaEntity1.setLstWorkshop(fincaEntity.getLstWorkshop());
		fincaEntity1.setLstRecreation(fincaEntity.getLstRecreation());
		fincaEntity1.setLstLodging(fincaEntity.getLstLodging());
		fincaEntity1.setLstFestival(fincaEntity.getLstFestival());
		this.iFincaRepository.save(fincaEntity1);
		fincaDTO = this.modelMapper.map(fincaEntity1, FincaDTO.class);
		return fincaDTO;
	}

	@Override
	public Boolean disableFinca(Integer fincaId) {
		FincaDTO fincaDTO = this.findByFincaId(fincaId);
		Finca fincaEntity = this.modelMapper.map(fincaDTO, Finca.class);
		if(fincaEntity != null) {
			fincaEntity.setState(false);
			this.iFincaRepository.save(fincaEntity);
			return true;
		}
		return false;
	}

	@Override
	public List<FincaDTO> findAllFincaBytState(boolean state) {
		List<Finca> fincaEntity = this.iFincaRepository.LstFincaByState(state);
		List<FincaDTO> fincaDTO = fincaEntity.stream().map(finca -> modelMapper.map(finca, FincaDTO.class)).collect(Collectors.toList());
		return fincaDTO;
	}
	
	
}
