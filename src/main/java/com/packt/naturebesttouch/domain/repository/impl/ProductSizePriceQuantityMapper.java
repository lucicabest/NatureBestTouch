package com.packt.naturebesttouch.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.packt.naturebesttouch.domain.ProductSizePriceQuantity;

public class ProductSizePriceQuantityMapper implements RowMapper<ProductSizePriceQuantity> {
	

	@Override
	public ProductSizePriceQuantity mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ProductSizePriceQuantity productSPQ = new ProductSizePriceQuantity();
		
		productSPQ.setPriceId(rs.getString("ID"));
		productSPQ.setProductId(rs.getString("PRODUCT_ID"));
		productSPQ.setSize(rs.getFloat("SIZE"));
		productSPQ.setPrice(rs.getBigDecimal("PRICE"));
		productSPQ.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
		productSPQ.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));

		return productSPQ;
	}
}
