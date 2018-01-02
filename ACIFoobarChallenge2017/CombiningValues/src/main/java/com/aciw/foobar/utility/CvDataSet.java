package com.aciw.foobar.utility;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * DO NOT MODIFY
 * 
 * @author Administrator
 *
 */
public class CvDataSet implements Serializable{
		private static final long serialVersionUID = 1L;
		private double targetValue;
		private ArrayList<Transaction> transactions;
		private String msg;
		public static final String SIGNATURE = "https://www.aciworldwide.com";
		public static final String  TEAM_PUBLIC_KEY = "SX73U4RFI7Z8N9AB";


		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public double getTargetValue(){
			return targetValue;
		}

		public ArrayList<Transaction> getTransactions(){
			return transactions;
		}

		public void setTransactions(ArrayList<Transaction> transactions){
			this.transactions = transactions;
		}

		public void setTargetValue(double targetAmount){
			this.targetValue = targetAmount;
		}

		@Override
		public String toString(){
			String output = "" + targetValue + "\n\n";
			output += transactions.toString();

			return output; 
		}
}

