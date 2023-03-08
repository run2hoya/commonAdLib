package com.castis.adlib.dto.exception;

public class UnreadableFileException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnreadableFileException() {
		super();
	}

	public UnreadableFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnreadableFileException(String message) {
		super(message);
	}

	public UnreadableFileException(Throwable cause) {
		super(cause);
	}

}
