package edu.cmu.tsp6.server.serviceImplementations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.cmu.tsp6.client.LogoutService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LogoutServiceImpl extends RemoteServiceServlet implements
LogoutService {

	public void logoutServer(String id) throws Exception {
		System.out.println("id=" + id + " will log out");
//		String serverInfo = getServletContext().getServerInfo();
//		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
//		return "Hello, " + id + "!<br><br>I am running " + serverInfo
//				+ ".<br><br>It looks like you are using:<br>" + userAgent;
		
		HttpServletRequest request = null;
		HttpSession session = null;
		

			request = this.getThreadLocalRequest();
			session = request.getSession();
			// Need revising
			//session.setAttribute(id, new Object());
			session.invalidate();
			//return id;
			
		
			
	}
}
