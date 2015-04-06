

import java.security.*;

import javax.crypto.*;

public class Inf1416_Trab1_Grupo3 
{
	 public static void main (String[] args) throws Exception 
	 {
		// check args and get plaintext
		if (args.length !=1) 
		{
		  System.err.println("Usage: no arg found");
		  System.exit(1);
		}
		
		// print plain text
		System.out.println( "\nOriginal Plain Text: " );
		System.out.println( args[0] );
		
		// get plain text
		byte[] plainText = args[0].getBytes("UTF8");
		
		//############ KEY PAIR ########################################
		
		// generate RSA key pair
		System.out.println( "\nStart generating RSA key pair" );
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		KeyPair key = keyGen.generateKeyPair();
		System.out.println( "Finish generating RSA key pair" );
	    	    
	    //############ KEY PAIR ########################################
		
		System.out.println( "\n\n###########GENERATE DIGITAL SIGNATURE###########" );
		
		// Generate Digital Signature
		byte[] digitalSignature = GenerateDigitalSignature(plainText, key.getPrivate());
		
		System.out.println( "DigitalSignature (hex):" );
	    System.out.println( GetHexadecimal(digitalSignature) );
	    
		System.out.println( "\n################################################" );
	    
	    System.out.println("\n\n\n###########VERIFY DIGITAL SIGNATURE###########" );
	    	    
	    boolean result = VerifyDigitalSignature(plainText, digitalSignature, key.getPublic());
	    
	    System.out.println( "\n################################################\n\n" );
	    
	    if(result)
	    {
	    	System.out.println("\nGreat, Plain Text integrity is OK !" );
	    }
	    else
	    {
	    	System.out.println("\nFAIL, Plain Text integrity compromised !" );
	    }

	 }
	 
	 public static boolean VerifyDigitalSignature(byte[] plainText, byte[] digitalSignature, PublicKey pubKey) throws Exception
	 {
		 //############ DIGEST ##########################################
	    
		 System.out.println( "\nGenerating Digest Received for Plain Text" );
	    
		 // get a message digest object using the MD5 algorithm
		 MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	    
		 // calculate the digest and print it out
		 messageDigest.update(plainText);
		 byte [] digest = messageDigest.digest();
	    
		 // print digest in hexadecimal
		 System.out.println( "Finish Digest Generation(hex): " );
		 System.out.println( GetHexadecimal(digest) );
	    
		 //############ DIGEST ##########################################
	    
	    
		 //############ CIPHER ##########################################
	    
		 // define RSA cipher object
		 Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	    
		 // Decrypt using private key
		 cipher.init(Cipher.DECRYPT_MODE, pubKey);
		 System.out.println( "\nStart DS decryption with public key" );
		 byte[] originalDigest = cipher.doFinal(digitalSignature);
		 System.out.println( "Finish DS decryption." );
		 System.out.println( "Original Digest(hex): " );
		 System.out.println( GetHexadecimal(originalDigest) );
	    
		 //############ CIPHER ##########################################
		 
		 // Sanity check
		 if(digest.length != originalDigest.length)
			 return false;
		 
		 for(int i = 0; i < digest.length; i++) 
		 {
			 if(digest[i] != originalDigest[i])
				 return false;
		 }
		 
		 return true;
		 
	 }
	 
	 public static byte[] GenerateDigitalSignature(byte[] plainText, PrivateKey privKey) throws Exception
	 {
		 //############ DIGEST ##########################################
	    
	    System.out.println( "\nGenerating Digest for Plain Text" );
	    
	    // get a message digest object using the MD5 algorithm
	    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	    
	    // calculate the digest and print it out
	    messageDigest.update(plainText);
	    byte [] digest = messageDigest.digest();
	    
	    // print digest in hexadecimal
	    System.out.println( "Finish Digest Generation(hex): " );
	    System.out.println( GetHexadecimal(digest) );
	    
	    //############ DIGEST ##########################################
	    
	    
	    //############ CIPHER ##########################################
	    
	    // define RSA cipher object
	    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	    
	    // encrypt using public key
	    cipher.init(Cipher.ENCRYPT_MODE, privKey);
	    System.out.println( "\nStart digest encryption with private key" );
	    byte[] cipherText = cipher.doFinal(digest);
	    System.out.println( "Finish digest encryption" );
	    
	    //############ CIPHER ##########################################
	    
	    return cipherText;
	 }
	 
	 public static String GetHexadecimal(byte [] info)
	 {
		// convert to hexadecimal
	    StringBuffer buf = new StringBuffer();
	    for(int i = 0; i < info.length; i++) {
	       String hex = Integer.toHexString(0x0100 + (info[i] & 0x00FF)).substring(1);
	       buf.append((hex.length() < 2 ? "0" : "") + hex);
	    }
	    return buf.toString();
	 }
}
