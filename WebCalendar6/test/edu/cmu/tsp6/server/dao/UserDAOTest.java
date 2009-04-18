package edu.cmu.tsp6.server.dao;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.cmu.tsp6.client.bo.User;
import edu.cmu.tsp6.server.dao.exception.UserExistException;
import edu.cmu.tsp6.server.dao.exception.UserNotExistException;

/**
 * Test {@link UserDAO}
 * @author Varokas
 *
 */
public class UserDAOTest {
	private static final String FILE_INIT = "res/test/userdao/init.xml";
	private static final String FILE_ADD = "res/test/userdao/add.xml";
	private static final String FILE_EDIT = "res/test/userdao/edit.xml";
	private static final String FILE_DELETE = "res/test/userdao/delete.xml";
	
	private static final String SCHEMA = "CALENDARDB";

	private static MySqlConnection dbUnitConnection;
	
	@BeforeClass
	public static void beforeClass() throws DatabaseUnitException {
		dbUnitConnection = new MySqlConnection(DatabaseConnection.getInstance().getConnection(), SCHEMA);
	}
	
	@Test
	public void addUserTest() throws Exception {
		//Insert Init
		IDataSet dataset = new FlatXmlDataSet(new FileInputStream(FILE_INIT));
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
		
		//Create One user
		User newUser = new User();
		newUser.setUserId("spark");
		newUser.setName("Sung-wook Park");
		newUser.setEmail("spark@gmail.com");
		newUser.setRemindDays(4);
		newUser.setPassword("password");
		
		//Add User
		UserDAO.getInstance().addUser(newUser);
		
		//Assert
		ITable actualTable = dbUnitConnection.createDataSet().getTable("USER");
		ITable expectedTable = new FlatXmlDataSet(new FileInputStream(FILE_ADD)).getTable("USER");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	@Test(expected=UserExistException.class)
	public void addDuplicateUserTest() throws Exception {
		//Insert Init
		IDataSet dataset = new FlatXmlDataSet(new FileInputStream(FILE_INIT));
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
		
		//Create One USER
		User newUser = new User();
		newUser.setUserId("non");
		newUser.setName("VP");
		newUser.setEmail("vp@gmail.com");
		newUser.setRemindDays(4);
		newUser.setPassword("password");
		
		//Add User
		UserDAO.getInstance().addUser(newUser);
	}
	
	@Test
	public void editUser() throws Exception {
		//Insert Init
		IDataSet dataset = new FlatXmlDataSet(new FileInputStream(FILE_INIT));
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
		
		//Create One user
		User existingUser = new User();
		existingUser.setUserId("non");
		existingUser.setName("VP");
		existingUser.setEmail("vp@gmail.com");
		existingUser.setRemindDays(4);
		existingUser.setPassword("password");
		
		//Edit User
		UserDAO.getInstance().editUser(existingUser);
		
		//Assert
		ITable actualTable = dbUnitConnection.createDataSet().getTable("USER");
		ITable expectedTable = new FlatXmlDataSet(new FileInputStream(FILE_EDIT)).getTable("USER");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	@Test(expected=UserNotExistException.class)
	public void editUserNotExist() throws Exception {
		//Insert Init
		IDataSet dataset = new FlatXmlDataSet(new FileInputStream(FILE_INIT));
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
		
		//Create One user
		User existingUser = new User();
		existingUser.setUserId("asfasf");
		existingUser.setName("VP");
		existingUser.setEmail("vp@gmail.com");
		existingUser.setRemindDays(4);
		existingUser.setPassword("password");
		
		//Edit User
		UserDAO.getInstance().editUser(existingUser);

	}
	
	@Test
	public void deleteUser() throws Exception {
		//Insert Init
		IDataSet dataset = new FlatXmlDataSet(new FileInputStream(FILE_INIT));
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
		
		//Delete User
		UserDAO.getInstance().deleteUser("non");
		
		//Assert
		ITable actualTable = dbUnitConnection.createDataSet().getTable("USER");
		ITable expectedTable = new FlatXmlDataSet(new FileInputStream(FILE_DELETE)).getTable("USER");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	@Test(expected=UserNotExistException.class)
	public void deleteUserNotExist() throws Exception {
		//Insert Init
		IDataSet dataset = new FlatXmlDataSet(new FileInputStream(FILE_INIT));
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
		
		//Delete User
		UserDAO.getInstance().deleteUser("asdasdaf");
	}
	
	@Test
	public void getUserTest() throws Exception {
		//Insert Init
		IDataSet dataset = new FlatXmlDataSet(new FileInputStream(FILE_INIT));
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
		
		//Get User
		User user = UserDAO.getInstance().getUser("non");
		
		assertNotNull(user);
		assertEquals("non", user.getUserId());
	}
	
	@Test
	public void getAllUserTest() throws Exception {
		//Insert Init
		IDataSet dataset = new FlatXmlDataSet(new FileInputStream(FILE_INIT));
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
		
		//Get User
		List<User> users = UserDAO.getInstance().getAllUser();
		
		assertNotNull(users);
		assertEquals(2, users.size());
	}
}
