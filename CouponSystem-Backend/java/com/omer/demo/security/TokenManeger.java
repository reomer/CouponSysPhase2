package com.omer.demo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omer.demo.exceptions.LoginException;
import com.omer.demo.exceptions.TokenNotExistException;


@Component
public class TokenManeger {
	
	@Autowired
	private Map<String,UserData> map;
	
	public void insert(String token, UserData userData) {
		map.put(token, userData);
		
	}
	
	public boolean isTokenExist(String token) throws TokenNotExistException{
	if(map.get(token) != null) {
		
		return true;
	}
	throw new TokenNotExistException("token is not valid");
	}

	public void removeExpiredTokens() { 
    for 	(Map.Entry<String, UserData> entry : map.entrySet()){
    	UserData userData = entry.getValue();
    	Date now =new Date(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(30));
    	Date otherDate = new Date(userData.getTimeStamp());
    	if(now.before(otherDate)) {
    		String  token = entry.getKey();
    		map.remove(token); 
    	}
    }
	}

	public void removeToken(String token) {
		map.remove(token);
	}
}
