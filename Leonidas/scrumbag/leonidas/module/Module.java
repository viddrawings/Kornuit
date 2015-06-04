package scrumbag.leonidas.module;
 
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
 
import org.json.JSONObject;
 
import scrumbag.Controller;
import scrumbag.domain.Kornuit;
import scrumbag.leonidas.connector.UrlReader;
import scrumbag.leonidas.modifier.ModifierProperties;
import scrumbag.leonidas.modifier.NoModifierFoundException;
import scrumbag.leonidas.modifier.RandomModifier;
 
public abstract class Module {
    protected String MODIFIER_NAME;
    protected float MODIFIER;
    protected int SCORE_INDEX;
    protected ModifierProperties modifierProperties;
 
    public Module() {
        modifierProperties = new ModifierProperties();
        String[] className = this.getClass().getName().split("\\.");
        MODIFIER_NAME = className[className.length - 1];
        try {
            MODIFIER = modifierProperties.getModifier(MODIFIER_NAME);
        } catch (NoModifierFoundException e) {
            e.printStackTrace();
        }
    }
     
    public JSONObject getJsonObject(String accessToken, String FIELD) {
        System.out.println("Connecting to facebook to get json...");
        try {
            JSONObject root = UrlReader.readJsonFromUrl("https://graph.facebook.com" + FIELD + "&access_token=" + accessToken);
 
            System.out.println("DEBUG -> feed object");
            System.out.println("\n");
            System.out.println(root.toString());
            System.out.println("\n");
             
            return root;
        } catch (Exception e) {
            System.err.println("Error occured while getting json from facebook (is the accesstoken still valid?)");
            e.printStackTrace();
        }
        return null;
    }
 
    public List<Kornuit> sortKornuiten(List<Kornuit> kornuiten) {
        // Sort all kornuiten on their scores (ascending)
        Collections.sort(kornuiten, new Comparator<Kornuit>() {
            public int compare(Kornuit k1, Kornuit k2) {
                return (int) (k1.getScore(SCORE_INDEX) - k2.getScore(SCORE_INDEX)); // Ascending
            }
        });
 
        // Add all scores to a hashmap, this prevents duplicates which is needed
        // to calculate each point group
        HashSet<Float> hs = new HashSet<Float>();
        for (Kornuit k : kornuiten) {
            hs.add(k.getScore(SCORE_INDEX));
        }
 
        // Calculate point group percentage
        float percent = 100.0f / (hs.size() - 1);
        int index = 1;
        float tempScore = kornuiten.get(0).getScore(SCORE_INDEX);
        float tempScore2;
        for (Kornuit k : kornuiten) {
            // If the score is different from the last, increment the index
            tempScore2 = k.getScore(SCORE_INDEX);
            k.setScore(SCORE_INDEX, percent * index);
            if (tempScore2 != tempScore) {
                index++;
            }
            tempScore = tempScore2;
        }
 
        for (Kornuit k : kornuiten) {
            k.setScore(SCORE_INDEX, k.getScore(SCORE_INDEX) * MODIFIER - RandomModifier.getRandom());
        }
 
        // Sort all kornuiten on their scores (ascending)
        Collections.sort(kornuiten, new Comparator<Kornuit>() {
            public int compare(Kornuit k1, Kornuit k2) {
                return Float.compare(k1.getScore(SCORE_INDEX), k2.getScore(SCORE_INDEX));
            }
        });
 
        return kornuiten;
    }
 
    public abstract void calculate(Controller controller, String accessToken);
}