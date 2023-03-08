package com.castis.adlib.dto.exception;

public class CanNotFoundExIdWrappingException extends WrappingException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CanNotFoundExIdWrappingException(String msg) {
		super(msg);
	}
	
	public CanNotFoundExIdWrappingException(Throwable t) {
		super(t);
	}
}
