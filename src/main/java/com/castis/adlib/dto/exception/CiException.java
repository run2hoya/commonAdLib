package com.castis.adlib.dto.exception;

public class CiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1427995761624433234L;
	private int errorCode;
	private String errorName;

	
	public CiException(String message) {
		super(message);
	}
	
	public CiException(int errorCode, String errorName, String message) {
		super(message);
		this.errorCode = errorCode;
		this.errorName = errorName;
	}
	
	public CiException(String errorName, String format, Object... args )
	{
		super(String.format(format, args));
		this.errorName = errorName;		
	}
	
	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public String getErrorString(){
		return getMessage();
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
