package com.packt.naturebesttouch.domain.repository;

import com.packt.naturebesttouch.domain.Cart;
import com.packt.naturebesttouch.dto.CartDto;

public interface CartRepository {

	void create(CartDto cartDto);
	Cart read(String id);
	void update(String id, CartDto cartDto);
	void delete(String id);
	void addItem(String cartId, String productId);
	void removeItem(String cartId, String productId);
	
}
