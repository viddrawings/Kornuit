package com.kornuit.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class FBGraph {
	private String accessToken;

	/**
	 * Constructs a FBGraph with an access token.
	 * @param accessToken Access token
	 */
	public FBGraph(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * Connect to FaceBook and retrieve logged in user's FaceBook information.
	 * @return String
	 */
	public String getFBGraph() {
		String graph = null;
		try {

			String g = "https://graph.facebook.com/me?" + accessToken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}
	
	/**
	 * Connect to FaceBook and retrieve logged in user's FaceBook friends.
	 * @return JSONObject
	 */
	public JSONObject getFBFriends() {
		String friends = null;
		JSONObject json = null;
		try {
			String g = "https://graph.facebook.com/me?fields=friends&" + accessToken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			friends = b.toString();
			System.out.println(friends);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		json = new JSONObject(friends);
		return json;
	}

	/**
	 * Convert FaceBook data to a Map.
	 * @param fbGraph received String from getFBGraph()
	 * @return Map with String, String
	 */
	public Map<String, String> getGraphData(String fbGraph) {
		Map<String, String> fbProfile = new HashMap<String, String>();
		try {
			JSONObject json = new JSONObject(fbGraph);
			fbProfile.put("id", json.getString("id"));
			fbProfile.put("name", json.getString("name"));
			if (json.has("gender"))
				fbProfile.put("gender", json.getString("gender"));
			if (json.has("locale"))
				fbProfile.put("locale", json.getString("locale"));
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return fbProfile;
	}
}