package edu.cmu.tsp6.client;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * [Temporal for other classes]
 * It is the class which works for user management with database.
 * Add, update and existence check for user from User table.   
 */
public class UserDAO {

	/**	
	 * Insert new user information into user table
	 */
	public int create(UserInfo user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO USER VALUES ");
			insertQuery.append("(?, ?, ?, ?)");
			
			//con = ConnectionManager.getConnection();
			
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			int result = pstmt.executeUpdate();
			pstmt.close();
			con.close();
			return result;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	/**	 
	 * * Update the registered user profile.	
	 * 
	 */
	public int updateUser(UserInfo user) throws SQLException {
		return 0;
		// 	
	}

	
	/**	
	 * Check the whether the user id exist or not
	 *  	 
	 */
	public boolean existedUser(String userId) throws SQLException {
		//
		return false;
	}
}
