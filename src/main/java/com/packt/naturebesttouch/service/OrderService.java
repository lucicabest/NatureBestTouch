package com.packt.naturebesttouch.service;

import com.packt.naturebesttouch.domain.Address;
import com.packt.naturebesttouch.domain.Order;
import com.packt.naturebesttouch.domain.ShippingDetail;

public interface OrderService {

	Long saveOrder(Order order);
	ShippingDetail addBillingAsShipping(Address address);
	
}
