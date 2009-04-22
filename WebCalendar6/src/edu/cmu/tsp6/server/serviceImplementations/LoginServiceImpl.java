package edu.cmu.tsp6.server.serviceImplementations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.cmu.tsp6.client.LoginService;
import edu.cmu.tsp6.client.bo.User;
import edu.cmu.tsp6.server.dao.UserDAO;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {

	public String loginServer(String id, String password) throws Exception {
		System.out.println("id=" + id + ", password=" + password);
//		String serverInfo = getServletContext().getServerInfo();
//		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
//		return "Hello, " + id + "!<br><br>I am running " + serverInfo
//				+ ".<br><br>It looks like you are using:<br>" + userAgent;
		
		HttpServletRequest request = null;
		HttpSession session = null;
		
		UserDAO ud = UserDAO.getInstance();
		
		User user = ud.getUser(id);
		
		if(user.getPassword().equals(password)) {
			System.out.println("LoginServiceImpl: Login Success");
			
			request = this.getThreadLocalRequest();
			session = request.getSession();
			// Need revising
			session.setAttribute(id, new Object());
			return user.getName();
		} else {
			System.out.println("LoginServiceImpl: Fail");
			return "";
		}		
		
			
	}
}
