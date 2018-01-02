package com.aciw.foobar.merkleTree;

import java.util.ArrayList;

import com.aciw.foobar.utility.Node;
import com.aciw.foobar.utility.RESTClient;
import com.aciw.foobar.utility.Transaction;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Administrator
 *
 */
public class App {
	private static final String username = "Team Break";
	private static final String password = "E7WBVI3";

	public static void main(String[] args) throws Exception {
		// set login credentials
		RESTClient.setUsername(username);
		RESTClient.setPassword(password);
		
		ArrayList<Transaction> input1 = RESTClient.getInput(1);
		ArrayList<Transaction> input2 = RESTClient.getInput(2);
		
		System.out.println(input1.size());
		System.out.println(input2.size());
		
		RESTClient.sendOutput(hashList(input1),1);
		RESTClient.sendOutput(hashList(input2),2);
	}
	
	public static String hashList(ArrayList<Transaction> list) throws Exception
	{
		ArrayList<Node> nodeList = new ArrayList<Node>();
		ArrayList<Node> temp = new ArrayList<Node>();
		String output = "";
		
		for(Transaction t : list)
		{
			Node n = new Node();
			n.setHash(hashing(t.toString()));
			nodeList.add(n);
		}
		while(nodeList.size() != 1)
		{	
			if(nodeList.size()%2 == 0 )
			{
				for(int i = 0; i < nodeList.size()/2; i++)
				{
					Node n = new Node();
					n.setHash(hashing(nodeList.get(i*2).getHash() + nodeList.get(i*2+1).getHash()));
					temp.add(n);
				}
				nodeList.clear();
				nodeList.addAll(temp);
				temp.clear();
			}
			else
			{
				for(int i = 0; i < (nodeList.size()-1)/2; i++)
				{
					Node n = new Node();
					n.setHash(hashing(nodeList.get(i*2).getHash() + nodeList.get(i*2+1).getHash()));
					temp.add(n);
				}
				temp.add(nodeList.get(nodeList.size()-1));
				nodeList.clear();
				nodeList.addAll(temp);
				temp.clear();
			}
		}
		
		return nodeList.get(0).getHash();
	}
	
	public static String hashing(String in) throws NoSuchAlgorithmException
    {
 
		MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(in.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
		return sb.toString();
    }
}
