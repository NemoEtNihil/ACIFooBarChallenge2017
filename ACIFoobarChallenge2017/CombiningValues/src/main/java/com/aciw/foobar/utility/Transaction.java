package com.aciw.foobar.utility;

/**
 * DO NOT MODIFY
 * 
 * @author Administrator
 *
 */
public class Transaction {

	private int id;
	private double value;
	private String publicKey;
	private String securityHash;

	public Transaction(){
		super();
	}
	
	public Transaction(int id, double value, String publicKey, String securityHash){
		this.id = id;
		this.value = value;
		this.publicKey = publicKey;
		this.securityHash = securityHash;
	}

	public int getId(){
		return id;
	}

	public double getValue(){
		return value;
	}
	
	public String getPublicKey(){
		return publicKey;
	}
    
	public String getSecurityHash(){
        return securityHash;
	}

    public void setId(int id){
		this.id = id;
	}

	public void setValue(double value){
		this.value = value;
	}
	
	public void setSecurityHash(String securityHash){
		this.securityHash = securityHash;
	}
	
	public void setPublicKey (String publicKey){
	       this.publicKey = publicKey;
	}

	@Override
	public String toString(){
		return value + "";
	}
}
