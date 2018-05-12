package com.packt.naturebesttouch.service;

import java.util.List;
import java.util.Map;

import com.packt.naturebesttouch.domain.Product;

public interface ProductService {

	void updateAllStock();

	List<Product> getAllProducts();

	List<Product> getProductsByCategory(String category);

	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	
	Product getProductById(String productID);
	
	Product getProductBycategoryAndPrice(String category, String price);
	
	List<Product> getProductsByCategoryByFilters(String category, Map<String, String> filterParams, String brand);
//	List<Product> getProductsByCategoryByFilter(String category, Map<String, String> filterParams, String brand);
	
}
