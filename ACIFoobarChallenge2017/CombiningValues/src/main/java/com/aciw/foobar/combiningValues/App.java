package com.aciw.foobar.combiningValues;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;

import com.aciw.foobar.utility.CvDataSet;
import com.aciw.foobar.utility.RESTClient;
import com.aciw.foobar.utility.Transaction;

public class App 
{
	private static final String username = "Team Break";
	private static final String password = "E7WBVI3";
	
    public static void main( String[] args ) throws Exception
    {
    	RESTClient.setUsername(username);
    	RESTClient.setPassword(password);
    	
    	// modify the range here to your liking.
    	// different ranges queries different parts of the dataset.
    	/*CvDataSet data = RESTClient.getInput(500, 550);

    	if(data == null) {
    		return;
    	}*/
    	
    	// retrieves data pulled from the database.
    	
		CvDataSet data = RESTClient.getInput(50, 1050);
		ArrayList<Transaction> transactions = data.getTransactions();
		ArrayList<Transaction> outputTransactions = new ArrayList<Transaction>();
		
		ArrayList<Transaction> myTransactions = getMyTransactions(transactions);
		
    	double targetAmount = data.getTargetValue();
    	boolean sorted = false;
    	
        while(!sorted)
        {
        	sorted = true;
        	for(int i = 0; i < myTransactions.size()-1; i++)
        	{
        		if(myTransactions.get(i).getValue() > myTransactions.get(i+1).getValue())
        		{
        			Collections.swap(myTransactions, i, i+1);
        			sorted = false;
        		}
        	}
        }
        
        double sum = 0;
        int x = 20;
        
        do
        {
        	sum += myTransactions.get(x).getValue();
        	outputTransactions.add(myTransactions.get(x));
        	x++;
        }while(sum < targetAmount);
        
        //outputTransactions.remove(0);
       
        if(sum != targetAmount)
        	for(Transaction t : outputTransactions)
        	{
        		if(t.getValue() == sum-targetAmount)
        		{
        			outputTransactions.remove(t);
        			break;
        		}
        	}
        
        sum = 0;
        
        for(Transaction t : outputTransactions)
        	sum+=t.getValue();
        
        if(sum != targetAmount)
        	for(Transaction f : outputTransactions)
        	{
        		for(Transaction u : myTransactions)
            	{
            		if((sum + u.getValue()-f.getValue())== targetAmount)
            		{
            			outputTransactions.remove(f);
            			outputTransactions.add(u);
            			break;
            		}
            	}
        	}
        
        sum = 0;
        for(Transaction t : outputTransactions)
        	sum+=t.getValue();

        System.out.println("Sum: " + sum);

        System.out.println("No: " + outputTransactions.size());
        
   
    	// performs algorithm for challenge
    	
    	
    	System.out.println(myTransactions.size());
        /*ArrayList<Transaction>check = new ArrayList<Transaction>();
        ArrayList<Transaction>answer = new ArrayList<Transaction>();
       
        double sum =0;
 
        int i=0;
        while(sum != targetAmount)
        {
            sum = (double)Math.round(sum * 10d) / 10d;
            while(!sorted)
            {
            	sorted = true;
            	for(int f = 0; f < myTransactions.size()-1; f++)
            	{
            		if(myTransactions.get(f).getValue() > myTransactions.get(f+1).getValue())
            		{
            			Collections.swap(myTransactions, f, f+1);
            			sorted = false;
            		}
            	}
            }
            
            while(!sorted)
            {
            	sorted = true;
            	for(int f = 0; f < outputTransactions.size()-1; f++)
            	{
            		if(outputTransactions.get(f).getValue() > outputTransactions.get(f+1).getValue())
            		{
            			Collections.swap(outputTransactions, f, f+1);
            			sorted = false;
            		}
            	}
            }
            if(sum+myTransactions.get(i).getValue() <= targetAmount)
            {
                sum += myTransactions.get(i).getValue();
                outputTransactions.add(myTransactions.get(i));
                answer.add(myTransactions.get(i));
                myTransactions.remove(i);
                i=0;
            }
            else if(sum > targetAmount)
            {
                sum -= outputTransactions.get(outputTransactions.size()-i-1).getValue();
                myTransactions.add(outputTransactions.get(i));
                answer.remove(myTransactions.get(i));
                outputTransactions.remove(i);
                i=0;
            }
            else
                i++;
 
            if(i==myTransactions.size() || i == outputTransactions.size())
            {
                System.out.println("out");
                //if(check.contains(myTransactions.get(myTransactions.size() - 1 - i)))
                if(check.contains(myTransactions.get(i)))
                {
                    sum += myTransactions.get(i).getValue();
                    outputTransactions.add(myTransactions.get(i));
                    myTransactions.remove(i);
                    i = 0;
                    System.out.println("in");
                }
                else
                {
                    check.add(myTransactions.get(i));
                    i--;
                }
            }
 
        System.out.println(sum);
        }*/
    	
    	//ArrayList<Transaction> answer = getCombination(targetAmount, outputTransactions);
    	
		//Displaying your results:
		System.out.println("Array supplied: " + transactions);
		System.out.println("Your transactions: " + myTransactions);
		System.out.println("Target amount: " + targetAmount);
		System.out.println("Your result: " + outputTransactions);
		
		RESTClient.sendOutput(outputTransactions);
    }
    
    /**
     * Method to return the transactions which belong to you out of the set of transactions
     * you extracted from the database.
     * 
     * @param transactions
     * @return
     * @throws Exception 
     */
    public static ArrayList<Transaction> getMyTransactions(ArrayList<Transaction> transactions) throws Exception{
    	ArrayList<Transaction> myTransactions = new ArrayList<Transaction>();
    	//These v are needed to verify a transaction's ownership.
    	String signature = CvDataSet.SIGNATURE;
		String myKey = CvDataSet.TEAM_PUBLIC_KEY;
		String hashedTKey = hashString(myKey);
		
		System.out.println(transactions.size());
    	
    	for(Transaction t : transactions)
    	{
    		int id = t.getId();
    		String publicKey = t.getPublicKey();
    		double value = t.getValue();
    		String hash = t.getSecurityHash();
    		String hashedPKey = hashString(publicKey);
    		String finalHash = id + value + signature + hashedTKey + hashedPKey;
    		finalHash = hashString(hashString(finalHash));
    		
    		if(hash.equals(finalHash))
    			myTransactions.add(t);
    	}
    	return myTransactions;
    }
    
    
    
    public static String hashString(String input) throws Exception
	{
    	
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
    
	/**
	 * Your implementation goes here.
	 * 
	 * @param targetAmount
	 * @param transactions
	 * @return
	 */
	public static ArrayList<Transaction> getCombination(double targetAmount, ArrayList<Transaction> transactions){
		return new ArrayList<Transaction>();//initially returning empty arraylist
	}
}
