package main.java.validator;

import java.util.regex.Pattern;

public abstract class PersonValidator {
	
	public static boolean isValidLoginInfo(String phone, String pass) {
	String phonereg = "^?\\d{10,11}$";
	String passreg = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^!?&+=])(?=\\S+$).{8,}";

	
	if (phone.equals(null) || pass.equals(null)) {
		return false;
	}

	return Pattern.matches(phonereg, phone) && Pattern.matches(passreg, pass);
		
	}

}
