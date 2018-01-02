package com.aciw.foobar.utility;

/**
 * DO NOT MODIFY
 * 
 * @author Administrator
 *
 */
public class AnswerMessenger {
	private String answer;
	private int setId;

	public AnswerMessenger(String answer, int setId) {
		super();
		this.answer = answer;
		this.setId = setId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getSetId() {
		return setId;
	}

	public void setSetId(int setId) {
		this.setId = setId;
	}
}
