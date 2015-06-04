package scrumbag.leonidas.module.facebook;

import org.json.JSONArray;
import org.json.JSONObject;

import scrumbag.Controller;
import scrumbag.domain.Kornuit;
import scrumbag.leonidas.module.Module;

public class FacebookTagModule extends Module{

	private static String FIELD = "/me?fields=tagged";
	@Override
	public void calculate(Controller controller, String accessToken) {
		JSONObject root = getJsonObject(accessToken, FIELD);
		JSONObject data_container = root.getJSONObject("tagged");
		JSONArray tag_data = data_container.getJSONArray("data");
		
		for(int i = 0; i < tag_data.length(); i++){
			JSONObject current_object = tag_data.getJSONObject(i);
			System.out.println(current_object);
			JSONObject value_node = current_object.getJSONObject("from");
			String id = value_node.getString("id");
			for(Kornuit k: controller.getKornuiten()){
				if(id.equalsIgnoreCase(k.getId())){
					System.err.println("FRIEND FOUND");
					k.setScore(3, k.getScore(3)+1);
					k.setAmmountOfTags((k.getAmmountOfTags()+1));
				}
			}
		}
	}

}
