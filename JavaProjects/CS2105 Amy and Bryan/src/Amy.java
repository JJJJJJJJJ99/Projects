// Author: Jingjing Wang

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;

/************************************************
  * This skeleton program is prepared for weak  *
  * and average students.                       *
  * If you are very strong in programming. DIY! *
  * Feel free to modify this program.           *
  ***********************************************/

// Alice knows Bob's public key
// Alice sends Bob session (AES) key
// Alice receives messages from Bob, decrypts and saves them to file

class Amy {  // Alice is a TCP client
    
    String bobIP;  // ip address of Bob
    int bobPort;   // port Bob listens to
    Socket connectionSkt;  // socket used to talk to Bob
    private ObjectOutputStream toBryan;   // to send session key to Bob
    private ObjectInputStream fromBryan;  // to read encrypted messages from Bob
    private Crypto crypto;        // object for encryption and decryption
    // file to store received and decrypted messages
    public static final String MESSAGE_FILE = "msgs.txt";
    private SealedObject encryptedMessage = null;
    private PrintWriter output = null;
    private byte[] sealedDigest = null;
    private PublicKey publicRSAkey = null;
    private PublicKey berisignPublickey = null;
    public static final String PUBLIC_KEY_FILE = "berisign.pub";
    
    public static void main(String[] args) {
        
        // Check if the number of command line argument is 2
        if (args.length != 2) {
            System.err.println("Usage: java Amy BryanIP BryanPort");
            System.exit(1);
        }
        
        new Amy(args[0], args[1]);
    }
    
    // Constructor
    public Amy(String ipStr, String portStr) {
        
        this.crypto = new Crypto();
        this.bobIP = ipStr;
        this.bobPort = Integer.parseInt(portStr);
        try {
			this.output = new PrintWriter("msgs.txt");
			 System.out.println("********msgs.txt file has been created!******");
			 System.out.println();
		} catch (FileNotFoundException e1) {
			System.out.println("FileNotFoundException!");
            System.exit(1);
		}
		
        try {
			this.connectionSkt = new Socket(bobIP, bobPort);
			this.toBryan = new ObjectOutputStream(this.connectionSkt.getOutputStream());
			this.fromBryan = new ObjectInputStream(this.connectionSkt.getInputStream());
			System.out.println("Connection to Bob has been created");
			
		} catch (IOException e) {
			System.out.println("Error connecting to Bob!");
            System.exit(1);
		}
        
		getPublicKey();
		readPublicKey();
		check();
        sendSessionKey();
        receiveMessages();           
    }
    
    public void readPublicKey() {
        // key is stored as an object and need to be read using ObjectInputStream.
        // See how Bob read his private key as an example.
    	ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
			berisignPublickey = (PublicKey) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Error reading berisign public key!");
            System.exit(1);
		} 
		
