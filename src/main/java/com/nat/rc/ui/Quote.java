package com.nat.rc.ui;

public class Quote {
	
	private String quoteId;
	private String quote;
	private String author;
	
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
	
	public static Quote getMockObject() {
		Quote quote=new Quote();
		quote.setQuoteId("Q1");
		quote.setAuthor("MARK BOULTON");
		quote.setQuote("To push the boundaries, you need to know where the edges are.");
		return quote;
	}
}
