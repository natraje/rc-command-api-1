package com.nat.rc.model;

import java.io.Serializable;
import java.util.Date;

public class UserQuoteRatingId implements Serializable{
	private String userId;
	private String quoteId;
	private Date ratedTime;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public Date getRatedTime() {
		return ratedTime;
	}
	public void setRatedTime(Date ratedTime) {
		this.ratedTime = ratedTime;
	}
	
	
}
