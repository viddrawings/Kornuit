package scrumbag.util;

import org.json.JSONObject;

import scrumbag.Controller;
import scrumbag.domain.Kornuit;

public class GraphTransformer {
	
	/**
	 * Retrieves the top 4 'Kornuiten' and stores them in a JSON object.
	 * @param con Controller controller
	 * @return JSONObject top 4
	 */
	public static JSONObject getTop4(Controller con){
		
		Kornuit [] top4 = new Kornuit[4];
		Kornuit highest = null;
		
		for(int i = 0; i < 4 ; i++){
			for(Kornuit k : con.getKornuiten()){
				
				if(highest == null)highest = k;
				if(k.getScore() > highest.getScore())highest = k;
			}
			top4[i] = highest;
		}

		String json = "{ \"data\" : [";
		int counter  = 0;
		for(Kornuit k: top4 ){
			if(counter < 3){
			json = json + "{ \"id\" : \"" + k.getId() + "\"}," ;
			counter++;
			}
			else{
			json = json + "{ \"id\" : \"" + k.getId() + "\"}" ;
			counter++;
			}
		}
		json = json + "]}";
		
		JSONObject data = new JSONObject(json);
		System.out.println(json);
		return data;
	}

}
