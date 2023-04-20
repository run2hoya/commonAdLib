package com.castis.adlib.util;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Slf4j
public class MyJAXBContext {

	public static JAXBContext initContext(Class<?> objClass) {
		try {
			return JAXBContext.newInstance(objClass);
		} catch (JAXBException e) {
			log.error("", e);
			return null;
		}
	}
}
