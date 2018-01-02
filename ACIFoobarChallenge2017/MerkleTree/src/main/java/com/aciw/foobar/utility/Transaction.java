package com.aciw.foobar.utility;
import java.io.Serializable;
import java.util.Date;

/**
 * DO NOT MODIFY
 * 
 * Transaction objects to act as models for the JSON data being fed in from
 * the web service. Parsed JSON strings for this challenge should be marshalled into 
 * this type.
 * 
 * Once stored a list of these objects can then be used to begin solving the challenge.
 * 
 * @author lodovicai
 *
 */
public class Transaction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sender;
	private String receiver;
	private double amount;
	private Date time;
	
	public Transaction() {
	
	}
	
	public Transaction(String sender, String receiver, double amount) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.time = new Date();
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Transaction [sender=" + sender + ", receiver=" + receiver + ", amount=" + amount + ", time=" + time
				+ "]";
	}
	
	
	
}
