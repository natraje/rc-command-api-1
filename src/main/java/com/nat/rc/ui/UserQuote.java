package com.nat.rc.ui;

import org.springframework.beans.BeanUtils;

import com.nat.rc.model.UserRatingModel;

public class UserQuote {
	
	private String userId;
	private String quoteId;
	private String quote;
	private String author;
	private int rating;
	
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
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public static UserRatingModel createUserRating(UserQuote quote) {
		UserRatingModel rating =new UserRatingModel();
		BeanUtils.copyProperties(quote, rating);
		rating.setRatedTime(new java.util.Date());
		return rating;
	}
}
