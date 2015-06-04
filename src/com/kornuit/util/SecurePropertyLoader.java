package com.kornuit.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SecurePropertyLoader {
	
	public SecurePropertyLoader(){
		
	}
	
	public static Properties getSecureProperties(String location) throws FileNotFoundException, IOException{
		Properties p = new Properties();
		System.err.println(location);
		 p.load(new FileInputStream(location));
		 return p;
	}

}
