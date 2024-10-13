package com.prashant.rateLimiter.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.prashant.rateLimiter.models.TokenBucket;
import com.prashant.rateLimiter.models.User;

public class TokenBucketFactory {
	
	private final Map<String,TokenBucket> buckets = new ConcurrentHashMap<String, TokenBucket>();
	private int capacity;
	private long refillRate;
	
	public TokenBucketFactory(int capacity, long refillRate){
		this.capacity = capacity;
		this.refillRate = refillRate;
	}
	
	// Get or Create a TokenBucket for the user
	public TokenBucket getBucketForUser(User user) {
		return this.buckets.computeIfAbsent(user.getUserId(), key -> new TokenBucket(this.capacity,this.refillRate));
	}
}
