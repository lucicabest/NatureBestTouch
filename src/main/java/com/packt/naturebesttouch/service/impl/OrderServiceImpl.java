package com.packt.naturebesttouch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.naturebesttouch.domain.Order;
import com.packt.naturebesttouch.domain.repository.OrderRepository;
import com.packt.naturebesttouch.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Long saveOrder(Order order) {
		return orderRepository.saveOrder(order);
	}

}
