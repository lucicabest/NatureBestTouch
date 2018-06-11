package com.packt.naturebesttouch.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.packt.naturebesttouch.domain.Address;
import com.packt.naturebesttouch.domain.repository.AddressRepository;
import com.packt.naturebesttouch.exception.AddressNotFoundException;
import com.packt.naturebesttouch.service.UserService;

@Repository
public class InMemoryAddressRepository implements AddressRepository{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private UserService userService;
	
	@Override
	public Long getAddressId(Address address) {
		
		String SQL = "SELECT address.id FROM address where DOOR_NO = :door_no and STREET_NAME = :streetName" + 
				" and AREA_NAME = :areaName and STATE = :state and COUNTRY = :country and ZIP = :zip";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("door_no", address.getDoorNo());
		params.put("streetName", address.getStreetName());
		params.put("areaName", address.getAreaName());
		params.put("state", address.getState());
		params.put("country", address.getCountry());
		params.put("zip", address.getZipCode());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		 try {
		 return jdbcTemplate.queryForObject(SQL, params, new AddressIdMapper());
		 } catch (DataAccessException e) {
		 throw new AddressNotFoundException(userService.getUserByUsername(currentPrincipalName).getUserId());
		 }
//		return jdbcTemplate.queryForObject(SQL, params, new ProductMapper());
		
		
		
	}

}
