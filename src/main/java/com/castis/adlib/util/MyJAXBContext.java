package com.castis.adlib.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class MyJAXBContext {

	public static JAXBContext initContext(Class<?> objClass) {
		try {
			return JAXBContext.newInstance(objClass);
		} catch (JAXBException e) {
			return null;
		}
	}
}
