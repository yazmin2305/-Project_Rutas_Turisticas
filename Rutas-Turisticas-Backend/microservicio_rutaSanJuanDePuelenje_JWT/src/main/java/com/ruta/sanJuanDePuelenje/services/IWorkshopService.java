package com.ruta.sanJuanDePuelenje.services;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Response;
import com.ruta.sanJuanDePuelenje.DTO.Command.WorkshopCommandDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.WorkshopQueryDTO;


public interface IWorkshopService {
	
	public Response<List<WorkshopQueryDTO>> findAllWorkshop();
	
	public Response<WorkshopQueryDTO> findByWorkshopId(Integer workshopId);
	
	public Response<WorkshopCommandDTO> saveWorkshop(WorkshopCommandDTO workshop);
	
	public Response<WorkshopQueryDTO> updateWorkshop(Integer workshopId, WorkshopCommandDTO workshop);
	
	public Response<Boolean> disableWorkshop(Integer workshopId);
	
	public Response<List<WorkshopQueryDTO>> findAllWorkshopBytState(boolean state);
}
