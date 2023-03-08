package com.castis.adlib.dto.exception;

public class WrappingException extends CiUncheckedException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrappingException(String msg) {
		super(msg);
	}
	
	public WrappingException(Throwable t) {
		super(t);
	}
}
