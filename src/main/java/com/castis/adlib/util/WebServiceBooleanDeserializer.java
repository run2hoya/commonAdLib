package com.castis.adlib.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class WebServiceBooleanDeserializer implements JsonDeserializer<Boolean> {

	@Override
	public Boolean deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		try {
			String value = json.getAsString();
			if(value.equalsIgnoreCase("true")){
				return true;
			} else if(value.equalsIgnoreCase("false")){
				return false;
			} else{
				return null;
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}
}
