package com.prashant.rateLimiter;

import com.prashant.rateLimiter.models.User;
import com.prashant.rateLimiter.sercvices.RateLimiterService;
import com.prashant.rateLimiter.sercvices.RateLimiterServiceImpl;

public class DriverMainClass {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// Initialize the rate limiter with user bucket of 5 tokens, global bucket of 12 tokens
		RateLimiterService rateLimiterService = RateLimiterServiceImpl.getInstance(5, 2, 12, 3);
		// per user token is 5 with 2 bucket/min refill rate
		// per system token is 12 with 3 bucket/min refill rate
		
		// Create a user
		User user1 = new User("user1");
	    User user2 = new User("user2");
	    
	    
		// Simulate multiple API calls
		for (int i = 0; i < 20; i++) {
			if (rateLimiterService.isAllowed(user1)) {
				System.out.println("Request " + (i + 1) + " from user1 is allowed.");
			} else {
				System.out.println("Request " + (i + 1) + " from user1 is denied due to rate limiting.");
			}
			
            // User2 makes a request
            if (rateLimiterService.isAllowed(user2)) {
                System.out.println("Request " + (i + 1) + " from user2 is allowed.");
            } else {
                System.out.println("Request " + (i + 1) + " from user2 is denied due to rate limiting.");
            }
			Thread.sleep(500); // Wait 0.5 seconds between each request

			if (i == 15) {
				Thread.sleep(1000 * 60);
			}
		}

	}

}
