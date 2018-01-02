package com.aciw.foobar.connectionTest;

import com.aciw.foobar.utility.RESTClient;

/**
 * Sample class to test connectivity with central server for challenges.
 * 
 * 
 * @author Administrator
 *
 */
public class App 
{
	private static final String username = "<enter_username_here>";
	private static final String password = "<enter_password_here>";
	

    public static void main( String[] args )
    {
    	// Set credentials
    	RESTClient.setUsername(username);
    	RESTClient.setPassword(password);
    	
    	// Retrieve inputs for the challenges
    	String input = RESTClient.getInput();
    	System.out.println(input);
    	
    	// When ready, submit the answer using this call.
    	// This sample submission echos the string you pass in so try using different messages to play with it.
    	RESTClient.sendOutput("Hello World!");
    }
}
