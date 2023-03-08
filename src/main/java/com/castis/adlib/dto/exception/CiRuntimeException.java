package com.castis.adlib.dto.exception;

public class CiRuntimeException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CiRuntimeException(String message) {
		super(message);
	}

	public CiRuntimeException() {
		super();
	}

	public CiRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CiRuntimeException(Throwable cause) {
		super(cause);
	}
}
