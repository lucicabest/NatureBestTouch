package com.packt.naturebesttouch.exception;

public class InvalidCartException extends RuntimeException{

	private static final long serialVersionUID = 3451061978479884524L;

	private String cartId;

	public InvalidCartException(String cartId) {
		this.cartId = cartId;
	}
	
	public String getCartId() {
		return cartId;
	}
	
}
