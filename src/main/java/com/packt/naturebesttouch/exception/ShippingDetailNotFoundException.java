package com.packt.naturebesttouch.exception;

public class ShippingDetailNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3979362816606545362L;
	private String customerId;
	
	public ShippingDetailNotFoundException(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerId() {
		return customerId;
	}
	
}
