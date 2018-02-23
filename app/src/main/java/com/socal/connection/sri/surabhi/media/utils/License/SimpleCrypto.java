package com.socal.connection.sri.surabhi.media.utils.License;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SimpleCrypto
{

	public static String LicenseEncrypt(String text, String key) throws SimpleCryptoException {
		try {
			byte[] keyBytes = new byte[16];
			byte[] b = key.getBytes("UTF-8");
			int len = b.length;

			if (len > keyBytes.length)
				len = keyBytes.length;
			System.arraycopy(b, 0, keyBytes, 0, len);

			return LicenseEncrypt(text, keyBytes);
		} catch (UnsupportedEncodingException e) {
			throw new SimpleCryptoException("Encrypt failed. UTF-8 Encoding not supported", e);
		}
	}
	
	
	public static String LicenseEncrypt(String text, byte[] key) throws SimpleCryptoException
	{
		byte[] results;
		byte[] iv = new byte[16];
		byte[] returnValue;
		try
		{
			SecureRandom ivMaker = new SecureRandom();
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			
			iv = new byte[16];
			ivMaker.nextBytes(iv);
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

			results = cipher.doFinal(text.getBytes("UTF-8"));
		}
		catch (NoSuchPaddingException e)
		{
			throw new SimpleCryptoException(
					"Encrypt failed. Cipher.getInstance(). AES/CBC/PKCS5Padding scheme not support by any provider", e);
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new SimpleCryptoException(
					"Encrypt failed. Cipher.getInstance(). AES/CBC/PKCS5Padding scheme not available", e);
		}
		catch (UnsupportedEncodingException e)
		{
			throw new SimpleCryptoException("Encrypt failed. UTF-8 Encoding not supported", e);
		}
		catch (InvalidKeyException e)
		{
			throw new SimpleCryptoException("Encrypt failed. Invalid Key", e);
		}
		catch (InvalidAlgorithmParameterException e)
		{
			throw new SimpleCryptoException("Encrypt failed. Invalid Key Algorithm", e);
		}
		catch (IllegalBlockSizeException e)
		{
			throw new SimpleCryptoException(
					"Decrypt failed. Total input length of the data processed by this cipher is not a multiple of block size",
					e);
		}
		catch (BadPaddingException e)
		{
			throw new SimpleCryptoException("Encrypt failed. Bad padding detected", e);
		}
		
		returnValue = new byte[iv.length + results.length];
		System.arraycopy(iv, 0, returnValue, 0, iv.length);
		System.arraycopy(results, 0, returnValue, 16, results.length);
		return Base64.encodeToString(returnValue, true);
	}

	public static String LicenseDecrypt(String text, String key) throws SimpleCryptoException
	{
		try
		{
			byte[] keyBytes = new byte[16];
			byte[] b = key.getBytes("UTF-8");
			int len = b.length;
			if (len > keyBytes.length)
				len = keyBytes.length;
			System.arraycopy(b, 0, keyBytes, 0, len);

			return LicenseDecrypt(text, keyBytes);
		}
		catch (UnsupportedEncodingException e)
		{
			throw new SimpleCryptoException("Decrypt failed. UTF-8 Encoding not supported", e);
		}
	}

	public static String LicenseDecrypt(String text, byte[] key) throws SimpleCryptoException
	{
		byte[] fullString;
		byte[] results;
		byte[] iv;
		String returnValue;
		try
		{
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

			fullString=Base64.decode(text);
			iv = new byte[16];
			System.arraycopy(fullString, 0, iv, 0, iv.length);
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
			
			results = new byte[fullString.length-iv.length];
			System.arraycopy(fullString, iv.length, results, 0, results.length);
			results = cipher.doFinal(results);
			returnValue = new String(results, "UTF-8");
		}
		catch (NoSuchPaddingException e)
		{
			throw new SimpleCryptoException(
					"Decrypt failed. Cipher.getInstance(). AES/CBC/PKCS5Padding scheme not support by any provider", e);
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new SimpleCryptoException(
					"Decrypt failed. Cipher.getInstance(). AES/CBC/PKCS5Padding scheme not available", e);
		}
		catch (UnsupportedEncodingException e)
		{
			throw new SimpleCryptoException("Decrypt failed. UTF-8 Encoding not supported", e);
		}
		catch (InvalidKeyException e)
		{
			throw new SimpleCryptoException("Decrypt failed. Invalid Key", e);
		}
		catch (InvalidAlgorithmParameterException e)
		{
			throw new SimpleCryptoException("Decrypt failed. Invalid Key Algorithm", e);
		}
		catch (IllegalBlockSizeException e)
		{
			throw new SimpleCryptoException(
					"Decrypt failed. Total input length of the data processed by this cipher is not a multiple of block size",
					e);
		}
		catch (BadPaddingException e)
		{
			throw new SimpleCryptoException("Decrypt failed. Bad padding detected", e);
		}
		return returnValue;

	}
}
