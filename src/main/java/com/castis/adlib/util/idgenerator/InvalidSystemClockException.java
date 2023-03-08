package com.castis.adlib.util.idgenerator;

public class InvalidSystemClockException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7710595412632547523L;

	public InvalidSystemClockException(String message){
        super(message);
    }
}
