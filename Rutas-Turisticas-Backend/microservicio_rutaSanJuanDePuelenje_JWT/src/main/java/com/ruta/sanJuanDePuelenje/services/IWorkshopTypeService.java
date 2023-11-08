package com.ruta.sanJuanDePuelenje.services;

import org.springframework.data.domain.Pageable;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.WorkshopTypeDTO;
import com.ruta.sanJuanDePuelenje.util.GenericPageableResponse;

public interface IWorkshopTypeService {
	
	public GenericPageableResponse findAllWorkshopTypes(Pageable pageable);
	
	public Response<WorkshopTypeDTO> findByWorkshopTypeId(Integer WorkshopTypeId);
	
	public Response<WorkshopTypeDTO> saveWorkshopType(WorkshopTypeDTO workshopType);
	
	public Response<WorkshopTypeDTO> updateWorkshopType(Integer workshopTypeId, WorkshopTypeDTO workshopType);
	
	public Response<Boolean> disableWorkshopType(Integer workshopTypeId);
	
	public Response<Boolean> enableWorkshopType(Integer workshopTypeId);
	
	public GenericPageableResponse findAllWorkshopTypeBytState(boolean state, Pageable pageable);
}
