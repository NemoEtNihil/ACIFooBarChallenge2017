package com.aciw.foobar.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * DO NOT MODIFY
 * 
 * @author Administrator
 *
 */
public class RESTClient {
	private static String username;
	private static String password;

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		RESTClient.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		RESTClient.password = password;
	}

	/**
	 * Submits your answer to the central server for verification
	 * 
	 * @param answer List of transactions that you believe adds up to the target amount.
	 */
	public static void sendOutput(List<Transaction> answer) {
		try {
			// prep the client
			List<Integer> idsToSend = new ArrayList<Integer>();
			AnswerMessenger messenger = new AnswerMessenger();
			Client client = ClientBuilder.newClient();
			String baseUrl = retrieveUrl();
			WebTarget webTarget = client.target(baseUrl + "challenges/CombiningValues/submit");

			answer.forEach(a -> idsToSend.add(a.getId()));
			messenger.setTransactionIds(idsToSend);

			// prepare request
			String authValue = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header("Authorization",
					"Basic " + authValue);

			// send out request with object and take in response
			Response response = invocationBuilder.post(Entity.entity(messenger, MediaType.APPLICATION_JSON));
			response.bufferEntity();

			String strResponse = response.readEntity(String.class);
			if (checkLoginPassed(strResponse)) {
				System.out.println(response.readEntity(String.class));
			} else {
				System.out.println("LOGIN FAILED: Username/password supplied was invalid.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static CvDataSet getInput(int from, int to) {
		// Link client to endpoint and prepare request invocation
		Client client = ClientBuilder.newClient();

		// Set the target and encode the supplied credentials to access the
		// service.
		String baseUrl = retrieveUrl();
		String query = String.format("challenges/CombiningValues?lower_limit=%d&upper_limit=%d", from, to);
		WebTarget webTarget = client.target(baseUrl + query);
		String authValue = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header("Authorization",
				"Basic " + authValue);

		// Execute get request to target and store response
		Response response = invocationBuilder.get();
		response.bufferEntity(); // buffer to allow for multiple readEntity
									// calls.

		// Verify the status of response

		String rspStr = (String) response.readEntity(String.class);

		if (checkLoginPassed(rspStr)) {
			CvDataSet result = new CvDataSet();
			// Marshal transaction list from response into appropriate object.
			ObjectMapper mapper = new ObjectMapper();

			try {
				result = mapper.readValue(rspStr, CvDataSet.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String msg = result.getMsg();
			if(msg != null){
				System.out.println(msg);
			}
			
			return result;
		} else {
			// Login failed
			System.out.println("LOGIN FAILED: Username/password supplied was invalid.");
			return null;
		}
	}

	private static String retrieveUrl() {
		String fileName = "../properties.txt";

		List<String> lines = new ArrayList<>();
		// read from the file
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			lines = stream.collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines.get(0).split("=")[1];
	}

	private static boolean checkLoginPassed(String serverResponse) {
		if (serverResponse.contains("\"status\":401")) {
			return false;
		}
		return true;
	}
}
