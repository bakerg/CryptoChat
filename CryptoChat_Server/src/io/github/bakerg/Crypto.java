package io.github.bakerg;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Crypto {
	private static MessageDigest md;
	final protected static char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
	public static String md5(String input){
		byte[] outbytes = null;
		try {
			md = MessageDigest.getInstance("MD5");
			outbytes = md.digest(input.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
		return bytesToHex(outbytes);
	}
	
	
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    int v;
	    for ( int j = 0; j < bytes.length; j++ ) {
	        v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public static byte[] RSAEncrypt(byte[] object){
		PublicKey key = readPublicKey();
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(object);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {			
			e.printStackTrace();
		} catch (InvalidKeyException e) {			
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {			
			e.printStackTrace();
		} catch (BadPaddingException e) {			
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] RSADecrypt(byte[] object){
		PrivateKey key = readPrivateKey();
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(object);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {			
			e.printStackTrace();
		} catch (InvalidKeyException e) {			
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {			
			e.printStackTrace();
		} catch (BadPaddingException e) {			
			e.printStackTrace();
		}
		return null;
	}
	
	public static PrivateKey readPrivateKey() {
		try {
			FileInputStream in = new FileInputStream("private.key");
			ObjectInputStream objin = new ObjectInputStream(new BufferedInputStream(in));
			BigInteger m = (BigInteger) objin.readObject();
			BigInteger e = (BigInteger) objin.readObject();
			RSAPrivateKeySpec spec = new RSAPrivateKeySpec(m, e);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			return factory.generatePrivate(spec);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {		
			e.printStackTrace();
		}
		return null;
	}


	public static byte[] serialize(Object object){
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(b);
			out.writeObject(object);
			b.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b.toByteArray();
	}
	
	public static Object deserialize(byte[] bytes){
		ByteArrayInputStream b = new ByteArrayInputStream(bytes);
		try {
			ObjectInputStream in = new ObjectInputStream(b);
			Object obj = in.readObject();
			in.close();
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static PublicKey readPublicKey(){
		try {
			FileInputStream in = new FileInputStream("public.key");
			ObjectInputStream objin = new ObjectInputStream(new BufferedInputStream(in));
			BigInteger m = (BigInteger) objin.readObject();
			BigInteger e = (BigInteger) objin.readObject();
			RSAPublicKeySpec spec = new RSAPublicKeySpec(m, e);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			return factory.generatePublic(spec);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {		
			e.printStackTrace();
		}
		return null;
	}
}