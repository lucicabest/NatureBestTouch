package com.packt.naturebesttouch.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.packt.naturebesttouch.domain.Address;
import com.packt.naturebesttouch.domain.Customer;
import com.packt.naturebesttouch.domain.Order;
import com.packt.naturebesttouch.domain.ShippingDetail;
import com.packt.naturebesttouch.domain.repository.AddressRepository;
import com.packt.naturebesttouch.domain.repository.OrderRepository;
import com.packt.naturebesttouch.domain.repository.ShippingDetailRepository;
import com.packt.naturebesttouch.exception.AddressNotFoundException;
import com.packt.naturebesttouch.exception.ShippingDetailNotFoundException;
import com.packt.naturebesttouch.service.CustomerService;
import com.packt.naturebesttouch.service.UserService;
import com.packt.naturebesttouch.service.impl.CartServiceImpl;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTempleate;

	@Autowired
	private UserService userService;

	@Autowired
	private CustomerService customerServices;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ShippingDetailRepository shippingDetailRepository;

	// @Autowired
	// private CartServiceImpl CartService;

	@Override
	public long saveOrder(Order order) {
		String customerId;
		if (customerServices.isCustomerExist()) {
			updateCustomer(order.getCustomer());
			customerId = customerServices.addCurrentCustomerToOrder().getCustomerId();
		} else {
			customerId = saveCustomer(order.getCustomer());
		}

		Long shippingDetailId = saveShippingDetail(order.getShippingDetail());
		order.getCustomer().setCustomerId(customerId);
		order.getShippingDetail().setId(shippingDetailId);
		long createdOrderId = createOrder(order);
		// CartService.clearCart(order.getCart().getId());
		return createdOrderId;
	}

	private long saveShippingDetail(ShippingDetail shippingDetail) {
		long addressId = saveAddress(shippingDetail.getShippingAddress());
		try {
			return shippingDetailRepository.getShippingDetailId(shippingDetail);
		} catch (ShippingDetailNotFoundException e) {
			String SQL = "INSERT INTO SHIPPING_DETAIL(NAME,SHIPPING_DATE,SHIPPING_ADDRESS_ID) "
					+ "VALUES (:name, :shippingDate, :addressId)";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", shippingDetail.getName());
			params.put("shippingDate", shippingDetail.getShippingDate());
			params.put("addressId", addressId);
			SqlParameterSource paramSource = new MapSqlParameterSource(params);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTempleate.update(SQL, paramSource, keyHolder, new String[] { "ID" });
			return keyHolder.getKey().longValue();
		}
	}

	private String saveCustomer(Customer customer) {
		long addressId = saveAddress(customer.getBillingAddress());
		String SQL = "INSERT INTO CUSTOMER(ID, NAME, PHONE_NUMBER, BILLING_ADDRESS_ID) "
				+ "VALUES (:id, :name, :phoneNumber, :addressId)";
		Map<String, Object> params = new HashMap<String, Object>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		params.put("id", userService.getUserByUsername(currentPrincipalName).getUserId());
		String name = userService.getUserByUsername(currentPrincipalName).getFirstName() + " "
				+ userService.getUserByUsername(currentPrincipalName).getLastName();
		params.put("name", name);
		params.put("phoneNumber", customer.getPhoneNumber());
		params.put("addressId", addressId);
		// SqlParameterSource paramSource = new MapSqlParameterSource(params);
		// KeyHolder keyHolder = new GeneratedKeyHolder();
		// jdbcTempleate.update(SQL, paramSource, keyHolder, new String[] { "ID" });
		jdbcTempleate.update(SQL, params);
		return userService.getUserByUsername(currentPrincipalName).getUserId();
	}

	@Override
	public void updateCustomer(Customer customer) {

		long addressId = saveAddress(customer.getBillingAddress());
		String SQL = "UPDATE CUSTOMER SET PHONE_NUMBER = :phoneNumber, BILLING_ADDRESS_ID = :addressId WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		params.put("id", userService.getUserByUsername(currentPrincipalName).getUserId());

		// params.put("name", customer.getName());
		params.put("phoneNumber", customer.getPhoneNumber());
		params.put("addressId", addressId);
		// SqlParameterSource paramSource = new MapSqlParameterSource(params);
		// KeyHolder keyHolder = new GeneratedKeyHolder();
		// jdbcTempleate.update(SQL, paramSource, keyHolder, new String[] { "ID" });
		jdbcTempleate.update(SQL, params);

	}

	private long saveAddress(Address address) {

		try {
			return addressRepository.getAddressId(address);
		} catch (AddressNotFoundException e) {
			// TODO: handle exception

			String SQL = "INSERT INTO ADDRESS(DOOR_NO,STREET_NAME,AREA_NAME,STATE,COUNTRY,ZIP) "
					+ "VALUES (:doorNo, :streetName, :areaName, :state, :country, :zip)";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("doorNo", address.getDoorNo());
			params.put("streetName", address.getStreetName());
			params.put("areaName", address.getAreaName());
			params.put("state", address.getState());
			params.put("country", address.getCountry());
			params.put("zip", address.getZipCode());
			SqlParameterSource paramSource = new MapSqlParameterSource(params);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTempleate.update(SQL, paramSource, keyHolder, new String[] { "ID" });
			return keyHolder.getKey().longValue();
		}
	}

	private long createOrder(Order order) {
		String SQL = "INSERT INTO ORDERS (CART_ID, CUSTOMER_ID, SHIPPING_DETAIL_ID, USERS_ID) "
				+ "VALUES (:cartId, :customerId, :shippingDetailId, :usersId)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", order.getOrderId());
		params.put("cartId", order.getCart().getId());
		params.put("customerId", order.getCustomer().getCustomerId());
		params.put("shippingDetailId", order.getShippingDetail().getId());

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		// System.out.println("currentPrincipal: " + currentPrincipalName + " User Id: "
		// + userService.getUserByUsername(currentPrincipalName).getUserId());

		params.put("usersId", userService.getUserByUsername(currentPrincipalName).getUserId());
		SqlParameterSource paramSource = new MapSqlParameterSource(params);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTempleate.update(SQL, paramSource, keyHolder, new String[] { "ID" });
		return keyHolder.getKey().longValue();
	}

}
