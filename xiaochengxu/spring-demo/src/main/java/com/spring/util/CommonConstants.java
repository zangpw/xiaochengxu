package com.spring.util;


public class CommonConstants {
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("qdmall.properties");
	
	public static final String IMG_SERVER = propertiesLoader.getProperty("img.server");
	public static final String FILE_BASEPATH = propertiesLoader.getProperty("file.basepath");
}
