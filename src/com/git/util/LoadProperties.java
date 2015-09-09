package com.git.util;

import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {
	
	public static String getProperty(String key){
		 InputStream ins=LoadProperties.class.getResourceAsStream("../../../../config/smtp.properties");  
	     Properties p = new Properties();  
	     try {  
	         p.load(ins);  
	     } catch (Exception e) {  
	         e.printStackTrace();  
	     }  
		 return p.getProperty(key);
	 }
}
