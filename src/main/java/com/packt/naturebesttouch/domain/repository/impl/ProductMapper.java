package com.packt.naturebesttouch.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.packt.naturebesttouch.domain.Product;
import com.packt.naturebesttouch.domain.ProductSizePriceQuantity;

public class ProductMapper implements RowMapper<Product> {

	// private ProductSizePriceQuantityMapper productSPQMapper;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

		Product product = new Product();

		String id = rs.getString("ID");
		// Cart cart = new Cart(id);
		System.out.println(id);
//		String SQL = String.format("SELECT * FROM PRICES WHERE PRODUCT_ID = '%s' ORDER BY SIZE", id);
		
//		select products.NAME, prices.ID, prices.PRODUCT_ID, prices.SIZE, prices.PRICE, prices.UNITS_IN_STOCK, prices.UNITS_IN_ORDER FROM products inner join prices ON products.ID = prices.PRODUCT_ID WHERE prices.PRODUCT_ID='1234';
		
		String SQL = String.format("SELECT PRODUCTS.NAME, PRICES.ID, PRICES.PRODUCT_ID, PRICES.SIZE, PRICES.PRICE, PRICES.UNITS_IN_STOCK, PRICES.UNITS_IN_ORDER FROM PRODUCTS INNER JOIN PRICES ON PRODUCTS.ID = PRICES.PRODUCT_ID WHERE PRICES.PRODUCT_ID = '%s' ORDER BY SIZE", id);
		System.out.println(SQL);
		System.out.println(jdbcTemplate);
		List<ProductSizePriceQuantity> productsSPQ = jdbcTemplate.query(SQL, new ProductSizePriceQuantityMapper());
		// cart.setCartItems(cartItems);
		// return cart;

		product.setProductId(rs.getString("ID"));
		product.setName(rs.getString("NAME"));
		product.setUnitSPQ(productsSPQ);
		product.setDescription(rs.getString("DESCRIPTION"));
		// product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
		// product.setManufacturer(rs.getString("MANUFACTURER"));
		product.setCategory(rs.getString("CATEGORY"));
		// product.setCondition(rs.getString("PRODUCT_CONDITION"));
		// product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
		// product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
		// product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
		return product;
	}
}
