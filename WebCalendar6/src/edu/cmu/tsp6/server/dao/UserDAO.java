package edu.cmu.tsp6.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.cmu.tsp6.client.bo.NotifyUser;
import edu.cmu.tsp6.client.bo.User;
import edu.cmu.tsp6.server.dao.exception.UserExistException;
import edu.cmu.tsp6.server.dao.exception.UserNotExistException;

/**
 * A singleton class for access to the User Information
 * 
 * @author Non
 * 
 */
public class UserDAO {
	private static UserDAO instance;

	private UserDAO() {
	}

	public static UserDAO getInstance() {
		if (instance == null)
			instance = new UserDAO();

		return instance;
	}

	private boolean isUserExist(String userId) {
		String sql = String.format("SELECT * FROM USER where USER_ID = '%s'",
				userId);
		Statement s = DatabaseConnection.getInstance().getStatement();

		try {
			s.execute(sql);
			return (s.getResultSet().next());
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

	}

	/**
	 * Add new user to the system
	 * 
	 * @param newUser
	 *            New user to be added
	 * @throws UserExistException
	 *             If the given userId exists in the system
	 */
	public void addUser(User newUser) throws UserExistException {
		if (isUserExist(newUser.getUserId()))
			throw new UserExistException();

		String sql = String
				.format(
						"INSERT INTO USER(USER_ID,USER_NAME,USER_EMAIL,USER_REMIND_DAYS,USER_PASSWORD) VALUES ('%s','%s','%s',%d,'%s')",
						newUser.getUserId(), newUser.getName(), newUser
								.getEmail(), newUser.getRemindDays(), newUser
								.getPassword());

		try {
			DatabaseConnection.getInstance().getStatement().execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Edit current user's data
	 * 
	 * @param existingUser
	 *            User data to be updated
	 * @throws UserNotExistException
	 *             If the given User's id does not exist in the system
	 */
	public void editUser(User existingUser) throws UserNotExistException {
		if (!isUserExist(existingUser.getUserId()))
			throw new UserNotExistException();

		String sql = String
				.format(
						"UPDATE USER SET USER_NAME='%s', USER_EMAIL='%s', USER_REMIND_DAYS = '%d', USER_PASSWORD = '%s' WHERE USER_ID='%s'",
						existingUser.getName(),
						existingUser.getEmail(), existingUser.getRemindDays(),
						existingUser.getPassword(), existingUser.getUserId());

		try {
			DatabaseConnection.getInstance().getStatement().execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete the given user Id from the system
	 * 
	 * @param userId
	 * @throws UserNotExistException
	 *             If the given user id does not exist in the system
	 */
	public void deleteUser(String userId) throws UserNotExistException {
		if (!isUserExist(userId))
			throw new UserNotExistException();

		String sql = String.format("DELETE FROM USER WHERE USER_ID='%s' ",
				userId);

		try {
			DatabaseConnection.getInstance().getStatement().execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the user information from the given id
	 * 
	 * @param userId
	 * @throws UserNotExistException
	 *             If the given user id does not exist in the system
	 * @return Null if no such user exist
	 */
	public User getUser(String userId) throws UserNotExistException {
		if (!isUserExist(userId))
			throw new UserNotExistException();

		String sql = String.format("SELECT * FROM USER WHERE USER_ID='%s' ",
				userId);

		try {
			ResultSet rs = DatabaseConnection.getInstance().getStatement().executeQuery(sql);
			rs.next();
			
			User user = new User();
			user.setUserId(rs.getString("USER_ID"));
			user.setName(rs.getString("USER_NAME"));
			user.setEmail(rs.getString("USER_EMAIL"));
			user.setRemindDays(rs.getInt("USER_REMIND_DAYS"));
			user.setPassword(rs.getString("USER_PASSWORD"));
			
			return user;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}

	/**
	 * Get Information of all users in the system
	 * 
	 * @return List of users or empty list if there's no users.
	 */
	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();
		
		String sql = String.format("SELECT * FROM USER");

		try {
			ResultSet rs = DatabaseConnection.getInstance().getStatement().executeQuery(sql);
			while(rs.next()) {
				User user = new User();
				user.setUserId(rs.getString("USER_ID"));
				user.setName(rs.getString("USER_NAME"));
				user.setEmail(rs.getString("USER_EMAIL"));
				user.setRemindDays(rs.getInt("USER_REMIND_DAYS"));
				user.setPassword(rs.getString("USER_PASSWORD"));
				
				users.add(user);
			}
			
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	/**
	 * Get the user list for notification mail
	 * @return Null if there's no list to send
	 */
	public List<NotifyUser> getSendUserList() {
		List<NotifyUser> userList = new ArrayList<NotifyUser>();
		String sql = "select u.USER_ID, u.USER_EMAIL, (select USER_NAME from USER x where x.USER_ID = e.EVENT_BIRTH_PERSON_ID) USER_NAME, e.EVENT_DATE "
				+"from USER u, EVENT e "
				+"where e.EVENT_DATE-u.USER_REMIND_DAYS=curdate()"
				+" and u.USER_ID <> e.EVENT_BIRTH_PERSON_ID";

		try {
			ResultSet rs = DatabaseConnection.getInstance().getStatement().executeQuery(sql);
			while (rs.next()) {
			
				NotifyUser user = new NotifyUser();
				user.setUserId(rs.getString("USER_ID"));
				user.setName(rs.getString("USER_NAME"));
				user.setEmail(rs.getString("USER_EMAIL"));
				user.setDate(rs.getDate("EVENT_DATE"));
				
				userList.add(user);
			}
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
