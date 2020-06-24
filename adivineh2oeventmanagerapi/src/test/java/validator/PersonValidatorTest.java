package test.java.validator;

import org.junit.Assert;
import org.junit.Test;

import main.java.validator.PersonValidator;

public class PersonValidatorTest {
	
	@Test
	public void testuserNotValid() {
		//password and phone number should match our regex if not we want false
		//regex requires greater than 8, 1 special char, 1 upper, 1 number this should return falsebecause it doesn't fit
		//phone should be all digits else false
		Assert.assertEquals(false, PersonValidator.isValidLoginInfo("12","k2h23"));
		Assert.assertEquals(false, PersonValidator.isValidLoginInfo("4242705023","DccI209166"));
		Assert.assertEquals(false, PersonValidator.isValidLoginInfo("424111273y","HcEWqA"));
		Assert.assertEquals(false, PersonValidator.isValidLoginInfo("1234567890","12345678"));
		Assert.assertEquals(false, PersonValidator.isValidLoginInfo("ddrdecvfgt","&&&&$$!@"));
		Assert.assertEquals(false, PersonValidator.isValidLoginInfo("12","DDDD112d"));	
	}
	@Test
	public void testuserValid() {
		//password and phone number should match our regex if not we want false
		//regex requires greater than 8, 1 special char, 1 upper, 1 number this should return falsebecause it doesn't fit
		//phone should be all digits else false
		Assert.assertEquals(true, PersonValidator.isValidLoginInfo("11111112143","k2h!K823"));
	}
}
