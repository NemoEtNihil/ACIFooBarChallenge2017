package com.aciw.foobar.utility;

import java.util.ArrayList;
import java.util.List;

/**
 * DO NOT MODIFY 
 * 
 * @author Administrator
 *
 */
public class AnswerMessenger {
	  private List<Integer> transactionIds = null;

	  public AnswerMessenger transactionIds(List<Integer> transactionIds) {
	    this.transactionIds = transactionIds;
	    return this;
	  }

	  public AnswerMessenger addTransactionIdsItem(Integer transactionIdsItem) {
	    if (this.transactionIds == null) {
	      this.transactionIds = new ArrayList<Integer>();
	    }
	    this.transactionIds.add(transactionIdsItem);
	    return this;
	  }


	  public List<Integer> getTransactionIds() {
	    return transactionIds;
	  }

	  public void setTransactionIds(List<Integer> transactionIds) {
	    this.transactionIds = transactionIds;
	  }
}
