package com.castis.adlib.define;


public class ResultCode {

	//일반 응답
	public final static int OK											= 200;
	public final static int NO_CONTENTS									= 204;
	public final static int PAIRTIAL_SUCCESS							= 230;
	public final static int INVALID_PARAMETER							= 400;
	public final static int	MANDATORY_PARAMETER							= 400;
	public final static int	NOT_FOUND									= 404;
	public final static int	NOT_FOUND_GROUP								= 404;
	public final static int	DUPLICATE_ID								= 406;
	public final static int	CONFLICT_ID									= 409;

	public final static int DB_GENERAL_ERROR							= 500;
	public final static int INTERNAL_SERVER_ERROR						= 500;
	public final static int NOT_ENOUGH_BALANCE							= 416;
	public final static int EXTERNAL_SYSTEM_ERROR						= 502;
	public final static int EXTERNAL_SYSTEM_RECEIVE_FAILURE				= 502;
	public final static int SERVICE_UNAVAILABLE_ERROR					= 503;

	public final static String OK_NAME										= "SUCCESS";
	public final static String INVALID_PARAMETER_NAME						= "INVALID_PARAMETER";
	public final static String MANDATORY_PARAMETER_NAME						= "MANDATORY_PARAMETER";
	public final static String INTERNAL_SERVER_ERROR_NAME					= "INTERNAL_SERVER_ERROR";
	public final static String CONFLICT_NAME					= "CONFLICT";
	public final static String DB_GENERAL_ERROR_NAME						= "DB_GENERAL_ERROR";
	public final static String POLICY_LICENSE_ERROR  						= "POLICY_LICENSE_ERROR";
	public final static String NOT_FOUND_NAME 								= "NOT_FOUND";
	public final static String NOT_FOUND_GROUP_NAME 						= "NOT_FOUND_GROUP";
	public final static String NOT_FOUND_MSO_COD 							= "NOT_FOUND_MSO_CODE";
	public final static String NOT_ENOUGH_COUPON_POLICY_BALANCE_NAME		= "NOT_ENOUGH_COUPON_POLICY_BALANCE";
	public final static String SERVICE_UNAVAILABLE_NAME 					= "SERVICE_UNAVAILABLE";
	public final static String EXTERNAL_SYSTEM_ERROR_NAME 					= "EXTERNAL_SYSTEM_ERROR";
	public final static String EXTERNAL_SYSTEM_RECEIVE_FAILURE_NAME 		= "EXTERNAL_SYSTEM_RECEIVE_FAILURE";
	public final static String DUPLICATE_ID_NAME 					   		= "DUPLICATE_ID";
	public final static String SERVICE_UNAVAILABLE_VERSION_NAME 		    = "SERVICE_UNAVAILABLE_VERSION";
	public final static String ALREADY_EXIST_NAME							= "ALREADY_EXIST";
	public final static String GENERAL_ERROR_NAME							= "GENERAL_ERROR";
	public final static String PARSING_ERROR_NAME							= "PARSING_ERROR";


}

