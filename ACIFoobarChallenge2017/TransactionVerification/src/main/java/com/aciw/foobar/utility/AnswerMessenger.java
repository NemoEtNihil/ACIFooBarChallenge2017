package com.aciw.foobar.utility;

/**
 * DO NOT MODIFY
 * 
 * @author Administrator
 *
 */
public class AnswerMessenger {
	private String sourcePublicKey;
	private String sourceSignature;
	private int setId;
	
	public AnswerMessenger(String sourcePublicKey, String sourceSignature, int setId) {
		super();
		this.sourcePublicKey = sourcePublicKey;
		this.sourceSignature = sourceSignature;
		this.setId = setId;
	}
	
	public String getSourcePublicKey() {
		return sourcePublicKey;
	}
	public void setSourcePublicKey(String sourcePublicKey) {
		this.sourcePublicKey = sourcePublicKey;
	}
	public String getSourceSignature() {
		return sourceSignature;
	}
	public void setSourceSignature(String sourceSignature) {
		this.sourceSignature = sourceSignature;
	}

	public int getSetId() {
		return setId;
	}

	public void setSetId(int setId) {
		this.setId = setId;
	}
	
}
