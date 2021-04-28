package com.anurag.covid.exception;

public class ServerDownException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServerDownException(String errorMessage) {
		super(errorMessage);
	}

}
