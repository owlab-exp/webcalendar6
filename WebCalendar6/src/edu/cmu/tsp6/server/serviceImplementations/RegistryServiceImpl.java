/**
 * 
 */
package edu.cmu.tsp6.server.serviceImplementations;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.cmu.tsp6.client.RegistryService;
import edu.cmu.tsp6.client.bo.User;
import edu.cmu.tsp6.server.dao.UserDAO;

/**
 * @author YONG
 *
 */
@RemoteServiceRelativePath("Registration")
public class RegistryServiceImpl extends RemoteServiceServlet implements RegistryService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static RegistryServiceImpl instance = new RegistryServiceImpl();
	
	public static RegistryService getInstance() {
		return instance;
	}

	/* (non-Javadoc)
	 * @see edu.cmu.tsp6.client.RegistryService#createUser(edu.cmu.tsp6.bo.User)
	 */
	@Override
	public void createUser(User user) {
		// check if correct type
		if (!(user instanceof User)) {
			throw new UnsupportedOperationException("User registration is supported");
		}
		User usr = (User)user;
		//validate if all the fields are present
		if (usr.getUserId() == null) {
			throw new NullPointerException("User Id should not be null");
		}
		if (usr.getName() == null) {
			throw new NullPointerException("User Name should not be null");
		}
		if (usr.getPassword() == null) {
			throw new NullPointerException("Password should not be null");
		}
		if (usr.getEmail() == null) {
			throw new NullPointerException("User Name should not be null");
		}
		if (usr.getRemindDays()==0) {
			throw new NullPointerException("User Name should be greater than 0");
		}
		System.out.println("-1");
		
		UserDAO ud = UserDAO.getInstance();
		//validate if event with same birthday person
		System.out.println(usr.getUserId());
		
		System.out.println("-3");
		// save it using the UserDAO
		
		ud.addUser((User)usr);
		//test it
	}

	@Override
	public User findUser(String userName) {
		UserDAO ud = UserDAO.getInstance();
		return ud.getUser(userName);
	}

	@Override
	public void updateUser(User user) throws Exception {
		// check if correct type
		/*if (!(user instanceof User)) {
			throw new UnsupportedOperationException("User registration is supported");
		}*/
		User usr = (User) this.getThreadLocalRequest().getSession().getAttribute(WebCalendarConstants.USER);
		
		if (usr.getName() == null) {
			throw new NullPointerException("User Name should not be null");
		}
		if (usr.getPassword() == null) {
			throw new NullPointerException("Password should not be null");
		}
		if (usr.getEmail() == null) {
			throw new NullPointerException("User Name should not be null");
		}
		if (usr.getRemindDays()==0) {
			throw new NullPointerException("User Name should be greater than 0");
		}
		
		UserDAO ud = UserDAO.getInstance();
		//validate if event with same birthday person
		
		if (ud.getUser(usr.getUserId())!= null) {
			throw new IllegalStateException("User has been registered already");
		}
		// save it using the UserDAO
		
		ud.editUser(usr);
		//test it
	}
	

}
