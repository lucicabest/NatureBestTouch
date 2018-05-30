package com.packt.naturebesttouch.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.packt.naturebesttouch.domain.CartItem;
import com.packt.naturebesttouch.service.ProductSPQService;

public class CartItemMapper implements RowMapper<CartItem> {

	private ProductSPQService productSPQService;

	public CartItemMapper(ProductSPQService productSPQService) {
		// super();
		this.productSPQService = productSPQService;
	}

	@Override
	public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		CartItem cartItem = new CartItem(rs.getString("ID"));
		cartItem.setProductSPQ(productSPQService.getProductSPQById(rs.getString("PRODUCT_PRICE_ID")));
		cartItem.setQuantity(rs.getInt("QUANTITY"));
		return cartItem;
	}
}
