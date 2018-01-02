package com.aciw.foobar.transactionVerification;

import com.aciw.foobar.utility.RESTClient;
import java.io.*;
import java.util.*;
import java.security.MessageDigest;

/**
 * 
 * @author Administrator
 *
 */
public class App 
{
	private static final String username = "Team Break";
	private static final String password = "E7WBVI3";
	
	public static String finalHashString;
	public static ArrayList<String> owner0KeysList = new ArrayList<String>();
	public static String owner1KeyString;
	public static ArrayList<String> signaturesList = new ArrayList<String>();
	
	public static File finalHash = new File("./files/1/FinalHash.txt");
	public static File owner0Keys = new File("./files/1/Owner0PublicKeys.txt");
	public static File owner1Key = new File("./files/1/Owner1PublicKey.txt");
	public static File signatures = new File("./files/1/Signatures.txt");
	private static byte Oxff;
	
    public static void main( String[] args ) throws IOException, Exception
    {
    	// set login credentials
    	RESTClient.setUsername(username);
    	RESTClient.setPassword(password);
    	
    	loadFiles();
    	
    	System.out.println("finalHash: " + finalHashString);
    	
    	boolean found = false;
    	String owner0Hash, owner1Hash, finalHash, signatureHash, baseHash = "", outputSignature = "", outputOwner0Key = "";
		owner1Hash = hashString(owner1KeyString);
		finalHash = finalHashString;
    	
    	for(int i = 0; i < signaturesList.size() && !found; i++)
    	{
    		for(int j = 0; j < owner0KeysList.size(); j++) 
    		{
    			signatureHash = hashString(signaturesList.get(i));
    			owner0Hash = hashString(owner0KeysList.get(j));
    			baseHash = signatureHash + owner0Hash + owner1Hash;
    			for(int k = 0; k < 2; k++)
    				baseHash = hashString(baseHash);
	    		if(baseHash.equals(finalHash))
	    		{
	    			outputSignature = signaturesList.get(i);
	    			outputOwner0Key = owner0KeysList.get(j);
	    			found = true;
	    			System.out.println("j: " + j + "\n i: " + j);
	    		}
    		}
    	}
    	System.out.println("baseHash: " + baseHash + "\n finalHash: " + finalHash);
    	
    	
    	
   	// Must read 4 files.
    	// FinalHash.txt, Owner0PublicKeys.txt, Owner1PublicKey.txt, Signatures.txt
    	// Store these values into appropriate variables to attempt the challenge.
    	
    	
    	// replace parameters with answer when ready to submit
    	RESTClient.sendOutput(outputOwner0Key, outputSignature);
    }
    
    public static String hashString(String input) throws Exception
	{
    	/*try
		{
			//Transforms String to bytes
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			
			//Transforms bytes to hex, and add constructs the String
			for (int i = 0; i <hash.length; i++)
			{
				String hex = Integer.toHexString(Oxff & hash[i]);
				if(hex.length() ==1)
					hexString.append('0');
				hexString.append(hex);
			}
			
		} catch (Exception ex)
		{
			throw new RuntimeException(ex);
		}*/
		MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(input.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
		return sb.toString();
	}
    
    public static void loadFiles() throws IOException
	{
    	
    	Scanner in;
    	String line;
    	
		in = new Scanner(finalHash);
		while(in.hasNext())
		{
			line =  in.nextLine();
			if(!(line.isEmpty() || line.equals("")))
				finalHashString = line;
		}
		in.close();

		in = new Scanner(owner0Keys);
		while(in.hasNext())
		{
			line =  in.nextLine();
			if(!(line.isEmpty() || line.equals("")))
				owner0KeysList.add(line);
		}
		in.close();
		
		in = new Scanner(owner1Key);
		while(in.hasNext())
		{
			line =  in.nextLine();
			if(!(line.isEmpty() || line.equals("")))
			owner1KeyString = line;
		}
		in.close();
		
		in = new Scanner(signatures);
		while(in.hasNext())
		{
			line =  in.nextLine();
			if(!(line.isEmpty() || line.equals("")))
				signaturesList.add(line);
		}
		in.close();
		
	}
}
