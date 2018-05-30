package com.packt.naturebesttouch.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.naturebesttouch.domain.Product;
import com.packt.naturebesttouch.domain.ProductSizePriceQuantity;
import com.packt.naturebesttouch.domain.repository.ProductSPQRepository;
import com.packt.naturebesttouch.exception.ProductNotFoundException;

@Repository
public class InMemoryProductSPQRepository implements ProductSPQRepository{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public ProductSizePriceQuantity getProductSPQById(String productSPQID) {
		// TODO Auto-generated method stub
		String SQL = "SELECT * FROM PRICES WHERE ID =:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", productSPQID);
		 try {
		 return jdbcTemplate.queryForObject(SQL, params, new ProductSizePriceQuantityMapper());
		 } catch (DataAccessException e) {
		 throw new ProductNotFoundException(productSPQID);
		 }

	}

	@Override
	public Product getProductByProduct_Id(String product_ID) {
		String SQL = "SELECT * FROM PRODUCTS WHERE ID =:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", product_ID);
		 try {
		 return jdbcTemplate.queryForObject(SQL, params, new ProductMapper());
		 } catch (DataAccessException e) {
		 throw new ProductNotFoundException(product_ID);
		 }
		
	}
}
