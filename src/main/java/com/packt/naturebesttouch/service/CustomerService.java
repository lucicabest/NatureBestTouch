package com.packt.naturebesttouch.service;

import com.packt.naturebesttouch.domain.Customer;

public interface CustomerService {

	public Boolean isCustomerExist();
	public Customer addCurrentCustomerToOrder();
	public Customer addNameCustomerToOrder();
	
}
