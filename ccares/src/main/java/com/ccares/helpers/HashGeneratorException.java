package com.ccares.helpers;

public class HashGeneratorException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HashGeneratorException() {
		super();
	}
	
	public HashGeneratorException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public HashGeneratorException(String message) {
		super(message);
	}

	public HashGeneratorException(Throwable throwable) {
		super(throwable);
	}
}
