package com.omer.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RemoveOldTokens {
	 
	@Autowired
	private TokenManeger tokenManeger;
	
	@Scheduled(fixedRate = 1000 * 60 * 30)
	public void deleteTokens() {
		tokenManeger.removeExpiredTokens();
	}
}
