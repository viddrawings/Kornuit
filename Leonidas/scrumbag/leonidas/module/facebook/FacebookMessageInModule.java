package scrumbag.leonidas.module.facebook;

import org.json.JSONArray;
import org.json.JSONObject;

import scrumbag.Controller;
import scrumbag.domain.Kornuit;
import scrumbag.leonidas.module.Module;

public class FacebookMessageInModule extends Module {
	private static final String FIELD = "/me?fields=inbox{from}";

	@Override
	public void calculate(Controller controller, String accessToken) {

		JSONObject root = getJsonObject(accessToken, FIELD);
		JSONObject data_container = (root.getJSONObject("inbox"));
		JSONArray sessions = data_container.getJSONArray("data");

		for (int i = 0; i < sessions.length(); i++) {
			JSONObject current_object = sessions.getJSONObject(i);
			String conversation_id = current_object.getString("id");

			JSONObject related_conversation = getJsonObject(accessToken, "/"
					+ conversation_id + "?");
			System.out.println(related_conversation);
			JSONObject data_container_conversation = related_conversation
					.getJSONObject("comments");
			JSONArray comments = data_container_conversation
					.getJSONArray("data");
			for (int j = 0; j < comments.length(); j++) {
				JSONObject comment_object = comments.getJSONObject(j);
				System.out.println("COMMENT -> " + comment_object);
				JSONObject from = comment_object.getJSONObject("from");
				for (Kornuit k : controller.getKornuiten()) {
					if (k.getId().equalsIgnoreCase(from.getString("id"))) {
						// System.err.println("IK HEB KORNUIT GEVONDEN SWA");
						k.setScore(2, k.getScore(2) + 1f);
						k.setMessagesReceived((k.getMessagesReceived() + 1));

					}
				}
			}

		}

	}

}
