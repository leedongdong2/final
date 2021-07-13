package service;

public interface EncryptionService {
	public String makeSalt();
	public String encryption(String salt,String pwd);
}
