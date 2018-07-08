package com.intrvw.hexagon.exceptions;

public class TechnicalException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TechnicalException(String message) {
	   super(message);
	}

	public TechnicalException(String string, Exception e) {
		super(string, e);
	}
}