		System.out.println("Public key read from file " + PUBLIC_KEY_FILE);
    }
    
    // This method get public key and digest from Bryan
    public void getPublicKey(){
    	try {
			publicRSAkey = (PublicKey) fromBryan.readObject();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Error getting RSA key from Bob!");
            System.exit(1);
		}
    	
    	// SealedDigest --> encryptedSignature --> which is MD5 Sum
    	// This sealed digest is encrypted by Berisign's public key
    	// Compare this with the bytearray calculated by Amy
    	try {
			sealedDigest = (byte[]) fromBryan.readObject();
			System.out.println(sealedDigest + "actual sealed digest.");
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Error getting MD5 digest from Bob!");
            System.exit(1);
		}
    	
    }
    
    public void check(){
    	MessageDigest md5 = null;
		try {
			md5 = md5.getInstance("MD5");
		} catch (NoSuchAlgorithmException e2) {
			System.out.println("Error initializing message digest md5!");
			System.exit(1);
		}
    	String s = "bryan";
    	byte[] b = null;
		try {
			b = s.getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
    	byte[] byteKey = publicRSAkey.getEncoded();
    	
    	md5.update(b);
    	md5.update(byteKey);
    	
    	// This is the MD5 Sum that will be compared with sealedDigest.
    	byte[] digest = md5.digest();
    	
    	
    	//Encrypt md5 digest
    	Cipher cipher  = null;	
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e2) {
			System.out.println("Error initialize cipher 1.");
			System.exit(1);
		}
				
					
		try {
			cipher.init(Cipher.DECRYPT_MODE, berisignPublickey);
		} catch (InvalidKeyException e1) {
			System.out.println("Error initialize cipher 2.");
			System.exit(1);
		}
		
		// Encrypt the digest caculated by Amy with Berisign's public key.
		byte[] sealedDigestToCompare = null;
		try {
			sealedDigestToCompare = cipher.doFinal(sealedDigest);
			System.out.println(digest + " sealed digest to compare");
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			System.out.println("Error creating SealedObject.");
			System.exit(1);
		}
			
		// Compare the encrypted digest caculated by Amy with the actually encrypted digest.	
		if(!Arrays.equals(sealedDigestToCompare, digest)){
			System.out.println("Error:MD5 signature does not match");
			System.exit(1);
		}
		
    }
		
    
    
    // Send session key to Bob
    public void sendSessionKey() {
    	try {
			toBryan.writeObject(crypto.getSessionKey());
			System.out.println("Session key has been initialized!");
			
		} catch (IOException e) {
			System.out.println("Error sending AES key to Bob!");
            System.exit(1);
		}
    }
    
    // Receive messages one by one from Bob, decrypt and write to file
    public void receiveMessages() {
    	try {
	    	for (int i = 0; i < 10; i++) {
	    		
				encryptedMessage = (SealedObject) fromBryan.readObject();	
	            String toPrint = this.crypto.decryptMsg(encryptedMessage);
	            System.out.println(toPrint);
	            output.println(toPrint);
	            output.flush();
	        }
	    	System.out.println("Message has been receieved!");
	    	
    	} catch (ClassNotFoundException | IOException e) {
    		System.out.println("Error receiving message from Bob!");
            System.exit(1);
		} 
    	
    	output.close();
    }
    
    
    
    /*****************/
    /** inner class **/
    /*****************/
    class Crypto {
        
        private SecretKey sessionKey;
        
        // Constructor
        public Crypto() {
            initSessionKey();
        }
        
        
        // Generate a session key
        public void initSessionKey() {
            // suggested AES key length is 128 bits
        	try {
	        	KeyGenerator keygen = KeyGenerator.getInstance("AES");				
	            keygen.init(128);
	            sessionKey = keygen.generateKey();
        	} catch (NoSuchAlgorithmException e) {
        		System.out.println("Error initializing session key!");
	            System.exit(1);
			}
        	
        	System.out.println("Session key has been initialized!");
        }
        
        // Seal session key with RSA public key in a SealedObject and return
        public SealedObject getSessionKey() {
   
        	SealedObject sealedSessionKey = null;
            try {
            	Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            	cipher.init(Cipher.ENCRYPT_MODE, publicRSAkey);
				sealedSessionKey = new SealedObject(sessionKey.getEncoded(), cipher);
				
			} catch (NoSuchPaddingException | NoSuchAlgorithmException 
					|IllegalBlockSizeException | InvalidKeyException | IOException e) {
				System.out.println("Error getting session key!");
	            System.exit(1);
			}   
 
            System.out.println("Got session key");
            
			return sealedSessionKey;
        }
        
        // Decrypt and extract a message from SealedObject
        public String decryptMsg(SealedObject encryptedMsgObj) {
            
            String plainText = null;
            
            // Alice and Bob use the same AES key/transformation
            Cipher cipher;
			try {
				cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.DECRYPT_MODE, sessionKey);
				plainText = (String) encryptedMsgObj.getObject(cipher);
			} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
					ClassNotFoundException | IllegalBlockSizeException | BadPaddingException | IOException e) {
				System.out.println("Error decrypting message!");
	            System.exit(1);
			}
			
			System.out.println("Decrypted Message!");
            
            return plainText;
        }
    }
}