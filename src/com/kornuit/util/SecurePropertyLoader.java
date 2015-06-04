<<<<<<< HEAD
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
=======
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
>>>>>>> 5c7e126b334cd885a5154cf42e7a4662abf935b2
