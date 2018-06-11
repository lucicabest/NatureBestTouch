package com.packt.naturebesttouch.domain.repository;

import com.packt.naturebesttouch.domain.Customer;
import com.packt.naturebesttouch.domain.Order;

public interface OrderRepository {

	long saveOrder(Order order);
	void updateCustomer(Customer customer);
}
