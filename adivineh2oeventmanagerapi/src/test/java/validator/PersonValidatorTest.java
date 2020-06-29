package test.java.validator;

import org.junit.Assert;
import org.junit.Test;

import main.java.validator.PersonValidator;

public class PersonValidatorTest {
	
	@Test
	public void loginRegexLongEnough() {
		Assert.assertEquals(false, PersonValidator.isValidLoginInfo("12","k2h23"));
	}
	@Test
	public void loginRegexNoSpecialCharsInPass() {
		Assert.assertEquals(false, PersonValidator.isValidLoginInfo("4242705023","DccI209166"));
	}
	@Test
	public void loginRegexNoLettersInPhoneNumber() {
		Assert.assertEquals(false, PersonValidator.isValidLoginInfo("424111273y","HcEWq*!3A"));
	}
	@Test
	public void loginRegexPhoneTooLong() {
		Assert.assertEquals(false, PersonValidator.isValidLoginInfo("123456789022","HcEWq*!3A"));
	}
	@Test
	public void loginRegexPassMustHaveNumbers() {
		Assert.assertEquals(false, PersonValidator.isValidLoginInfo("4242705023","&&&&$$!@"));
	}
	@Test
	public void loginRegexPassMustHaveOnePlusUpperCaseLetters() {
		Assert.assertEquals(false, PersonValidator.isValidLoginInfo("4242705023","%*1124!fd"));

	}
	
	@Test
	public void loginRegexCorrect() {
		//password and phone number should match our regex if not we want false
		//regex requires greater than 8, 1 special char, 1 upper, 1 number this should return falsebecause it doesn't fit
		//phone should be all digits else false
		Assert.assertEquals(true, PersonValidator.isValidLoginInfo("11111112143","k2h!K823"));
	}
	
}
