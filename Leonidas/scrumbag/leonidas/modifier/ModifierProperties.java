<<<<<<< HEAD
package scrumbag.leonidas.modifier;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ModifierProperties {
	private final String FILE_NAME = "scrumbag/leonidas/modifier/modifiers.properties";
	private static Properties properties;
	
	public ModifierProperties() {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
		properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			System.out.println("properties file not found at: " + FILE_NAME);
			e.printStackTrace();
		}
	}
	
	public float getModifier(String name) throws NoModifierFoundException {
		if (properties.getProperty(name) != null) {
			return Float.parseFloat(properties.getProperty(name));
		} else {
			throw new NoModifierFoundException("Modifier: \'" + name + "\' was not found in: \'" + FILE_NAME + "\'.");
		}
	}
}
=======
package scrumbag.leonidas.modifier;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ModifierProperties {
	private final String FILE_NAME = "scrumbag/leonidas/modifier/modifiers.properties";
	private static Properties properties;
	
	public ModifierProperties() {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
		properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			System.out.println("properties file not found at: " + FILE_NAME);
			e.printStackTrace();
		}
	}
	
	public float getModifier(String name) throws NoModifierFoundException {
		if (properties.getProperty(name) != null) {
			return Float.parseFloat(properties.getProperty(name));
		} else {
			throw new NoModifierFoundException("Modifier: \'" + name + "\' was not found in: \'" + FILE_NAME + "\'.");
		}
	}
}
>>>>>>> 5c7e126b334cd885a5154cf42e7a4662abf935b2
