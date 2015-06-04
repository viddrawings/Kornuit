<<<<<<< HEAD
package scrumbag.leonidas.module.generic;

import java.util.List;

import scrumbag.Controller;
import scrumbag.domain.Kornuit;
import scrumbag.leonidas.module.Module;

public class TimeModule extends Module {

	public TimeModule() {
		super();
		SCORE_INDEX = 0;
	}

	@Override
	public void calculate(Controller controller, String accessToken) {
		float totalScore = 0f;
		List<Kornuit> kornuiten = controller.getKornuiten();
		for (Kornuit kornuit : kornuiten) {
			for (int i = 1; i <= kornuit.getScores().length - 1; i++) {
				totalScore = kornuit.getScore(i);
			}
			kornuit.setScore(SCORE_INDEX, 1 + totalScore * kornuit.daysSinceAppointment() / 10000);
		}
	}
}
=======
package scrumbag.leonidas.module.generic;

import java.util.List;

import scrumbag.Controller;
import scrumbag.domain.Kornuit;
import scrumbag.leonidas.module.Module;

public class TimeModule extends Module {

	public TimeModule() {
		super();
		SCORE_INDEX = 0;
	}

	@Override
	public void calculate(Controller controller, String accessToken) {
		float totalScore = 0f;
		List<Kornuit> kornuiten = controller.getKornuiten();
		for (Kornuit kornuit : kornuiten) {
			for (int i = 1; i <= kornuit.getScores().length - 1; i++) {
				totalScore = kornuit.getScore(i);
			}
			kornuit.setScore(SCORE_INDEX, 1 + totalScore * kornuit.daysSinceAppointment() / 10000);
		}
	}
}
>>>>>>> 5c7e126b334cd885a5154cf42e7a4662abf935b2
