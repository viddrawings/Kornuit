package scrumbag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import scrumbag.domain.Kornuit;
import scrumbag.leonidas.module.Module;
import scrumbag.leonidas.module.facebook.FacebookFeedLikeModule;
import scrumbag.leonidas.module.facebook.FacebookIgnoreModule;
import scrumbag.leonidas.module.facebook.FacebookMessageInModule;
import scrumbag.leonidas.module.generic.TimeModule;

public class Controller {
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public void setKornuiten(List<Kornuit> kornuiten) {
		this.kornuiten = kornuiten;
	}

	private List<Module> modules;
	private List<Kornuit> kornuiten;

	public Controller() {
		modules = new ArrayList<Module>();

		addModule(new FacebookFeedLikeModule());
		addModule(new FacebookIgnoreModule());
		addModule(new FacebookMessageInModule());
		addModule(new TimeModule());

		kornuiten = new ArrayList<Kornuit>();
	}

	public void updateScores(String accessToken) {
		System.out.println("Updating kornuit scores");
		System.out.println("AANTAL KORNUITEN = " + kornuiten.size());
		for (Module module : modules) {
			module.calculate(this, accessToken);
		}

		for (Kornuit kornuit : kornuiten) {
			if (!kornuit.isIgnored()) {
				float totalScore = 0f;
				for (int i = 1; i <= kornuit.getScores().length - 1; i++) {
					totalScore += kornuit.getScore(i);
				}
				if (kornuit.daysSinceAppointment() == -1) {
					kornuit.setScore(totalScore);
				} else {
					kornuit.setScore(kornuit.getScore(0) * totalScore);
//					System.out.println("KORNUIT -> " + kornuit.getId() + " HEEFT: "
//							+ kornuit.getMessagesReceived()
//							+ " BERICHTEN ONTVANGEN.");
//					System.out.println("KORNUIT -> " + kornuit.getId() + " HEEFT: "
//							+ kornuit.getAmmountOfFeedLikes()
//							+ " LIKES OP DE FEED ONTVANGEN.");
				}
			} else {
				System.out.println("Ignoring " + kornuit.getName()
						+ " because of ignore flag");
			}
		}

		System.out.println("Sorting kornuiten");
		// Sort all kornuiten on their scores (ascending)
		Collections.sort(kornuiten, new Comparator<Kornuit>() {
			public int compare(Kornuit k1, Kornuit k2) {
				return Float.compare(k1.getScore(), k2.getScore());
			}
		});
	}

	public Kornuit makeSuggestion(String accessToken) {
		updateScores(accessToken);
		// return last kornuit (has highest score)
		Kornuit tempKornuit = kornuiten.get(kornuiten.size() - 1);
		tempKornuit.setLastAppointment(new Date());
		if(tempKornuit.isWasLast()){
			tempKornuit.setWasLast(false);
			tempKornuit.setScore(tempKornuit.getScore()/2);
			return this.makeSuggestion(accessToken);
		}
		return tempKornuit;
	}

	public List<Kornuit> giveTop(int number) {
		int size = kornuiten.size() - 1;
		List<Kornuit> topKornuiten = new ArrayList<Kornuit>();
		if (size - number < 0) {
			number = size;
		}
		for (int i = size; i > size - number; i--) {
			topKornuiten.add(kornuiten.get(i));
		}
		return topKornuiten;
	}

	public List<Kornuit> getKornuiten() {
		return kornuiten;
	}

	public void addModule(Module module) {
		if (!modules.contains(module)) {
			modules.add(module);
		}
	}

	public boolean hasKornuit(String id) {
		for (Kornuit kornuit : kornuiten) {
			if (kornuit.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public void addKornuit(Kornuit kornuit) {
		if (!hasKornuit(kornuit.getId())) {
			kornuiten.add(kornuit);
		}
	}

	public Kornuit getKornuit(String id) {
		for (Kornuit kornuit : kornuiten) {
			if (kornuit.getId().equals(id)) {
				return kornuit;
			}
		}
		return null;
	}
}