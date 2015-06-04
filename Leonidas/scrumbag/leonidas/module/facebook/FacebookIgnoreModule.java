package scrumbag.leonidas.module.facebook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import scrumbag.Controller;
import scrumbag.domain.Kornuit;
import scrumbag.leonidas.module.Module;

public class FacebookIgnoreModule extends Module {
	private static final String FIELD = "/me?fields=family";
	
	public FacebookIgnoreModule () {
	}

	@Override
	public void calculate(Controller controller, String accessToken) {
		JSONObject root = getJsonObject(accessToken, FIELD);
		
		
		// ignore family
		JSONObject dataContainer = root.getJSONObject("family");
		JSONArray familyArray = dataContainer.getJSONArray("data");
		
		String id;
		JSONObject familyMember;
		Kornuit kornuit;
		for (int i = 0; i < familyArray.length(); i++) {
			try {
				familyMember = familyArray.getJSONObject(i);
				id = familyMember.getString("id");
				
				// Als de kornuit nog niet bestaat, maak 'm dan aan
				if (!controller.hasKornuit(id)) {
					System.out.println("NEW KORNUIT ->" + id);
					kornuit = new Kornuit();
					kornuit.setId(id);
					kornuit.setName(familyMember.getString("name"));
				} else {
					kornuit = controller.getKornuit(id);
				}
				kornuit.setIgnore(true);
				controller.addKornuit(kornuit);
			} catch (JSONException e) {
				// JSON informatie die we niet nodig hebben
			}
		}

		// ignore self
		String selfId = root.getString("id");
		if (controller.hasKornuit(selfId)) {
			controller.getKornuit(selfId).setIgnore(true);
		}
		
		// TODO ignore user specified
		
		sortKornuiten(controller.getKornuiten());
	}
}
