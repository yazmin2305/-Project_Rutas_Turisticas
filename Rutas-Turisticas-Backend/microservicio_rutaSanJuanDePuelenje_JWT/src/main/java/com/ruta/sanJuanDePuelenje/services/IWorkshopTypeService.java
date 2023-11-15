package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.WorkshopTypeCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.WorkshopTypeQueryDTO;

public interface IWorkshopTypeService {
	
	public Response<List<WorkshopTypeQueryDTO>> findAllWorkshopTypes();
	
	public Response<WorkshopTypeQueryDTO> findByWorkshopTypeId(Integer WorkshopTypeId);
	
	public Response<WorkshopTypeCommandDTO> saveWorkshopType(WorkshopTypeCommandDTO workshopType);
	
	public Response<WorkshopTypeQueryDTO> updateWorkshopType(Integer workshopTypeId, WorkshopTypeCommandDTO workshopType);
	
	public Response<Boolean> disableWorkshopType(Integer workshopTypeId);
	
	public Response<Boolean> enableWorkshopType(Integer workshopTypeId);
	
	public Response<List<WorkshopTypeQueryDTO>> findAllWorkshopTypeBytState(boolean state);
}
