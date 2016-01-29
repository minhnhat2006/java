package com.sharpinu.util;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

public class CryptoHelper {

	private static final String UNICODE_FORMAT = "UTF8";
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	private static String SHARP_IN_U_ENCRYPTION_KEY = "sHarPiNu.CoM_KEY_1234567";

	public static String encrypt(String unencryptedString) {
		try {
			String encryptedString = null;
			byte[] arrayBytes = SHARP_IN_U_ENCRYPTION_KEY.getBytes(UNICODE_FORMAT);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
			Cipher cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
			SecretKey key = skf.generateSecret(ks);
			
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = cipher.doFinal(plainText);
			encryptedString = new String(Base64.encodeBase64URLSafe(encryptedText));
			
			return encryptedString;
		} catch (Exception e) {
			throw new RuntimeException("Failed to encrypt", e);
		}
	}

	public static String decrypt(String encryptedString) {
		try {
			String decryptedText = null;
			byte[] arrayBytes = SHARP_IN_U_ENCRYPTION_KEY.getBytes(UNICODE_FORMAT);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
			Cipher cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
			SecretKey key = skf.generateSecret(ks);
			
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptedText = Base64.decodeBase64(encryptedString);
			byte[] plainText = cipher.doFinal(encryptedText);
			decryptedText = new String(plainText);
			
			return decryptedText;
		} catch (Exception e) {
			throw new RuntimeException("Failed to decrypt", e);
		}
	}

	public static void main(String args[]) throws Exception {

		String target = "6";
		String encrypted = CryptoHelper.encrypt(target);
		String decrypted = CryptoHelper.decrypt(encrypted);

		System.out.println("String To Encrypt: " + target);
		System.out.println("Encrypted String:" + encrypted);
		System.out.println("Decrypted String:" + decrypted);

	}

}
