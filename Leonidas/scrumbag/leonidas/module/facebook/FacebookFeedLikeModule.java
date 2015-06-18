package scrumbag.leonidas.module.facebook;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import scrumbag.Controller;
import scrumbag.domain.Kornuit;
import scrumbag.leonidas.module.Module;
 
public class FacebookFeedLikeModule extends Module {
    private static final String FIELD = "/me?fields=feed{likes}";
 
    public FacebookFeedLikeModule() {
        SCORE_INDEX = 1;
    }
 
    /**
     * Retrieves all 'Kornuiten' and calculates their score based on feed likes. When done it places them in the controller.
     */
    @Override
    public void calculate(Controller controller, String accessToken) {
        // Get a json object from facebook
        JSONObject root = getJsonObject(accessToken, FIELD);
        JSONObject dataContainer = root.getJSONObject("feed");
        JSONArray array = dataContainer.getJSONArray("data");
        String id;
 
        // Loop door alle json objecten heen
        for (int i = 0; i < array.length(); i++) {
            JSONObject o = null;
            try {
                // Get the like data
                o = array.getJSONObject(i);
                JSONObject likeContainer = o.getJSONObject("likes");
                JSONArray likeArray = likeContainer.getJSONArray("data");
 
                id = null;
                // Loop through the like array
                for (int a = 0; a < likeArray.length(); a++) {
                    JSONObject like = likeArray.getJSONObject(a);
                    id = like.getString("id");
                    // Als de kornuit nog niet bestaat, maak 'm dan aan
                    Kornuit kornuit = null;
                    if (!controller.hasKornuit(id)) {
                        System.out.println("NEW KORNUIT ->" + id);
                        kornuit = new Kornuit();
                        kornuit.setId(id);
                        kornuit.setName(like.getString("name"));
                    } else {
                        kornuit = controller.getKornuit(id);
                    }
                    kornuit.setScore(SCORE_INDEX, kornuit.getScore(SCORE_INDEX) + 1F);
                    kornuit.setAmmountOfFeedLikes(kornuit.getAmmountOfFeedLikes() +1);
                    controller.addKornuit(kornuit);
                }
            } catch (JSONException e) {
                // JSON informatie die we niet nodig hebben
            }
        }
        System.out.println("Ik roep sort kornuiten aan");
        sortKornuiten(controller.getKornuiten());
    }
}