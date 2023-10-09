package com.ruta.sanJuanDePuelenje.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RoutesBadRuntimeException extends Exception{

	public RoutesBadRuntimeException(String Message, int status, Throwable e) {
		super(Message, status, e);
	}
	
	public RoutesBadRuntimeException(String Message, int status) {
		super(Message, status);
	}

}
