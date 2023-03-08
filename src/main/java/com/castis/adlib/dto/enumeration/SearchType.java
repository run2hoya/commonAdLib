package com.castis.adlib.dto.enumeration;

public enum SearchType {
	UNKNOWN("UNKNOWN"),
	DIRECTORY("@directory"),
	SUB_DIRECTORY("@subDirectory"),
	DAILY_DIRECTORY("@dailyDirectory")
	;
	
	private String value;
	
	private SearchType(String value){
		this.value = value;
	}
	
	public String toString(){
		return value;
	}
	
	public static SearchType valueof(String value){
		
		if(value == null)
			return SearchType.UNKNOWN;
		
		SearchType[] list = SearchType.values();
		for(SearchType type : list){
			if(type.toString().equalsIgnoreCase(value)){
				return type;
			}
		}
		return SearchType.UNKNOWN;
	}
}
