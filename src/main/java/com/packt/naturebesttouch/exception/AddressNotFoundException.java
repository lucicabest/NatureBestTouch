package com.packt.naturebesttouch.exception;

public class AddressNotFoundException extends RuntimeException{


	private static final long serialVersionUID = -4860617388481744544L;
	private String customerId;
	
	
	public AddressNotFoundException(String customerId) {
		this.customerId = customerId;
	}


	public String getCustomerId() {
		return customerId;
	}
	
}
