package connectplus.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
	private String _password;
	private int _salt;

	public Password(String strPassword, int nSalt) {
		_password = strPassword;
		_salt = nSalt;
	}
	
	public String ComputeSaltedHash() {
		// ** Create Byte array of password string
		byte[] _secretBytes = {};
		try {
			_secretBytes = _password.getBytes("US-ASCII");
		} catch (UnsupportedEncodingException  uee){
			uee.printStackTrace();
		}
		
		// ** Create a new salt
		byte[] _saltBytes = new byte[4];
        _saltBytes[0] = (byte)(_salt >> 24);
        _saltBytes[1] = (byte)(_salt >> 16);
        _saltBytes[2] = (byte)(_salt >> 8);
        _saltBytes[3] = (byte)(_salt);
        
        // ** append the two arrays
        byte[] toHash = new byte[_secretBytes.length + _saltBytes.length];
        System.arraycopy(_secretBytes, 0, toHash, 0, _secretBytes.length);
        System.arraycopy(_saltBytes, 0, toHash, _secretBytes.length, _saltBytes.length);
        MessageDigest sha1 = null;
        try {
        	sha1 = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException nae){
        	nae.printStackTrace();
        }
        byte[] computedHash = sha1.digest(toHash);
		
        String genHash = "";
        try{
        	genHash = new String(computedHash, "US-ASCII");
        } catch ( UnsupportedEncodingException uee){
        	uee.printStackTrace();
        }
        
        // ** Convert out of range characters to `?`
        genHash = genHash.replaceAll(String.valueOf((char)65533), "?");

		return genHash;
	}
	
	public boolean isPasswordValid(String originalPwd){
		String hash = ComputeSaltedHash();
		return hash.equals(originalPwd);
	}
	
	
	public static void main(String[] args) {
		Password pwd = new Password("123", -1679505561);
		String hash = pwd.ComputeSaltedHash();
		System.out.println("Hash: [" + hash + "]");
	}
	
	/*
	public string GenerateSaltedHash(string __password, int __salt)
	        {
	            // Create Byte array of password string
	            ASCIIEncoding encoder = new ASCIIEncoding();
	            Byte[] _secretBytes = encoder.GetBytes(__password);
	            
	            // Create a new salt
	            Byte[] __saltBytes = new Byte[4];
	            __saltBytes[0] = (byte)(__salt >> 24);
	            __saltBytes[1] = (byte)(__salt >> 16);
	            __saltBytes[2] = (byte)(__salt >> 8);
	            __saltBytes[3] = (byte)(__salt);

	            // append the two arrays
	            Byte[] toHash = new Byte[_secretBytes.Length + __saltBytes.Length];
	            Array.Copy(_secretBytes, 0, toHash, 0, _secretBytes.Length);
	            Array.Copy(__saltBytes, 0, toHash, _secretBytes.Length, __saltBytes.Length);

	            SHA1 sha1 = SHA1.Create();
	            Byte[] computedHash = sha1.ComputeHash(toHash);

	            return encoder.GetString(computedHash);
	            
	        }
	*/
}
