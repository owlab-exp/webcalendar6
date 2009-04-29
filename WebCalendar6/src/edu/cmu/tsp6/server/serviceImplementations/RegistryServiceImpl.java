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
	public void createUser(User user) throws Exception  {
		// check if correct type
		if (!(user instanceof User)) {
			throw new UnsupportedOperationException("User registration is supported");
		}
		User usr = (User)user;
		//validate if all the fields are present
		if (user.getUserId() == null || user.getUserId().length() ==0 ) {
			throw new NullPointerException("User Id should not be blank");
		}		
		if (usr.getName().equals(null) || user.getName().trim().length() ==0 ) {
			throw new NullPointerException("User Name should not be blank");
		}
		if (usr.getPassword() == null|| user.getPassword().length() ==0 ) {
			throw new NullPointerException("Password should not be blank");
		}
		if (usr.getEmail() == null|| user.getEmail().length() ==0 ) {
			throw new NullPointerException("User Name should not be blank");
		}
		if (usr.getRemindDays()<= 0) {
			throw new NullPointerException("Remind date should be greater than 0");
		}
		
		UserDAO ud = UserDAO.getInstance();
		//validate if event with same birthday person
		System.out.println(usr.getUserId());
		 
		// save it using the UserDAO
		if (ud.isUserExist(usr.getUserId())){
			throw new NullPointerException(usr.getUserId() + " is already registered!");
		}		
		ud.addUser((User)usr);
		//test it
	}

	@Override
	public User findUser(String userId) throws Exception  {
		UserDAO ud = UserDAO.getInstance();
		return ud.getUser(userId);
	}

	
}
