package com.ruta.sanJuanDePuelenje.util.exception;

import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

import lombok.*;

@Getter
@Setter
public class Exception extends RuntimeException{
	
	private static final long serialVersionUID = 6365652257268547172L;

	/** Mensaje informativo para el usuario */
    private final String Message;

    /** Codigo que define el estado de la transaccion */
    private final int status;
    
    /**
     * The exceptions resource bundle.
     */
    private static final ResourceBundle messages =
            ResourceBundle.getBundle("exceptions", LocaleContextHolder.getLocale());
    
    Exception(String key, String arg) {
        super(formatMessage(getMessage(key), arg));
        this.status = 0;
        this.Message = "";
    }
    
    /**
     * Método constructor
     */
    public Exception (String Message, int status, Throwable e) {
        super(e);
        this.Message = Message;
        this.status = status;
    }
    
    /**
     * Método constructor
     */
    public Exception (String Message, int status) {
        super(Message);
		this.Message = Message;
        this.status = status;
    }
    
    private static String formatMessage(String message, String arg) {
        return message.replace("{}", arg);
    }

    private static String getMessage(String key) {
        return messages.getString(key);
    }

}
