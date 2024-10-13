package com.prashant.rateLimiter.models;

public class TokenBucket {

	private final int capacity;
	private final long refillRate;
	private long token;
	private long lastRefillTimeStamp;

	public TokenBucket(int capacity, long refillRate) {
		this.capacity = capacity;
		this.refillRate = refillRate;
		this.token = capacity; // Start with full bucket
		this.lastRefillTimeStamp = System.nanoTime();
	}

	// Refill the token based on elapsed time
	public void refill() {
		long current = System.nanoTime();
		long elapsedTime = current - this.lastRefillTimeStamp;
		// refill rate is in token/minute
		long tokenToAdd = (elapsedTime / 60_000_000_000L) * this.refillRate;
		this.token = Math.min(this.capacity, this.token + tokenToAdd);
		this.lastRefillTimeStamp = current;
	}

	public synchronized boolean consume() {
		refill();
		if (this.token > 0) {
			this.token--;
			return true;
		}
		return false;
	}

}
