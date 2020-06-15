package test.com.adivineh2oeventmanager.dao.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import main.com.adivineh2oeventmanager.dao.impl.PersonDAOImpl;


public class PersonDAOImplTest {

	@Ignore @Test
	public void testUpdatePersonPass() {
		PersonDAOImpl personDAOImpl = new PersonDAOImpl(); 
		boolean result = personDAOImpl.updatePersonPass("4242705023", "Dcsd209466!");
		Assert.assertEquals(true, result);
	}
	 @Test
	public void testisPhoneAndPassMatch() {
		PersonDAOImpl personDAOImpl = new PersonDAOImpl(); 
		boolean result = personDAOImpl.isPhoneAndPassMatch("4242705023", "Dcsd209466!");
		Assert.assertEquals(true, result);
	}
	
	
}
