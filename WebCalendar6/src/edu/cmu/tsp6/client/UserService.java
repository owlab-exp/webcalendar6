package edu.cmu.tsp6.client;

import java.sql.SQLException;


// -- Remove for SQLExceptions
// -- Implement remote service
// -- return exception except SQLException

public class UserService {
	private UserService() {
	}
	public static UserService instance() {
		return (new UserService());
	}
	public int createUser(User user) throws SQLException {
		/*if (getUserDAO().existedUser(user.getUserId())) {
			throw new ExistedUserException(user.getUserId() + " is already registered.");
		}*/
		return 0; //getUserDAO().create(user);
	}
	public int updateUser(User user) throws SQLException {
		/**
		 * Check the user existence and update user profile
		 */
		return 0; //getUserDAO().updateUser(user);
	} 
	
	public User findUser(String userId) throws SQLException {

		/**
		 * Check the user existence
		 */
/*		if (getUserDAO().existedUser(user.getUserId()) {
			throw new UserNotFoundException(userId + " does not exist.");
		}*/
		return null;
	} 	
	
	private UserDAO getUserDAO() {
		return new UserDAO();
	}
}
