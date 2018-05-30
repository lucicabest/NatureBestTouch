package com.packt.naturebesttouch.dto;

import java.io.Serializable;

public class CartItemDto implements Serializable{

	private static final long serialVersionUID = 3345145548956281088L;

	private String id;
	private String productSPQId;
	private int quantity;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductSPQId() {
		return productSPQId;
	}
	public void setProductSPQId(String productId) {
		this.productSPQId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
