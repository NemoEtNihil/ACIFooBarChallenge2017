package com.aciw.foobar.merkleTree;

import org.junit.Test;

import com.aciw.foobar.utility.RESTClient;

public class AppTest {
	@Test
	public void testSubmission() {
		RESTClient.setUsername("aqua");
		RESTClient.setPassword("rin");
		RESTClient.getInput(1);
		RESTClient.sendOutput("0dfb448a005ac59c0fcf4f33f9ecda60a6cd3bdd529eb7f464c5e2ddc2ba3e88", 1);
		RESTClient.sendOutput("answer set 2", 2);
		RESTClient.sendOutput("answer set 3", 3);

		RESTClient.setUsername("ruby");
		RESTClient.getInput(1);
		RESTClient.sendOutput("0dfb448a005ac59c0fcf4f33f9ecda60a6cd3bdd529eb7f464c5e2ddc2ba3e88", 1);
		RESTClient.sendOutput("answer set 2", 2);
		RESTClient.sendOutput("answer set 3", 3);

		RESTClient.setUsername("Sapphire");
		RESTClient.getInput(1);
		RESTClient.sendOutput("0dfb448a005ac59c0fcf4f33f9ecda60a6cd3bdd529eb7f464c5e2ddc2ba3e88", 1);
		RESTClient.sendOutput("answer set 2", 2);
		RESTClient.sendOutput("answer set 3", 3);

		RESTClient.setUsername("ian");
		RESTClient.setPassword("superadmin");
		RESTClient.getInput(1);
		RESTClient.sendOutput("0dfb448a005ac59c0fcf4f33f9ecda60a6cd3bdd529eb7f464c5e2ddc2ba3e88", 1);
		RESTClient.sendOutput("answer set 2", 2);
		RESTClient.sendOutput("answer set 3", 3);

		RESTClient.setUsername("rina");
		RESTClient.setPassword("normie");
		RESTClient.getInput(1);
		RESTClient.sendOutput("0dfb448a005ac59c0fcf4f33f9ecda60a6cd3bdd529eb7f464c5e2ddc2ba3e88", 1);
		RESTClient.sendOutput("answer set 2", 2);
		RESTClient.sendOutput("answer set 3", 3);
	}
}