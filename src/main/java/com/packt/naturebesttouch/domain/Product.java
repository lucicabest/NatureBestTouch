package com.packt.naturebesttouch.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.packt.naturebesttouch.validator.Category;

@XmlRootElement
public class Product implements Serializable {

	private static final long serialVersionUID = 656979300737590990L;

	// @Pattern(regexp = "P[1-9]+", message =
	// "{Pattern.Product.productId.validation}")
	private String productId;
	@Size(min = 4, max = 50, message = "{Size.Product.name.validation}")
	private String name;
	private List<ProductSizePriceQuantity> unitSPQ;
	// private BigDecimal unitPrice;
	private String description;
	// private String manufacturer;
	@Category
	private String category;
	@JsonIgnore
	private MultipartFile productImage;
	@JsonIgnore
	private MultipartFile productDocumentation;
	// private long unitsInStock;
	// private long unitsInOrder;
	// private boolean discontinued;
	// private String condition;

	public Product() {
		super();
	}

	// public Product(String productId, String name, BigDecimal unitPrice) {
	// this.productId = productId;
	// this.name = name;
	// this.unitPrice = unitPrice;
	// }

	public Product(String productId, String name, List<ProductSizePriceQuantity> unitSPQ) {
		super();
		this.productId = productId;
		this.name = name;
		this.unitSPQ = unitSPQ;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// public BigDecimal getUnitPrice() {
	// return unitPrice;
	// }
	//
	// public void setUnitPrice(BigDecimal unitPrice) {
	// this.unitPrice = unitPrice;
	// }

	public List<ProductSizePriceQuantity> getUnitSPQ() {
		return unitSPQ;
	}

	public void setUnitSPQ(List<ProductSizePriceQuantity> unitSPQ) {
		this.unitSPQ = unitSPQ;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// public String getManufacturer() {
	// return manufacturer;
	// }
	//
	// public void setManufacturer(String manufacturer) {
	// this.manufacturer = manufacturer;
	// }

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	// public long getUnitsInStock() {
	// return unitsInStock;
	// }
	//
	// public void setUnitsInStock(long unitsInStock) {
	// this.unitsInStock = unitsInStock;
	// }
	//
	// public long getUnitsInOrder() {
	// return unitsInOrder;
	// }
	//
	// public void setUnitsInOrder(long unitsInOrder) {
	// this.unitsInOrder = unitsInOrder;
	// }
	//
	// public boolean isDiscontinued() {
	// return discontinued;
	// }
	//
	// public void setDiscontinued(boolean discontinued) {
	// this.discontinued = discontinued;
	// }
	//
	// public String getCondition() {
	// return condition;
	// }
	//
	// public void setCondition(String condition) {
	// this.condition = condition;
	// }

	@XmlTransient
	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	@XmlTransient
	public MultipartFile getProductDocumentation() {
		return productDocumentation;
	}

	public void setProductDocumentation(MultipartFile productDocumentation) {
		this.productDocumentation = productDocumentation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
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
		Product other = (Product) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

}
