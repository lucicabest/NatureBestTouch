package com.packt.naturebesttouch.exception;

public class CustomerNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -5410433119705925627L;
	private String customerId;
	
	
	public CustomerNotFoundException(String customerId) {
	
		this.customerId = customerId;
	}


	public String getCustomerId() {
		return customerId;
	}
	
		
}
