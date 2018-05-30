package com.packt.naturebesttouch.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable{

	private static final long serialVersionUID = 5066585151735544440L;

	private String id;
	private ProductSizePriceQuantity productSPQ;
	private int quantity;
	private BigDecimal totalPrice;
	
	
	public CartItem(String id) {
		super();
		this.id = id;
	}


	public String getId() {
		return id;
	}

	
	public ProductSizePriceQuantity getProductSPQ() {
		return productSPQ;
	}


	public void setProductSPQ(ProductSizePriceQuantity productSPQ) {
		this.productSPQ = productSPQ;
		this.updateTotalPrice();
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public BigDecimal getTotalPrice() {
		this.updateTotalPrice();
		return totalPrice;
	}


	public void updateTotalPrice() {
		totalPrice = this.productSPQ.getPrice().multiply(new BigDecimal(this.quantity));
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
