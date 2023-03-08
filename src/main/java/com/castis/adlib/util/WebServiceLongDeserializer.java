package com.castis.adlib.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class WebServiceLongDeserializer implements JsonDeserializer<Long>{

	@Override
	public Long deserialize(JsonElement json, Type typeOfT,	JsonDeserializationContext context) throws JsonParseException {
		Long value = null;
		try {
			value = json.getAsLong();
			return value;
		} catch (Exception e) {
		}
		return value;
	}
}
