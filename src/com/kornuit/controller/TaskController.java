package com.kornuit.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kornuit.calendar.Afspraak;
import com.kornuit.calendar.CreateCalendarEvent;
import com.kornuit.connections.FBConnection;
import com.kornuit.connections.OracleJDBC;
import com.kornuit.core.security.User;
import com.kornuit.data.FBGraph;

@Controller
public class TaskController {

	private String code = "";
	@Autowired
	ServletContext context;

	/**
	 * Login with FaceBook.
	 * @param request HttpServletRequest request
	 * @param response HttpServletResponse response
	 * @return ModelAndView
	 */
	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView MV = new ModelAndView("index");

		code = request.getParameter("code");
		if (code == null || code.equals("")) {
			throw new RuntimeException(
					"ERROR: Didn't get code parameter in callback.");
		}

		FBConnection fbConnection = new FBConnection();
		String accessToken = fbConnection.getAccessToken(code);

		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);

		request.getSession().setAttribute("name", fbProfileData.get("name"));
		request.getSession()
				.setAttribute("facebookId", fbProfileData.get("id"));
		return MV;
	}

	/**
	 * Logs out currently logged in user.
	 * @param request HttpServletRequest request
	 * @param response HttpServletResponse response
	 * @return ModelAndView
	 */
	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView MV = new ModelAndView("index");

		FBConnection fbConnection = new FBConnection();
		fbConnection.clearAccessToken();
		request.getSession().invalidate();

		return MV;
	}

	/**
	 * Checks if user is logged in, if true it sets all the appointments in the request and returns the URL to forward.
	 * @param request HttpServletRequest request
	 * @param response HttpServletResponse response
	 * @return String URL
	 * @throws FileNotFoundException throws FileNotFoundException
	 * @throws IOException throws IOException
	 * @throws SQLException throws SQLException
	 */
	@RequestMapping("mijnkornuit")
	public String mijnKornuit(HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException, IOException, SQLException {
		
		String username = request.getSession().getAttribute("username").toString();
		List<Afspraak> alle_afspraken = OracleJDBC.getAfspraken(
				request.getSession().getServletContext()
						.getRealPath("/properties/connections.properties"), username);
		
		request.setAttribute("afspraken", alle_afspraken);
		
		return "mijnkornuit";
	}

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping("accounts")
	public String accounts() {
		return "accounts";
	}

	@RequestMapping("suggestie")
	public String suggestie() {
		return "suggestie";
	}

	@RequestMapping("loguit")
	public String loguit() {

		// TODO LOGUIT FLAGS

		return "login";
	}

	/**
	 * Sends a FaceBook URL where to user is able to log in to his FaceBook account to connect FaceBook to this application.
	 * @param request HttpServletRequest request
	 * @param response HttpServletResponse response
	 * @return String URL
	 * @throws IOException throws IOException
	 */
	@RequestMapping("newfacebookaccount")
	public String newfacebookaccount(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String fbLoginUrl = "";
		try {
			fbLoginUrl = "http://www.facebook.com/dialog/oauth?"
					+ "client_id="
					+ FBConnection.FB_APP_ID
					+ "&redirect_uri="
					+ URLEncoder.encode(
							"http://localhost:8080/Kornuit/datamapperfacebook",
							"UTF-8") + "&scope=email";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		response.sendRedirect(fbLoginUrl);
		return "accounts";
	}

	/**
	 * Retrieves a FaceBook access token, and stores it into the database coupled with the username.
	 * @param request HttpServletRequest request
	 * @param response HttpServletResponse response
	 * @return String URL
	 * @throws FileNotFoundException throws FileNotFoundException
	 * @throws IOException throws IOException
	 * @throws SQLException throws SQLException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("datamapperfacebook")
	public String datamapper(HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException, SQLException {

		String code = request.getParameter("code");
		System.out.println("CODE = " + code);
		FBConnection con = new FBConnection();
		String token = con.getAccessToken(code);

		request.getSession().setAttribute("facebooktoken", true);
		OracleJDBC.saveToken(
				context.getRealPath("/properties/connections.properties"),
				token, "FACEBOOK",
				(String) request.getSession().getAttribute("username"));

		String username = (String) request.getSession()
				.getAttribute("username");
		Map<String, User> users = (Map<String, User>) context
				.getAttribute("users");
		if (users.containsKey(username)) {
			users.remove(username);
		}
		request.getSession().invalidate();
		return "login";

	}

	/**
	 * Deletes the already coupled FaceBook access token from the database.
	 * @param request HttpServletRequest request
	 * @param response HttpServletResponse response
	 * @return String URL
	 * @throws FileNotFoundException throws FileNotFoundException
	 * @throws IOException throws IOException
	 * @throws SQLException throws SQLException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("deletefacebookaccount")
	public String deletefacebookaccount(HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException, SQLException {

		String username = (String) request.getSession()
				.getAttribute("username");
		OracleJDBC.deleteToken(
				context.getRealPath("/properties/connections.properties"),
				username, "FACEBOOK");
		request.getSession().setAttribute("facebooktoken", false);
		Map<String, User> users = (Map<String, User>) context
				.getAttribute("users");
		if (users.containsKey(username)) {
			users.remove(username);
		}
		request.getSession().invalidate();
		return "login";
	}
	
	/**
	 * Checks if the user has connected to Google, if not the user will be asked to login with his Google account, after that has successfully finished it will make a new appointment into Google Calendar.
	 * @param request HttpServletRequest request
	 * @param response HttpServletResponse response
	 * @return String URL
	 * @throws IOException throws IOException
	 * @throws SQLException throws SQLException
	 */
	@RequestMapping("makecalendarevent")
	public String makecalendarevent(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		
		String dateTimeInput = request.getParameter("sug_datetime");
		dateTimeInput += ":0";
		System.out.println("HALLOOOOOOOOOOOOOOOOOOOOO: " + dateTimeInput);
		Timestamp stamp = Timestamp.valueOf(dateTimeInput.replace("T"," "));
		
		System.out.println("TETSTESTSETSETES: " + stamp);
		Afspraak a = new Afspraak();
		a.setActiviteit(request.getParameter("sug_activity"));
		a.setDatumTijd(stamp);
		a.setFacebookVriendId(request.getParameter("sug_id"));
		a.setFacebookVriendNaam(request.getParameter("sug_name"));
		a.setLocatie(request.getParameter("sug_location"));
		a.setUser(request.getSession().getAttribute("username").toString());
		
		OracleJDBC.nieuweAfspraak(
				context.getRealPath("/properties/connections.properties"), a);

		if(request.getParameter("makeGE") != null) {
			if(request.getParameter("makeGE").equals("YES")) {
				CreateCalendarEvent event = new CreateCalendarEvent();
				boolean isMade = event.createEvent(a);
				System.out.println("IS ER EEN EVENT AANGEMAAKT: " + isMade);
			} else {
				System.out.println("ER IS GEEN EVENT AANGEMAAKT!");
			}
		}

		return "suggestie";
	}

}
