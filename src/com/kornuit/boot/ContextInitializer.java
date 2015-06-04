<<<<<<< HEAD
package com.kornuit.boot;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import scrumbag.Controller;

import com.kornuit.core.security.SecurityLog;
import com.kornuit.core.security.User;


/**
 * 
 * @author Mark Stroeven & Tim Ijpenga
 *
 */
public class ContextInitializer implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent context) {

		//NO IMPEMENTATION
		
	}

	/**
	 * This method is started directly when the server starts. This method initalizes the security map 
	 * used to store client information and the user map used t store currently active users.
	 */
	@Override
	public void contextInitialized(ServletContextEvent context) {

		Map<String, SecurityLog> securityMap = new HashMap<String, SecurityLog>();
		context.getServletContext().setAttribute("securitymap", securityMap);
		
		Map<String, User> currentUsers = new HashMap<String, User>();
		context.getServletContext().setAttribute("users", currentUsers);
		
		System.out.println("************************************************************************");
		System.out.println("********Soms smeer ik pindakaas op mijn scrotum*************************");
		System.out.println("***********En laat het mijn hond eraf likken.***************************");
		System.out.println("************************************************************************");
		
		Controller controller = new Controller();
		context.getServletContext().setAttribute("leonidasController", controller);
		
	}

}
=======
package com.kornuit.boot;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import scrumbag.Controller;

import com.kornuit.core.security.SecurityLog;
import com.kornuit.core.security.User;


/**
 * 
 * @author Mark Stroeven & Tim Ijpenga
 *
 */
public class ContextInitializer implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent context) {

		//NO IMPEMENTATION
		
	}

	/**
	 * This method is started directly when the server starts. This method initalizes the security map 
	 * used to store client information and the user map used t store currently active users.
	 */
	@Override
	public void contextInitialized(ServletContextEvent context) {

		Map<String, SecurityLog> securityMap = new HashMap<String, SecurityLog>();
		context.getServletContext().setAttribute("securitymap", securityMap);
		
		Map<String, User> currentUsers = new HashMap<String, User>();
		context.getServletContext().setAttribute("users", currentUsers);
		
		System.out.println("************************************************************************");
		System.out.println("********Soms smeer ik pindakaas op mijn scrotum*************************");
		System.out.println("***********En laat het mijn hond eraf likken.***************************");
		System.out.println("************************************************************************");
		
		Controller controller = new Controller();
		context.getServletContext().setAttribute("leonidasController", controller);
		
	}

}
>>>>>>> 5c7e126b334cd885a5154cf42e7a4662abf935b2
