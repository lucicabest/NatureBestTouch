package com.packt.naturebesttouch.exception;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -4924912137646904268L;
	private String username;
	
	
	public UserNotFoundException(String username) {
//		super();
		this.username = username;
	}


	public String getUsername() {
		return username;
	}
	
}
