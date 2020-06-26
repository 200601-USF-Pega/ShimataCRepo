package test.java.dao.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import main.java.dao.impl.PersonDAOImpl;


public class PersonDAOImplTest {

	@Test
	public void testUpdatePersonPass() {
		PersonDAOImpl personDAOImpl = new PersonDAOImpl(); 
		boolean result = personDAOImpl.putUpdatePass("4242705023", "Awesome00!");
		Assert.assertEquals(true, result);
	}
	@Ignore @Test
	public void testisPhoneAndPassMatch() {
		PersonDAOImpl personDAOImpl = new PersonDAOImpl(); 
		boolean result = personDAOImpl.readGetPersonLogin("4242705023", "Awesome00!");
		Assert.assertEquals(true, result);
	}
}
