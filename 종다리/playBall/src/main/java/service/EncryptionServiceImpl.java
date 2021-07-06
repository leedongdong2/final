package service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Service;


@Service
public class EncryptionServiceImpl implements EncryptionService {
	
  public String makeSalt() {
	  SecureRandom random = null;
	try {
		random = SecureRandom.getInstance("SHA1PRNG");
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	  byte[] bytes = new byte[16];
	  random.nextBytes(bytes);
	  String salt = new String(Base64.getEncoder().encode(bytes));
	  
	  return salt;
  }
  
  public String encryption(String salt,String pwd) {
	  MessageDigest md = null;
	try {
		md = MessageDigest.getInstance("SHA-256");
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  md.update(salt.getBytes());
	  md.update(pwd.getBytes());
	  String hex = String.format("%064x", new BigInteger(1, md.digest()));
	  return hex;
  }
}
