package com.kornuit.core.security;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityFilter implements Filter {

	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized MODDERFOKKER");
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		System.out.println("FILTER MAGIC");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		Map<String, User> users = (Map<String, User>) context
				.getAttribute("users");
		String un = (String) req.getSession().getAttribute("username");
		if (null != un) {
			System.out.println("USERNAME DETECTED: " + un);
		}


		String uri = req.getRequestURI();
		System.out.println(uri);
		if (uri.endsWith(("/Kornuit/"))) {
			System.out.println("HET IS EEN REQUES NAAR DE INLOGPAGINA");
			chain.doFilter(request, response);
		} else if (uri.matches(".*(css|jpg|png|gif|js)")) {
			chain.doFilter(request, response);
			return;
		}

		else if (uri.endsWith(("/Kornuit/securelogin"))) {
			System.out.println("HET IS EEN REQUES NAAR DE INLOGSERVLET");
			chain.doFilter(request, response);
		} else if (users.containsKey(un)) {
			chain.doFilter(request, response);
		} else {
			res.sendError(HttpServletResponse.SC_FORBIDDEN,
					"CRITICAL ERROR: please log in to use this system.");
		}

	}

	public void destroy() {
		// close any resources here
	}

}