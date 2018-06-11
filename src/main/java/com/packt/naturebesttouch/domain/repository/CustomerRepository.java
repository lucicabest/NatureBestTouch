package com.packt.naturebesttouch.domain.repository;

import com.packt.naturebesttouch.domain.Customer;

public interface CustomerRepository {
	
	public void saveCustomer(Customer customer);
	public Customer getCustomer(String customerId);
		
}
