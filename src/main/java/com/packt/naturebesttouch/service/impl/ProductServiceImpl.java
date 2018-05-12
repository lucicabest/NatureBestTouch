package com.packt.naturebesttouch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.naturebesttouch.domain.Product;
import com.packt.naturebesttouch.domain.repository.ProductRepository;

import com.packt.naturebesttouch.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void updateAllStock() {
		List<Product> allProducts = productRepository.getAllProducts();
		for (Product product : allProducts) {
			if (product.getUnitsInStock() < 500)
				productRepository.updateStock(product.getProductId(), product.getUnitsInStock() + 1000);
		}
	}

	public List<Product> getAllProducts() {
		List<Product> result = productRepository.getAllProducts();
		return result;
	}

	@Override
	public List<Product> getProductsByCategory(String category) {

		return productRepository.getProductsByCategory(category);
	}

	@Override
	public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		
		return productRepository.getProductsByFilter(filterParams);
	}

	@Override
	public Product getProductById(String productID) {
		return productRepository.getProductById(productID);
	}

	@Override
	public Product getProductBycategoryAndPrice(String category, String price) {

		return productRepository.getProductBycategoryAndPrice(category, price);
	}

	@Override
//	public List<Product> getProductsByCategoryByFilter(String category, Map<String, String> filterParams, String brand) {
	public List<Product> getProductsByCategoryByFilters(String category, Map<String, String> filterParams, String brand) {
		return productRepository.getProductsByCategoryByFilters(category, filterParams, brand);
//		return productRepository.getProductsByCategoryByFilter(category, filterParams, brand);
	}
	
	
	

}
