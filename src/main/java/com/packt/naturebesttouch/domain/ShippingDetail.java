package com.packt.naturebesttouch.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ShippingDetail implements Serializable{

	private static final long serialVersionUID = 2157754952732077570L;

	private Long id;
	private String name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date shippingDate;
	private Address shippingAddress;
	
	
	public ShippingDetail() {
		this.shippingAddress = new Address();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getShippingDate() {
		return shippingDate;
	}


	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
//		this.shippingDate = new Date();
//		System.out.println( "Shipping date: " + this.shippingDate);
		
	}


	public Address getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(Address shippingAddress) {
//		this.shippingAddress = shippingAddress;
//		doing this way because otherwise if you go back after doing ShippingSameAsBilling, 
//		every time you change the shipping it will change the billing too because of the same references
		this.shippingAddress.setAreaName(shippingAddress.getAreaName());
		this.shippingAddress.setCountry(shippingAddress.getCountry());
		this.shippingAddress.setDoorNo(shippingAddress.getDoorNo());
		this.shippingAddress.setState(shippingAddress.getState());
		this.shippingAddress.setStreetName(shippingAddress.getStreetName());
		this.shippingAddress.setZipCode(shippingAddress.getZipCode());
		this.shippingAddress.setId(shippingAddress.getId());
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
		ShippingDetail other = (ShippingDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
