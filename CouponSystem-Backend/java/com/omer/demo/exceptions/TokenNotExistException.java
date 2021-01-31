package com.omer.demo.exceptions;

public class TokenNotExistException extends Exception  {
	
	public TokenNotExistException(String message) {
		
		super("No Available Token: " + message);

}
}