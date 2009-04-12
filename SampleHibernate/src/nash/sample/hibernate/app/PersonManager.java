package nash.sample.hibernate.app;

import java.util.Date;

import nash.sample.hibernate.domain.Person;
import nash.sample.hibernate.util.HibernateUtil;

import org.hibernate.Session;


/**
 * Very simple tutorial to test domain object & iBATIS
 * 
 * @author Ernest Lee
 *
 */
public class PersonManager {
	
	public static void main(String[] args) {
        
        
        
        PersonManager mgr = new PersonManager();
        
        Person person = new Person();
        person.setId(3);
        person.setFirstName("H-HJ");
        person.setLastName("Lee");
        person.setBirthDate(new Date());
        person.setHeightInMeters(170);
        person.setWeightInKilograms(77);
        
        // create and store
        Integer id = mgr.createAndStorePerson(person);
        
        // get
        person = mgr.getPersion(id);
        
        
        // update
        person.setFirstName("Ara");
        person.setHeightInMeters(130);
        person.setWeightInKilograms(40);
        
        // update
        mgr.updatePerson(person);
        
        // delete
        mgr.deletePerson(person);
        
                
        HibernateUtil.getSessionFactory().close();
    }

    public Integer createAndStorePerson(Person thePerson) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        Integer id = (Integer)session.save(thePerson);

        session.getTransaction().commit();
        
        return id;
    }
    
    public Person getPersion(Integer id) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        Person person = (Person) session.get(Person.class, id);

        session.getTransaction().commit();
        
        return person;
    }
    
    public void updatePerson(Person thePerson) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        session.saveOrUpdate(thePerson);

        session.getTransaction().commit();
    }
    
    public void deletePerson(Person thePerson) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        session.delete(thePerson);

        session.getTransaction().commit();
    }


}
