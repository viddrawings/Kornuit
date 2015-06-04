<<<<<<< HEAD
package com.kornuit.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import scrumbag.Controller;
import scrumbag.domain.Kornuit;

@Path("/SuggestionProvider/{accesstoken}")
public class SuggestionProvider {

	private String picture;

	@GET
	public String getSuggestion(@PathParam("accesstoken") String accessToken, @Context HttpServletRequest request)
			throws JSONException, IOException {

		
		String[] tokensList = accessToken.split(("&"));
		String validToken = tokensList[0];
		System.out.println("received token = " + validToken);
		Controller controller = (Controller) request.getSession().getAttribute("leonidasController");

		// Simulate user login

		Kornuit winner = controller.makeSuggestion(validToken);
		System.out.println("SUGGESTION: " + winner.getName());
		picture = "http://graph.facebook.com/" + winner.getId() + "/picture?type=large";
		StringBuffer buffer = null;

		BufferedReader reader = null;
		try {
			URL url = new URL("https://graph.facebook.com/" + winner.getId()
					+ "?&access_token=" + accessToken);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

		} finally {
			if (reader != null)
				reader.close();
		}
		JSONObject userdata = new JSONObject(buffer.toString());
		String firstName = userdata.getString("first_name");
		String lastName = userdata.getString("last_name");
		String name = firstName + " " + lastName;
		JSONObject payload = new JSONObject("{\"payload\" : \"" + picture
				+ "\", \"name\" : \" " + name + "\"}");

		return payload.toString();

	}

}
=======
package com.kornuit.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import scrumbag.Controller;
import scrumbag.domain.Kornuit;

@Path("/SuggestionProvider/{accesstoken}")
public class SuggestionProvider {

	private String picture;

	@GET
	public String getSuggestion(@PathParam("accesstoken") String accessToken, @Context HttpServletRequest request)
			throws JSONException, IOException {

		
		String[] tokensList = accessToken.split(("&"));
		String validToken = tokensList[0];
		System.out.println("received token = " + validToken);
		Controller controller = (Controller) request.getSession().getAttribute("leonidasController");

		// Simulate user login

		Kornuit winner = controller.makeSuggestion(validToken);
		System.out.println("SUGGESTION: " + winner.getName());
		picture = "http://graph.facebook.com/" + winner.getId() + "/picture?type=large";
		StringBuffer buffer = null;

		BufferedReader reader = null;
		try {
			URL url = new URL("https://graph.facebook.com/" + winner.getId()
					+ "?&access_token=" + accessToken);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

		} finally {
			if (reader != null)
				reader.close();
		}
		JSONObject userdata = new JSONObject(buffer.toString());
		String firstName = userdata.getString("first_name");
		String lastName = userdata.getString("last_name");
		String name = firstName + " " + lastName;
		JSONObject payload = new JSONObject("{\"payload\" : \"" + picture
				+ "\", \"name\" : \" " + name + "\"}");

		return payload.toString();

	}

}
>>>>>>> 5c7e126b334cd885a5154cf42e7a4662abf935b2
