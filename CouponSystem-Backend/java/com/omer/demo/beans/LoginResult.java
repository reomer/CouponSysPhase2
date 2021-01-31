package com.omer.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class LoginResult {
	
	public String token;
	private String type;

}
