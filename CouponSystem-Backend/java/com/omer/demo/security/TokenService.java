package com.omer.demo.security;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
	
	@Autowired
	private TokenManeger tokenManeger;
	
	public String createToken(String email) {
		
		String UserEmail = email;
		long Date =System.currentTimeMillis();
		String token = UUID.randomUUID().toString();
		tokenManeger.insert(token, new UserData(email,Date) );
		return token;
	}
	

}
