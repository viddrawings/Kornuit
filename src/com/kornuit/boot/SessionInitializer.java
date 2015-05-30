package com.kornuit.boot;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import scrumbag.Controller;
import scrumbag.domain.Kornuit;

import com.kornuit.connections.OracleJDBC;
import com.kornuit.core.security.User;

/**
 * 
 * @author Mark Stroeven & Tim Ijpenga
 *
 */
public class SessionInitializer implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent session) {

		Controller controller = new Controller();
		session.getSession().setAttribute("leonidasController", controller);
		System.out.println("SESSON HAS BEEN CREATED");

	}

	/**
	 * Used to automatically remove any user whoms sessions is destroyed from
	 * the users map
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent session) {

		System.out.println("Writing user data to database");

		String username = null;
		if (null != session.getSession().getAttribute("username")) {
			username = (String) session.getSession().getAttribute("username");
		}
		Controller c = (Controller) session.getSession().getAttribute(
				"leonidasController");
		List<Kornuit> all_kornuiten = c.getKornuiten();
		System.out.println("CONTENT OF KORNUITEN");
		for(Kornuit k : all_kornuiten){
			System.out.println(k.getName());
		}
		System.out.println("END OF CONTENT");

		try {
			OracleJDBC.saveKornuit(session.getSession().getServletContext()
					.getRealPath("/properties/connections.properties"),
					username, all_kornuiten);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ServletContext context = session.getSession().getServletContext();
		Map<String, User> users = (Map<String, User>) context
				.getAttribute("users");
		users.remove(username);
		System.out.println("SESSON HAS BEEN DESTROYED");

	}

}
