package test.java.dao.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import main.java.dao.impl.PersonDAOImpl;
import main.java.model.Person;


public class PersonDAOImplTest {

	@Test
	public void testUpdatePersonPass() {
		PersonDAOImpl personDAOImpl = new PersonDAOImpl(); 
		boolean result = personDAOImpl.updatePutPersonPass("4242705023", "Awesome00!", "Awesome00!");
		Assert.assertEquals(true, result);
	}
	@Test
	public void testisPhoneAndPassMatch() {
		PersonDAOImpl personDAOImpl = new PersonDAOImpl(); 
		Person result = personDAOImpl.readGetPersonLogin("4242705023", "Awesome00!");
		Assert.assertEquals("4242705023", result.getPhone());
//		Assert.assertFalse(result.toString().contains("Awesome00!"));

	}
}
