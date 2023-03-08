package com.castis.adlib.util.validation;


import com.castis.adlib.define.ResultCode;
import com.castis.adlib.dto.exception.CiException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.castis.adlib.util.StringUtil;

import java.util.List;


public class Validation implements IResourceValidation{
	static final Log		log = LogFactory.getLog( Validation.class );
	

	public final static String	PRODUCT	= "PRODUCT";

	private Validation() {

	}
	
	@Override
	public  void isInteger(String parameter, Integer value) throws Exception {
		if(value ==null) {
			throw new CiException(ResultCode.INVALID_PARAMETER, ResultCode.INVALID_PARAMETER_NAME,
						String.format("Invalid Value : %s[%s]",parameter, value));
		}
	}
	@Override
	public  void isInteger(String parameter, String value) throws Exception {
		isNotNullStr(parameter, value);
		if(StringUtil.ableToConvertInteger(value) == false) {
			throw new CiException(ResultCode.INVALID_PARAMETER, ResultCode.INVALID_PARAMETER_NAME, 
						String.format("Invalid Value : %s[%s]",parameter, value));
		}
	}
	@Override
	public  void isLong(String parameter, String value) throws Exception {
		isNotNullStr(parameter, value);
		if(StringUtil.ableToConvertToLong(value) == false) {
			throw new CiException(ResultCode.INVALID_PARAMETER, ResultCode.INVALID_PARAMETER_NAME, 
						String.format("Invalid Value : %s[%s]",parameter, value));
		}
	}
	
	@Override
	public  void isNotNullStr(String parameter, String value) throws Exception {

		if(StringUtil.isNull(value)) {
			throw new CiException(ResultCode.MANDATORY_PARAMETER, ResultCode.MANDATORY_PARAMETER_NAME, 
						String.format("Invalid Value : %s[%s]",parameter, value));
		}
	}
	@Override
	public  void isNotNullDBIntId(String parameter, int value) throws Exception {

		if(value <= 0) {
			throw new CiException(ResultCode.MANDATORY_PARAMETER, ResultCode.MANDATORY_PARAMETER_NAME, 
						String.format("Invalid Value : %s[%s]",parameter, value));
		}
	}
	
	@Override
	public void isNotNullArray(String parameter, Object[] list) throws Exception{
		if(list == null || list.length == 0) {
			throw new CiException(ResultCode.MANDATORY_PARAMETER, ResultCode.MANDATORY_PARAMETER_NAME, 
						String.format("Invalid Value : %s[%s]",parameter, list));
		}
	}
	
	public void isNotNullListObject(String parameter, List<?> list) throws Exception{
		if(list == null || list.isEmpty()) {
			throw new CiException(ResultCode.MANDATORY_PARAMETER, ResultCode.MANDATORY_PARAMETER_NAME, 
						String.format("Invalid Value : %s[%s]",parameter, list));
		}
	}


}
