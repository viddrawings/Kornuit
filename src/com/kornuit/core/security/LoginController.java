package com.kornuit.core.security;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import scrumbag.domain.Kornuit;

import com.kornuit.connections.OracleJDBC;
import com.kornuit.data.FBGraph;

@Controller
public class LoginController {

	@Autowired
	ServletContext context;

	@SuppressWarnings("unchecked")
	@RequestMapping("securelogin")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password)
			throws FileNotFoundException, SQLException, IOException,
			ServletException {

		ModelAndView MV = new ModelAndView("index");
		String path = context.getRealPath("/properties/connections.properties");
		HashMap<String, SecurityLog> securityMap = null;
		Object o = context.getAttribute("securitymap");
		if (o instanceof HashMap<?, ?>) {
			securityMap = (HashMap<String, SecurityLog>) o;
		}

		String remoteAddress = request.getRemoteAddr();
		String remoteHost = request.getRemoteHost();
		int remotePort = request.getRemotePort();

		if (securityMap.containsKey(remoteAddress)) {
			System.out.println("IP ADDRESS FOUND IN RECORDS -> UPDATING TIME");
			SecurityLog currentEntry = securityMap.get(remoteAddress);
			if (currentEntry.isBanned()) {
				request.setAttribute("loginerror",
						"Ongeldige username / password");
				request.getRequestDispatcher("loguit").forward(request,
						response);
			}
			currentEntry.setLastVisit(new Date());
			System.out.println("VALIDATING USER");
			if (OracleJDBC.validateUser(path, username, password)) {

				User u = new User(username, remoteAddress);
				Map<String, User> users = (Map<String, User>) context
						.getAttribute("users");
				request.getSession().setAttribute("username", username);
				users.put(username, u);
				System.out.println("USER IS VALID!");
				setMediaContent(request, username);

				populateKornuit(username, request);

				request.getRequestDispatcher("index")
						.forward(request, response);

			} else {
				System.out.println("Routing user back to login");
				request.setAttribute("loginerror",
						"Ongeldige username / password");
				request.getRequestDispatcher("loguit").forward(request,
						response);
			}

		} else {
			System.out
					.println("IP ADDRESS NOT FOUND IN RECORDS -> UPDATING TIME");
			SecurityLog newEntry = new SecurityLog(remoteAddress, remoteHost,
					remotePort, false, null, null);
			newEntry.setLastVisit(new Date());
			securityMap.put(remoteHost, newEntry);
			System.out.println("VALIDATING USER");
			if (OracleJDBC.validateUser(path, username, password)) {

				User u = new User(username, remoteAddress);
				Map<String, User> users = (Map<String, User>) context
						.getAttribute("users");
				request.getSession().setAttribute("username", username);
				users.put(username, u);
				System.out.println("USER IS VALID!");
				setMediaContent(request, username);

				populateKornuit(username, request);

				request.getRequestDispatcher("index")
						.forward(request, response);

			} else {
				System.out.println("Routing user back to login");
				request.setAttribute("loginerror",
						"Ongeldige username / password");
				request.getRequestDispatcher("loguit").forward(request,
						response);
			}

		}

		return MV;
	}

	
	public void setMediaContent(HttpServletRequest request, String username)
			throws FileNotFoundException, SQLException, IOException {

		if (OracleJDBC.hasFacebookToken(
				context.getRealPath("/properties/connections.properties"),
				username)) {

			String tokenValue = OracleJDBC.getToken(
					context.getRealPath("/properties/connections.properties"),
					username, "FACEBOOK");
			tokenValue = tokenValue.replaceAll("access_token=", "");

			String prefix = "'";
			String suffix = "'";
			String finalToken = prefix + tokenValue + suffix;
			finalToken = finalToken.replaceAll("\n", "");

			System.out.println("TOKEN VALUE = " + finalToken);

			request.getSession().setAttribute("fbTokenValue", finalToken);

			request.getSession().setAttribute("facebooktoken", true);
			FBGraph fbGraph = new FBGraph(OracleJDBC.getToken(
					context.getRealPath("/properties/connections.properties"),
					username, "FACEBOOK"));
			String graph = fbGraph.getFBGraph();
			Map<String, String> fbProfileData = fbGraph.getGraphData(graph);

			request.getSession()
					.setAttribute("name", fbProfileData.get("name"));
			request.getSession().setAttribute("facebookId",
					fbProfileData.get("id"));
		} else {
			request.getSession().setAttribute("facebooktoken", false);
		}

		if (OracleJDBC.hasTwitterToken(
				context.getRealPath("/properties/connections.properties"),
				username)) {
			request.getSession().setAttribute("twittertoken", true);
		} else {

		}
		request.getSession().setAttribute("twittertoken", false);

		if (OracleJDBC.hasGoogleToken(
				context.getRealPath("/properties/connections.properties"),
				username)) {
			request.getSession().setAttribute("googletoken", true);
		} else {

		}
		request.getSession().setAttribute("googletoken", false);

	}

	public void populateKornuit(String username, HttpServletRequest request)
			throws FileNotFoundException, IOException, SQLException {

		List<Kornuit> alle_kornuiten = OracleJDBC.getKornuiten(
				request.getSession().getServletContext()
						.getRealPath("/properties/connections.properties"),
				username);
		scrumbag.Controller c = (scrumbag.Controller) request.getSession()
				.getAttribute("leonidasController");
		c.setKornuiten(alle_kornuiten);
		System.out.println("Deze kornuiten zijn vanuit de database opgehaald:");
		for (Kornuit k : alle_kornuiten) {
			System.out.println(k.getName());
		}
	}

}
