package com.packt.naturebesttouch.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;

//import com.packt.naturebesttouch.validator.SizePQ;


public class ProductSizePriceQuantity implements Serializable {

	private static final long serialVersionUID = 3690392531428509119L;

	private String priceId;
	
	private String productId;

	//	@SizePQ
	private float size;
//	@Pattern(regexp="[1-9]+", message="{Pattern.ProductSizePriceQuantity.price.validation}")
	@Min(value = 0, message = "{Min.ProductSizePriceQuantity.price.validation}")
	@Digits(integer = 8, fraction = 2, message = "{Digits.ProductSizePriceQuantity.price.validation}")
	@NotNull(message = "{NotNull.ProductSizePriceQuantity.price.validation}")
	private BigDecimal price;
	private long unitsInStock;
	private long unitsInOrder;

	public ProductSizePriceQuantity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductSizePriceQuantity(String priceId, String productId, float size, BigDecimal price, long unitsInStock,
			long unitsInOrder) {
		super();
		this.productId = productId;
		this.priceId = priceId;
		this.size = size;
		this.price = price;
		this.unitsInStock = unitsInStock;
		this.unitsInOrder = unitsInOrder;
	}

	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public long getUnitsInOrder() {
		return unitsInOrder;
	}

	public void setUnitsInOrder(long unitsInOrder) {
		this.unitsInOrder = unitsInOrder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((priceId == null) ? 0 : priceId.hashCode());
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
		ProductSizePriceQuantity other = (ProductSizePriceQuantity) obj;
		if (priceId == null) {
			if (other.priceId != null)
				return false;
		} else if (!priceId.equals(other.priceId))
			return false;
		return true;
	}

}
