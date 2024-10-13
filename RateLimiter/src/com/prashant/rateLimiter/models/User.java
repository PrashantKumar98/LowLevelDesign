package com.prashant.rateLimiter.models;

public class User {
	private String userId;
	
	public User(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return this.userId;
	}
}
