package com.packt.naturebesttouch.domain.repository;

import java.util.List;
import java.util.Map;

import com.packt.naturebesttouch.domain.Product;
import com.packt.naturebesttouch.domain.ProductSizePriceQuantity;

public interface ProductRepository {

	List<Product> getAllProducts();

	void updateStock(String productId, long noOfUnits);

	List<Product> getProductsByCategory(String category);

//	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	
	Product getProductById(String productID);
	
//	Product getProductBycategoryAndPrice(String category, String price);
	
//	List<Product> getProductsByCategoryByFilters(String category, Map<String, String> filterParams, String brand);
//	List<Product> getProductsByCategoryByFilter(String category, Map<String, String> filterParams, String brand);
	
	String addProduct(Product product);
	void addProductSPQ(ProductSizePriceQuantity productSPQ, String productId);
}
