package com.nat.rc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name="userrating")
@IdClass(UserQuoteRatingId.class)
public class UserRatingModel implements Serializable {
	
	@Id
	private String userId;
	@Id
	private String quoteId;
	private String quote;
	private String author;
	private int rating;
	@Id
	private Date ratedTime;
	//private int state=0;
	
	public UserRatingModel() {}
	
	
	public UserRatingModel(String userId, String quoteId, String quote, String author, int rating, Date ratedTime) {
		super();
		this.userId = userId;
		this.quoteId = quoteId;
		this.quote = quote;
		this.author = author;
		this.rating = rating;
		this.ratedTime = ratedTime;
	}
	
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
	public Date getRatedTime() {
		return ratedTime;
	}
	public void setRatedTime(Date ratedTime) {
		this.ratedTime = ratedTime;
	}



	
	
	
	
}
