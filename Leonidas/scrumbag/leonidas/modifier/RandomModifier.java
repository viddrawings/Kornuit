<<<<<<< HEAD
package scrumbag.leonidas.modifier;

import java.util.Random;

public abstract class RandomModifier {
	private static Random random = new Random();
	
	public static float getRandom() {
		return (random.nextFloat() - .5f) * .1f;
	}
=======
package scrumbag.leonidas.modifier;

import java.util.Random;

public abstract class RandomModifier {
	private static Random random = new Random();
	
	public static float getRandom() {
		return (random.nextFloat() - .5f) * .1f;
	}
>>>>>>> 5c7e126b334cd885a5154cf42e7a4662abf935b2
}