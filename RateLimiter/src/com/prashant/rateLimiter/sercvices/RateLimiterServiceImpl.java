package com.prashant.rateLimiter.sercvices;

import com.prashant.rateLimiter.factory.TokenBucketFactory;
import com.prashant.rateLimiter.models.TokenBucket;
import com.prashant.rateLimiter.models.User;

public class RateLimiterServiceImpl implements RateLimiterService {
	
	private static RateLimiterServiceImpl instance;
	private TokenBucketFactory tokenBucketFactory;
	
	private RateLimiterServiceImpl(int capacity,long refillRate) {
		this.tokenBucketFactory = new TokenBucketFactory(capacity, refillRate);
	}
	
	public static RateLimiterServiceImpl getInstance(int capacity , long refillRate) {
		if(instance == null) {
			synchronized(RateLimiterServiceImpl.class) {
				instance = new RateLimiterServiceImpl(capacity, refillRate);	
			}
		}
		return instance;
	}

	@Override
	public boolean isAllowed(User user) {
		TokenBucket  bucket = tokenBucketFactory.getBucketForUser(user);
		return bucket.consume();
	}

	
}
