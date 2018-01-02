package com.aciw.foobar.transactionVerification;

import org.junit.Test;

import com.aciw.foobar.utility.RESTClient;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@Test
	public void testEndToEnd(){
		RESTClient.setUsername("aqua");
		RESTClient.setPassword("rin");
    	RESTClient.sendOutput("abcd", "efg");
	}
}
