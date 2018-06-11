package com.packt.naturebesttouch.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.packt.naturebesttouch.domain.Address;
import com.packt.naturebesttouch.domain.Customer;

public class CustomerMapper implements RowMapper<Customer> {

//	@Autowired
//	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

		Customer customer = new Customer();

		String id = rs.getString("ID");
		// Cart cart = new Cart(id);
		System.out.println("In Customer Mapper. id = rs.getString(ID) : " + id);
		// String SQL = String.format("SELECT * FROM PRICES WHERE PRODUCT_ID = '%s'
		// ORDER BY SIZE", id);

		// select products.NAME, prices.ID, prices.PRODUCT_ID, prices.SIZE,
		// prices.PRICE, prices.UNITS_IN_STOCK, prices.UNITS_IN_ORDER FROM products
		// inner join prices ON products.ID = prices.PRODUCT_ID WHERE
		// prices.PRODUCT_ID='1234';

//		String SQL = String.format("SELECT USERS.FIRST_NAME, USERS.LAST_NAME, " 
//				+ "CUSTOMER.ID, CUSTOMER.PHONE_NUMBER, "
//				+ "ADDRESS.ID AS ADDRESS_ID, ADDRESS.DOOR_NO, ADDRESS.STREET_NAME, ADDRESS.AREA_NAME, ADDRESS.STATE, " 
//				+ "ADDRESS.COUNTRY, ADDRESS.ZIP " 
//				+ "FROM CUSTOMER Left JOIN users ON users.ID = CUSTOMER.ID "
//				+ "INNER JOIN ADDRESS ON CUSTOMER.BILLING_ADDRESS_ID = ADDRESS.ID " 
//				+ "AND CUSTOMER.ID = '%s' ORDER BY FIRST_NAME;", id);
//		System.out.println(SQL);
//		System.out.println(jdbcTemplate);
//		List<ProductSizePriceQuantity> productsSPQ = jdbcTemplate.query(SQL, new ProductSizePriceQuantityMapper());
		// cart.setCartItems(cartItems);
		// return cart;

		customer.setCustomerId(rs.getString("ID"));
		customer.setName(rs.getString("FIRST_NAME") + " " + rs.getString("LAST_NAME"));
		customer.setPhoneNumber(rs.getString("PHONE_NUMBER"));
		Address address = new Address();
		address.setId(rs.getLong("ADDRESS_ID"));
		address.setDoorNo(rs.getString("DOOR_NO"));
		address.setStreetName(rs.getString("STREET_NAME"));
		address.setAreaName(rs.getString("AREA_NAME"));
		address.setState(rs.getString("STATE"));
		address.setCountry(rs.getString("COUNTRY"));
		address.setZipCode(rs.getString("ZIP"));
		customer.setBillingAddress(address);
		return customer;

	}

}
