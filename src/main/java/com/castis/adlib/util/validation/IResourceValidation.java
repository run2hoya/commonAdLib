package com.castis.adlib.util.validation;




public interface IResourceValidation {
	public  void isNotNullStr(String parameter, String value) throws Exception;
	public  void isNotNullDBIntId(String parameter, int value) throws Exception;
	public 	void isNotNullArray(String parameter, Object[] list) throws Exception;
	public void isInteger(String parameter, String value) throws Exception;
	void isInteger(String parameter, Integer value) throws Exception;
	public  void isLong(String parameter, String value) throws Exception;
}