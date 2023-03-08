package com.castis.adlib.util;

import com.google.gson.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class JsonUtil {
	static final Log		log = LogFactory.getLog( JsonUtil.class );
	public static final String CHAR_SET_NAME_UTF_8 = "UTF-8";
	
	
	//java Object를 json으로 변환
	public static String objectToJsonForJSPEval(Object obj)
	{
		String jsonString = objectToJson(obj);
		if(jsonString != null && !jsonString.isEmpty())
			jsonString = jsonString.replaceAll("\"", "\\\\\"");
		return jsonString;
	}
	
	//java Object를 json으로 변환
	public static String objectToJson(Object obj)
	{
		String jsonString = null;
		GsonBuilder gb = new GsonBuilder();
		gb.disableHtmlEscaping();
		gb = gb.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Gson gson = gb.create();
		jsonString = gson.toJson(obj);
		
		return jsonString;
	}
	
		
	//json을 java Object로 변환
	public static Object jsonToObject(String jsonString, Class<?> outputClass) {
		Object resultObject = null;
		if(jsonString != null && !jsonString.isEmpty()) {				
			GsonBuilder gb = new GsonBuilder()

			.registerTypeAdapter(Date.class, new WebServiceDateDeserializer())
			.registerTypeAdapter(Integer.class, new WebServiceIntegerDeserializer())
			.registerTypeAdapter(Long.class, new WebServiceLongDeserializer())
			.registerTypeAdapter(Boolean.class, new WebServiceBooleanDeserializer())
//			.registerTypeAdapter(SettlementStatus.class, new WebServiceEnumDeserializer())
			;
			gb = gb.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Gson gson = gb.create();
			resultObject = gson.fromJson(jsonString, outputClass);
		}
		
		return resultObject;
	}
	
	//json을 java Object로 변환
	public static Object jsonToObjectFromKey(String jsonString, Class<?> outputClass, String key) {
		Object resultObject = null;
		if(jsonString != null && !jsonString.isEmpty()) {				
			GsonBuilder gb = new GsonBuilder()

			.registerTypeAdapter(Date.class, new WebServiceDateDeserializer())
			.registerTypeAdapter(Integer.class, new WebServiceIntegerDeserializer())
			.registerTypeAdapter(Long.class, new WebServiceLongDeserializer())
			.registerTypeAdapter(Boolean.class, new WebServiceBooleanDeserializer())
//			.registerTypeAdapter(SettlementStatus.class, new WebServiceEnumDeserializer())
			;
			gb = gb.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Gson gson = gb.create();
			
			JsonObject jsonObject = (JsonObject) new JsonParser().parse(jsonString);
			resultObject = gson.fromJson(jsonObject.get(key).toString(), outputClass);
		}
		
		return resultObject;
	}
	
	public static Object jsonToObject(InputStream iStream, Class<?> outputClass) {
		return jsonToObject(iStream, outputClass, CHAR_SET_NAME_UTF_8);
	}
	
	public static Object jsonToObject(InputStream iStream, Class<?> outputClass, String charsetName) {
		Object resultObject = null;
		if(iStream != null) {	
			InputStreamReader reader = null;
			try {
				reader = new InputStreamReader(iStream, charsetName);
			} catch (Exception e) {
				log.error(e);
				log.debug("", e);
				return null;
			}
			
			GsonBuilder gb = new GsonBuilder();
			gb = gb.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Gson gson = gb.create();
			resultObject = gson.fromJson(reader, outputClass);
		}
		return resultObject;
	}
	 
	public static Object jsonToObject(InputStream iStream, Type type) {
		return jsonToObject(iStream, type, CHAR_SET_NAME_UTF_8);
	}
	public static Object jsonToObject(InputStream iStream, Type type, String charsetName) {
		Object resultObject = null;
		if(iStream != null) {	
			InputStreamReader reader = null;
			try {
				reader = new InputStreamReader(iStream, charsetName);
			} catch (Exception e) {
				log.error(e);
				log.debug("", e);
				return null;
			}
			
			GsonBuilder gb = new GsonBuilder();
			gb = gb.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Gson gson = gb.create();
			resultObject = gson.fromJson(reader, type);
		}
		return resultObject;
	}
	
	
	public static void jsonArrayToList(InputStream iStream, Class<?> outputClass, List result) {
		jsonArrayToList(iStream, outputClass, result, CHAR_SET_NAME_UTF_8);
	}
	
	public static void jsonArrayToList(InputStream iStream, Class<?> outputClass, List result, String charsetName) {
		if(iStream != null) {	
			InputStreamReader reader = null;
			try {
				//reader = new InputStreamReader(iStream, "euc-kr");
				reader = new InputStreamReader(iStream, charsetName);
			} catch (Exception e) {
				log.error(e);
				log.debug("", e);
				e.printStackTrace();
			}
			
			JsonParser parser = new JsonParser();
			JsonArray jsonArray = parser.parse(reader).getAsJsonArray();
			GsonBuilder gb = new GsonBuilder();
			gb = gb.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Gson gson = gb.create();
			
			for(JsonElement je:jsonArray) {
				result.add(gson.fromJson(je, outputClass));
			}
		}
	}
	
}
