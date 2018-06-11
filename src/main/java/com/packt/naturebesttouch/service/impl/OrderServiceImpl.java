package com.packt.naturebesttouch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.packt.naturebesttouch.domain.Address;
import com.packt.naturebesttouch.domain.Customer;
import com.packt.naturebesttouch.domain.Order;
import com.packt.naturebesttouch.domain.ShippingDetail;
import com.packt.naturebesttouch.domain.repository.CustomerRepository;
import com.packt.naturebesttouch.domain.repository.OrderRepository;
import com.packt.naturebesttouch.service.OrderService;
import com.packt.naturebesttouch.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private UserService userService;

	@Override
	public Long saveOrder(Order order) {
		return orderRepository.saveOrder(order);
	}

	@Override
	public ShippingDetail addBillingAsShipping(Address address) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		String name = userService.getUserByUsername(currentPrincipalName).getFirstName() + " "
				+ userService.getUserByUsername(currentPrincipalName).getLastName();
//		Customer customer = customerRepository
//				.getCustomer(userService.getUserByUsername(currentPrincipalName).getUserId());
		ShippingDetail shippingDetail = new ShippingDetail();
		shippingDetail.setName(name);
		shippingDetail.setShippingAddress(address);
		return shippingDetail;
	}

}
