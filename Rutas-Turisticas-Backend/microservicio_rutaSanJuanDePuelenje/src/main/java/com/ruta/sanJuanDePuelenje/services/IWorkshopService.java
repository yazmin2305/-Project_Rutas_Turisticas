package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.WorkshopDTO;


public interface IWorkshopService {
	
	public Response<List<WorkshopDTO>> findAllWorkshop();
	
	public Response<WorkshopDTO> findByWorkshopId(Integer workshopId);
	
	public Response<WorkshopDTO> saveWorkshop(WorkshopDTO workshop);
	
	public Response<WorkshopDTO> updateWorkshop(Integer workshopId, WorkshopDTO workshop);
	
	public Response<Boolean> disableWorkshop(Integer workshopId);
	
	public Response<List<WorkshopDTO>> findAllWorkshopBytState(boolean state);
}
