package com.prashant.rateLimiter.factory;

import com.prashant.rateLimiter.models.TokenBucket;

/*Global Bucket: A separate global token bucket is introduced, shared by all users, to limit overall API capacity.*/

public class GlobalTokenBucketFactory {
	
	private TokenBucket globalBucket;
	
	public GlobalTokenBucketFactory(int capacity, long refillRate) {
		this.globalBucket = new TokenBucket(capacity,refillRate);
	}
	
	public TokenBucket getGlobalBucket() {
		return this.globalBucket;
	}

}
