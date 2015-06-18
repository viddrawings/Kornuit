package scrumbag.leonidas.modifier;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ModifierProperties {
	private final String FILE_NAME = "scrumbag/leonidas/modifier/modifiers.properties";
	private static Properties properties;
	
	/**
	 * Retrieves all to modifier properties information from a file and places it in a static attribute.
	 */
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
	
	/**
	 * Retrieves all to modifier properties information from a given file and places it in a static attribute.
	 * @param name String file location
	 * @return properties name
	 * @throws NoModifierFoundException throws NoModifierFoundException
	 */
	public float getModifier(String name) throws NoModifierFoundException {
		if (properties.getProperty(name) != null) {
			return Float.parseFloat(properties.getProperty(name));
		} else {
			throw new NoModifierFoundException("Modifier: \'" + name + "\' was not found in: \'" + FILE_NAME + "\'.");
		}
	}
}
