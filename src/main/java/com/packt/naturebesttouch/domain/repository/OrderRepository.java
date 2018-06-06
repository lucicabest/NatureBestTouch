package com.packt.naturebesttouch.domain.repository;

import com.packt.naturebesttouch.domain.Order;

public interface OrderRepository {

	long saveOrder(Order order);
	
}
