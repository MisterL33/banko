package banko.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class ConnectionFilter
 */
@WebFilter("/*")
public class ConnectionFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ConnectionFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String path = req.getContextPath();
		String loginURI = path + "/home";
		String restURI = path + "/rest/";
		// System.out.println(session.getAttribute("checker"));
		boolean loggedIn = session != null && session.getAttribute("checker") == Boolean.TRUE;
		boolean loginRequest = req.getRequestURI().equals(loginURI);
		boolean restRequest = req.getRequestURI().startsWith(restURI);
		System.out.print(req.getRequestURI());

		if (loggedIn || loginRequest || restRequest) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect(loginURI);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
