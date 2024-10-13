package com.prashant.rateLimiter.sercvices;

import com.prashant.rateLimiter.factory.GlobalTokenBucketFactory;
import com.prashant.rateLimiter.factory.TokenBucketFactory;
import com.prashant.rateLimiter.models.TokenBucket;
import com.prashant.rateLimiter.models.User;

public class RateLimiterServiceImpl implements RateLimiterService {
	
	private static RateLimiterServiceImpl instance;
	private TokenBucketFactory tokenBucketFactory;
	private GlobalTokenBucketFactory globalBucketFactory;
	
	private RateLimiterServiceImpl(int capacity,long refillRate,int globalCapacity, long globalRefillRate) {
		this.tokenBucketFactory = new TokenBucketFactory(capacity, refillRate);
		this.globalBucketFactory = new GlobalTokenBucketFactory(globalCapacity, globalRefillRate);
	}
	
	public static RateLimiterServiceImpl getInstance(int capacity , long refillRate, int globalCapacity, long globalRefillRate) {
		if(instance == null) {
			synchronized(RateLimiterServiceImpl.class) {
				instance = new RateLimiterServiceImpl(capacity, refillRate,globalCapacity,globalRefillRate);	
			}
		}
		return instance;
	}

	// Check if the user request is allowed (per-user and global rate limits)
	@Override
	public boolean isAllowed(User user) {
		// First check per-user bucket
		TokenBucket  bucket = tokenBucketFactory.getBucketForUser(user);
		boolean userAllowed = bucket.consume();
		
		// Then check global bucket if user is allowed
		if(userAllowed) {
			TokenBucket globalBucket = globalBucketFactory.getGlobalBucket();
			return globalBucket.consume(); // Check global token bucket
		}
		 // If user is not allowed due to user bucket being empty
		return false;
	}

	
}
