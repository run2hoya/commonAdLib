package com.castis.adlib.util.idgenerator;

public class GetHardwareIdFailedException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1842973664245964335L;
	GetHardwareIdFailedException(String reason){
        super(reason);
    }
    GetHardwareIdFailedException(Exception e){
        super(e);
    }
}
