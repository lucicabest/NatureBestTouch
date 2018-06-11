package com.packt.naturebesttouch.domain.repository.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.packt.naturebesttouch.domain.ShippingDetail;
import com.packt.naturebesttouch.domain.repository.AddressRepository;
import com.packt.naturebesttouch.domain.repository.ShippingDetailRepository;
import com.packt.naturebesttouch.exception.ShippingDetailNotFoundException;
import com.packt.naturebesttouch.service.UserService;

@Repository
public class InMemoryShippingDetailRepository implements ShippingDetailRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private UserService userService;
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Long getShippingDetailId(ShippingDetail shippingDetail) {

		
		String SQL = "SELECT shipping_detail.id FROM shipping_detail where NAME = :name and SHIPPING_DATE = :shippingDate"
				+ " and SHIPPING_ADDRESS_ID = :shippingAddressId";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", shippingDetail.getName());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		// Tue Jun 12 00:00:00 CDT 2018
		Long shippingAddressId = addressRepository.getAddressId(shippingDetail.getShippingAddress());
		
//		System.out.println("shippingDetail.getShippingDate(): " + formatter.format(shippingDetail.getShippingDate())
//				+ " 00:00:00");
//		System.out.println("Name: " + shippingDetail.getName());
//		System.out.println("Shipping address ID: " + shippingAddressId);
		params.put("shippingDate", formatter.format(shippingDetail.getShippingDate()) + " 00:00:00");
		params.put("shippingAddressId", shippingAddressId);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		try {
			return jdbcTemplate.queryForObject(SQL, params, new ShippingDetailIdMapper());
		} catch (DataAccessException e) {
			throw new ShippingDetailNotFoundException(userService.getUserByUsername(currentPrincipalName).getUserId());
		}
		// return jdbcTemplate.queryForObject(SQL, params, new ProductMapper());

	}

}
