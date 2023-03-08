package com.castis.adlib.dto;

public class TransactionID {
	
	public static String TRANSACTION_ID_TYPE = "trID";
	public static String REQUEST_ID_TYPE = "reqID";
	public static String TRANSACTION_SEARCH_TYPE = "search";
	
	String	trType = TRANSACTION_ID_TYPE;
	String	key = "";

	public TransactionID(String trType, String key) {
		super();
		this.trType = trType;
		this.key = key;
	}
	
	public TransactionID(String key) {
		super();
		this.key = key;
	}

	
	public String getTrType() {
		return trType;
	}

	public String getKey() {
		return key;
	}

	@Override
	public String toString() {
		return "["+ trType + ":" + key + "] ";
	}
	
}
