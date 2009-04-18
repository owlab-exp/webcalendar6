package edu.cmu.tsp6.server;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.cmu.tsp6.server.dao.AllDaoTest;
import edu.cmu.tsp6.server.service.AllServicesTest;
import edu.cmu.tsp6.server.service.BirthdayEventServiceImplTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AllServicesTest.class,
	AllDaoTest.class
})
public class AllTest {

}
