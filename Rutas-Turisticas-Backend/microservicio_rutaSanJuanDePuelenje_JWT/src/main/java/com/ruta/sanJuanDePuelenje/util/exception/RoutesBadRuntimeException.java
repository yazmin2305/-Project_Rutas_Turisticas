package com.ruta.sanJuanDePuelenje.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RoutesBadRuntimeException extends CoreException{

	private static final long serialVersionUID = 1L;
	
	public RoutesBadRuntimeException(String key, String arg) {
	        super(key, arg);
	}

	public RoutesBadRuntimeException(String Message, int status, Throwable e) {
		super(Message, status, e);
	}
	
	public RoutesBadRuntimeException(String Message, int status) {
		super(Message, status);
	}

}
