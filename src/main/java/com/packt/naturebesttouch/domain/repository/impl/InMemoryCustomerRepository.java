package com.packt.naturebesttouch.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.packt.naturebesttouch.domain.Customer;
import com.packt.naturebesttouch.domain.repository.CustomerRepository;
import com.packt.naturebesttouch.exception.CustomerNotFoundException;
import com.packt.naturebesttouch.service.UserService;


@Repository
public class InMemoryCustomerRepository implements CustomerRepository{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	UserService userService;
	
	@Override
	public void saveCustomer(Customer customer) {
//		long addressId = saveAddress(customer.getBillingAddress());
		String SQL = "INSERT INTO CUSTOMER(ID, NAME, PHONE_NUMBER, BILLING_ADDRESS_ID) "
				+ "VALUES (:id, :name, :phoneNumber, :addressId)";
		Map<String, Object> params = new HashMap<String, Object>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		params.put("id", userService.getUserByUsername(currentPrincipalName).getUserId());
		
		params.put("name", customer.getName());
		params.put("phoneNumber", customer.getPhoneNumber());
		params.put("addressId", customer.getBillingAddress().getId());
//		SqlParameterSource paramSource = new MapSqlParameterSource(params);
//		KeyHolder keyHolder = new GeneratedKeyHolder();
//		jdbcTempleate.update(SQL, paramSource, keyHolder, new String[] { "ID" });
		jdbcTemplate.update(SQL, params);
	}

	@Override
	public Customer getCustomer(String customerId) {
		
//		String SQL = "SELECT * FROM CUSTOMER WHERE ID =:customerId";
		String SQL = "SELECT USERS.FIRST_NAME, USERS.LAST_NAME, " 
				+ "CUSTOMER.ID, CUSTOMER.PHONE_NUMBER, "
				+ "ADDRESS.ID AS ADDRESS_ID, ADDRESS.DOOR_NO, ADDRESS.STREET_NAME, ADDRESS.AREA_NAME, ADDRESS.STATE, " 
				+ "ADDRESS.COUNTRY, ADDRESS.ZIP " 
				+ "FROM CUSTOMER Left JOIN users ON users.ID = CUSTOMER.ID "
				+ "INNER JOIN ADDRESS ON CUSTOMER.BILLING_ADDRESS_ID = ADDRESS.ID " 
				+ "AND CUSTOMER.ID = :customerId ORDER BY FIRST_NAME";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerId", customerId);
		 try {
		 return jdbcTemplate.queryForObject(SQL, params, new CustomerMapper());
		 } catch (DataAccessException e) {
		 throw new CustomerNotFoundException(customerId);
		 }
//		return jdbcTemplate.queryForObject(SQL, params, new ProductMapper());
		
	}

	
}
