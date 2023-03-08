package com.castis.adlib.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class WebServiceIntegerDeserializer implements JsonDeserializer<Integer> {

	@Override
	public Integer deserialize(JsonElement json, Type typeOfT,	JsonDeserializationContext context) throws JsonParseException {
		Integer value = null;
		try {
			value = json.getAsInt();
			return value;
		} catch (Exception e) {
		}
		return value;
	}

}
