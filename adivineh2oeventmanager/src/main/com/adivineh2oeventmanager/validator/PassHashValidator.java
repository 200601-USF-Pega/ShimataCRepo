package main.com.adivineh2oeventmanager.validator;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class PassHashValidator {
	
	public static final int SALT_LENGTH = 20;
	public static final int PBE_ITERATION_COUNT = 200; // 1024;

	private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

	
	public static final byte[] hashAPass(String pass_to_hash) {
		
		System.setProperty("jdk.crypto.KeyAgreement.legacyKDF","true");
		
		byte[] iv = "1234567890asdfgh".getBytes();

		byte[] encryptedText = null;

		try {

			Key key = buildKey(pass_to_hash);


			Cipher encryptionCipher = Cipher.getInstance(CIPHER_ALGORITHM);


			IvParameterSpec ivspec = new IvParameterSpec(iv);

			encryptionCipher.init(Cipher.ENCRYPT_MODE, key, ivspec);

			encryptedText = encryptionCipher.doFinal(pass_to_hash.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return encryptedText;
	}
	
	private static Key buildKey(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		  MessageDigest digester = MessageDigest.getInstance("SHA-256");
		  digester.update(password.getBytes("UTF-8"));
		  byte[] key = digester.digest();
		  SecretKeySpec spec = new SecretKeySpec(key, "AES");
		  return spec;
		}


	public static final boolean isHashPassMatch(byte[] hashed_pass, String input_pass) {
		
		System.out.println(hashed_pass);

		byte[] iv = "1234567890asdfgh".getBytes();

		byte[] salt = "dfghjklpoiuytgftgyhj".getBytes();

		String cleartext = "";

		try {

			PBEKeySpec pbeKeySpec = new PBEKeySpec(input_pass.toCharArray(), salt, PBE_ITERATION_COUNT, 256);
			
			Key key = buildKey(input_pass);


			Cipher decryptionCipher = Cipher.getInstance(CIPHER_ALGORITHM);

			IvParameterSpec ivspec = new IvParameterSpec(iv);

			decryptionCipher.init(Cipher.DECRYPT_MODE, key, ivspec);

			byte[] decryptedText = decryptionCipher.doFinal(hashed_pass);

			cleartext = new String(decryptedText);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cleartext.equals(input_pass);
	}

}
