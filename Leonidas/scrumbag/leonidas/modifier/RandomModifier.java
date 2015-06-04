package scrumbag.leonidas.modifier;

import java.util.Random;

public abstract class RandomModifier {
	private static Random random = new Random();
	
	public static float getRandom() {
		return (random.nextFloat() - .5f) * .1f;
	}
}