package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.WorkshopTypeDTO;

public interface IWorkshopTypeService {
	
	public Response<List<WorkshopTypeDTO>> findAllWorkshopTypes();
	
	public Response<WorkshopTypeDTO> findByWorkshopTypeId(Integer WorkshopTypeId);
	
	public Response<WorkshopTypeDTO> saveWorkshopType(WorkshopTypeDTO workshopType);
	
	public Response<WorkshopTypeDTO> updateWorkshopType(Integer workshopTypeId, WorkshopTypeDTO workshopType);
	
	public Response<Boolean> disableWorkshopType(Integer workshopTypeId);
	
	public Response<List<WorkshopTypeDTO>> findAllWorkshopTypeBytState(boolean state);
}
