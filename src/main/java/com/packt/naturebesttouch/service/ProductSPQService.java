package com.packt.naturebesttouch.service;

import com.packt.naturebesttouch.domain.Product;
import com.packt.naturebesttouch.domain.ProductSizePriceQuantity;

public interface ProductSPQService {

	ProductSizePriceQuantity getProductSPQById(String productSPQID);
	Product getProductByProduct_Id(String product_ID);
}
