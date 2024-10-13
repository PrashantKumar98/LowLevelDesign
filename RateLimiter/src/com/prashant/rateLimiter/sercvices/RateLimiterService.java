package com.prashant.rateLimiter.sercvices;

import com.prashant.rateLimiter.models.User;

public interface RateLimiterService {
	public boolean isAllowed(User user);
}
