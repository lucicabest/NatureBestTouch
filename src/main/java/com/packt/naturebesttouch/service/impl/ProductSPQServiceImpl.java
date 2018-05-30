package com.packt.naturebesttouch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.naturebesttouch.domain.Product;
import com.packt.naturebesttouch.domain.ProductSizePriceQuantity;
import com.packt.naturebesttouch.domain.repository.ProductRepository;
import com.packt.naturebesttouch.domain.repository.ProductSPQRepository;
import com.packt.naturebesttouch.service.ProductSPQService;

@Service
public class ProductSPQServiceImpl implements ProductSPQService{

	@Autowired
	private ProductSPQRepository productSPQRepository;

	@Override
	public ProductSizePriceQuantity getProductSPQById(String productSPQID) {
		// TODO Auto-generated method stub
		return productSPQRepository.getProductSPQById(productSPQID);
	}

	@Override
	public Product getProductByProduct_Id(String product_ID) {
		return productSPQRepository.getProductByProduct_Id(product_ID);
	}

	
	
}
