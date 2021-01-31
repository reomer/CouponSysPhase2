package com.omer.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.omer.demo.security.UserData;

@Configuration
public class TokenConfig {

	@Bean
	public Map<String, UserData> createMap(){
		return new HashMap<String, UserData>();
		
	}
}
