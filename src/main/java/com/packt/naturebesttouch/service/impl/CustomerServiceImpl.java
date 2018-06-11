package com.packt.naturebesttouch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.packt.naturebesttouch.domain.Customer;
import com.packt.naturebesttouch.domain.repository.CustomerRepository;
import com.packt.naturebesttouch.exception.CustomerNotFoundException;
import com.packt.naturebesttouch.service.CustomerService;
import com.packt.naturebesttouch.service.UserService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	UserService userService;

	@Override
	public Boolean isCustomerExist() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		System.out.println("In boolean isCustomerExist");
		System.out.println("currentPrincipal: " + currentPrincipalName + " User Id: "
				+ userService.getUserByUsername(currentPrincipalName).getUserId());

		try {
			Customer customer = customerRepository
					.getCustomer(userService.getUserByUsername(currentPrincipalName).getUserId());
			// return true;
		} catch (CustomerNotFoundException e) {

			return false;

		}

		// System.out.print("");
		// System.out.print("");
		// System.out.print("");

		// if (customer == null) {
		//// throw new CustomerNotFoundException(customerId);
		// System.out.println("Before returning false => customer-ul ii null");
		// return false;
		// }

		// return false;
		// System.out.println("Customer name: " + customer.getName());
		// System.out.println("customer address id" +
		// customer.getBillingAddress().toString());
		// System.out.println("Before returning True => customer-ul nu ii null");
		return true;
	}

	@Override
	public Customer addCurrentCustomerToOrder() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Customer customer = customerRepository
				.getCustomer(userService.getUserByUsername(currentPrincipalName).getUserId());
		return customer;
	}

	@Override
	public Customer addNameCustomerToOrder() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Customer customer = new Customer();
		String name = userService.getUserByUsername(currentPrincipalName).getFirstName() + " "
				+ userService.getUserByUsername(currentPrincipalName).getLastName();
		customer.setName(name);
		return customer;
	}

}
