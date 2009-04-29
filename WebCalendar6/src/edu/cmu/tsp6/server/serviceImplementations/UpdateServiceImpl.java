/**
 * 
 */
package edu.cmu.tsp6.server.serviceImplementations;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.cmu.tsp6.client.UpdateService;
import edu.cmu.tsp6.client.bo.User;
import edu.cmu.tsp6.server.dao.UserDAO;

/**
 * @author YONG
 *
 */
@RemoteServiceRelativePath("Profile")
public class UpdateServiceImpl extends RemoteServiceServlet implements UpdateService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static UpdateServiceImpl instance = new UpdateServiceImpl();
	
	public static UpdateServiceImpl getInstance() {
		return instance;
	}

	/* (non-Javadoc)
	 * @see edu.cmu.tsp6.client.RegistryService#createUser(edu.cmu.tsp6.bo.User)
	 */
	
	@Override
	public User findUser(String userID)  {
		UserDAO ud = UserDAO.getInstance();
		return ud.getUser(userID);
	}
	
	@Override
	public User getCurrentUser() {
		String t = "";		
		t = this.getThreadLocalRequest().getSession().getAttribute(WebCalendarConstants.ID).toString();
		
		return this.findUser(t);
	}
	
	@Override
	public void updateUser(User user) throws Exception {

		if (user.getName() == null || user.getName().length()== 0) {
			throw new NullPointerException("User Name should not be null");
		}
		if (user.getPassword() == null|| user.getPassword().length()== 0) {
			throw new NullPointerException("Password should not be null");
		}
		if (user.getEmail() == null|| user.getEmail().length()== 0) {
			throw new NullPointerException("User Name should not be null");
		}
		if (user.getRemindDays()<= 0) {
			throw new NullPointerException("Remind date should be greater than 0");
		}
		
		UserDAO ud = UserDAO.getInstance();
		//validate if event with same birthday person
//		
//		if (ud.getUser(user.getUserId())== null) {
//			throw new IllegalStateException("User has not been registered!");
//		}
		// save it using the UserDAO		
		ud.editUser(user);
		
		//test it
	}

	

}
