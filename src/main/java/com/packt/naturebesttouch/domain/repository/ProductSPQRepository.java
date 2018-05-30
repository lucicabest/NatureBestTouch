package com.packt.naturebesttouch.domain.repository;

import com.packt.naturebesttouch.domain.Product;
import com.packt.naturebesttouch.domain.ProductSizePriceQuantity;

public interface ProductSPQRepository {

	ProductSizePriceQuantity getProductSPQById(String productSPQID);
	
	Product getProductByProduct_Id(String product_ID);
}
