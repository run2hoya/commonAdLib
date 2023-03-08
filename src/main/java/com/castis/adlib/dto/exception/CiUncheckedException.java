package com.castis.adlib.dto.exception;

public class CiUncheckedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CiUncheckedException(String message) {
		super(message);
	}

	public CiUncheckedException() {
		super();
	}

	public CiUncheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public CiUncheckedException(Throwable cause) {
		super(cause);
	}
}
