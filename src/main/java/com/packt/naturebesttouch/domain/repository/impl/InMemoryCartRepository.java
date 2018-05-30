package com.packt.naturebesttouch.domain.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.packt.naturebesttouch.domain.Cart;
import com.packt.naturebesttouch.domain.CartItem;
import com.packt.naturebesttouch.domain.ProductSizePriceQuantity;
import com.packt.naturebesttouch.domain.repository.CartRepository;
import com.packt.naturebesttouch.dto.CartDto;
import com.packt.naturebesttouch.dto.CartItemDto;
import com.packt.naturebesttouch.service.ProductSPQService;

@Repository
public class InMemoryCartRepository implements CartRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTempleate;
	@Autowired
	private ProductSPQService productSPQService;

	public void create(CartDto cartDto) {
//		String INSERT_CART_SQL = "INSERT INTO CART(ID) VALUES (:id)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
//		jdbcTemplate.update(SQL, new MapSqlParameterSource(params), keyHolder);
		
//		keyHolder.getKey().toString();
		
		String INSERT_CART_SQL = "INSERT INTO CART() VALUES ()";
		Map<String, Object> cartParams = new HashMap<String, Object>();
//		cartParams.put("id", cartDto.getId());
//		jdbcTempleate.update(INSERT_CART_SQL, cartParams);
		jdbcTempleate.update(INSERT_CART_SQL, new MapSqlParameterSource(cartParams), keyHolder);
		cartDto.setId(keyHolder.getKey().toString());
		
		cartDto.getCartItems().stream().forEach(cartItemDto -> {
			ProductSizePriceQuantity productSPQById = productSPQService.getProductSPQById(cartItemDto.getProductSPQId());
			String INSERT_CART_ITEM_SQL = "INSERT INTO CART_ITEM(PRODUCT_PRICE_ID,CART_ID,QUANTITY) "
					+ "VALUES (:product_id, :cart_id, :quantity)";
			Map<String, Object> cartItemsParams = new HashMap<String, Object>();
//			cartItemsParams.put("id", cartItemDto.getId());
			cartItemsParams.put("product_id", productSPQById.getProductId());
			cartItemsParams.put("cart_id", cartDto.getId());
			cartItemsParams.put("quantity", cartItemDto.getQuantity());
			
			KeyHolder keyHolder2 = new GeneratedKeyHolder();
			jdbcTempleate.update(INSERT_CART_ITEM_SQL, new MapSqlParameterSource(cartItemsParams), keyHolder2);
			cartItemDto.setId(keyHolder2.getKey().toString());
		});
	}

	public Cart read(String id) {
		String SQL = "SELECT * FROM CART WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		CartMapper cartMapper = new CartMapper(jdbcTempleate, productSPQService);
		try {
			return jdbcTempleate.queryForObject(SQL, params, cartMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void update(String id, CartDto cartDto) {
		List<CartItemDto> cartItems = cartDto.getCartItems();
		for (CartItemDto cartItem : cartItems) {
			String SQL = "UPDATE CART_ITEM SET QUANTITY= :quantity, PRODUCT_ID = :productId WHERE ID = :id AND CART_ID = :cartId";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", cartItem.getId());
			params.put("quantity", cartItem.getQuantity());
			params.put("productId", cartItem.getProductSPQId());
			params.put("cartId", id);
			jdbcTempleate.update(SQL, params);
		}
	}

	@Override
	public void delete(String id) {
		String SQL_DELETE_CART_ITEM = "DELETE FROM CART_ITEM WHERE CART_ID = :id";
		String SQL_DELETE_CART = "DELETE FROM CART WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		jdbcTempleate.update(SQL_DELETE_CART_ITEM, params);
		jdbcTempleate.update(SQL_DELETE_CART, params);
	}

	@Override
	public void addItem(String cartId, String productId) {
		String SQL = null;
		Cart cart = null;
		cart = read(cartId);
		if (cart == null) {
			CartItemDto newCartItemDto = new CartItemDto();
			newCartItemDto.setId(cartId + productId);
			newCartItemDto.setProductSPQId(productId);
			newCartItemDto.setQuantity(1);
			CartDto newCartDto = new CartDto(cartId);
			newCartDto.addCartItem(newCartItemDto);
			create(newCartDto);
			return;
		}
		Map<String, Object> cartItemsParams = new HashMap<String, Object>();
		if (cart.getItemByProductSPQId(productId) == null) {
			SQL = "INSERT INTO CART_ITEM (ID, PRODUCT_ID, CART_ID, QUANTITY) VALUES (:id, :productId, :cartId, :quantity)";
			cartItemsParams.put("id", cartId + productId);
			cartItemsParams.put("quantity", 1);
		} else {
			SQL = "UPDATE CART_ITEM SET QUANTITY = :quantity WHERE CART_ID = :cartId AND PRODUCT_ID = :productId";
			CartItem existingItem = cart.getItemByProductSPQId(productId);
			cartItemsParams.put("id", existingItem.getId());
			cartItemsParams.put("quantity", existingItem.getQuantity() + 1);
		}
		cartItemsParams.put("productId", productId);
		cartItemsParams.put("cartId", cartId);
		jdbcTempleate.update(SQL, cartItemsParams);
	}

	@Override
	public void removeItem(String cartId, String productId) {
		String SQL_DELETE_CART_ITEM = "DELETE FROM CART_ITEM WHERE PRODUCT_ID = :productId AND CART_ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", cartId);
		params.put("productId", productId);
		jdbcTempleate.update(SQL_DELETE_CART_ITEM, params);
	}
}
