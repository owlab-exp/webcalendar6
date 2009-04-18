package edu.cmu.tsp6.server.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.cmu.tsp6.server.dao.EventDAOTest;
import edu.cmu.tsp6.server.dao.UserDAOTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  BirthdayEventServiceImplTest.class
})
public class AllServicesTest {

}
