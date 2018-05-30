package com.packt.naturebesttouch.validator;

//import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.packt.naturebesttouch.domain.Product;
import com.packt.naturebesttouch.domain.ProductSizePriceQuantity;
//import com.packt.naturebesttouch.exception.ProductNotFoundException;
import com.packt.naturebesttouch.service.ProductService;;

@Component
public class ExistingSizePriceValidator implements Validator {

	@Autowired
	private ProductService productService;	
	
	public boolean supports(Class<?> clazz) {
		System.out.println("In boolean Function supports of ExistingSizePriceValidator");
//		System.out.println("Class of clazz: " + clazz.getClass().getName());
//		System.out.println("Class of ProductSizePriceQuantity: " + ProductSizePriceQuantity.class);
//		System.out.println(ProductSizePriceQuantity.class.isAssignableFrom(clazz));
//		System.out.println("Class of Product: " + Product.class);
//		System.out.println(Product.class.isAssignableFrom(clazz));
		return (ProductSizePriceQuantity.class.isAssignableFrom(clazz))||(Product.class.isAssignableFrom(clazz));
//		return Product.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		System.out.println("In Validate Function of ExistingSizePriceValidator");
//		System.out.println(target.getClass().getName());
		
		ProductSizePriceQuantity productSPQ = (ProductSizePriceQuantity) target;
//		System.out.println(productSPQ.getProductId());
//		if(product.getUnitPrice()!= null && new	BigDecimal(1000).compareTo(product.getUnitPrice())<=0 &&
//				product.getUnitsInStock()>99)
		
			
//		ProductSizePriceQuantity productSPQ = product.getUnitSPQ().get(product.getUnitSPQ().size()-1);
		
//		System.out.println(productService);
		
		Product product = productService.getProductById(productSPQ.getProductId());
//
		for (ProductSizePriceQuantity productSPQparse : product.getUnitSPQ()) {
//			System.out.println("Compar" + productSPQparse.getSize() + " cu " + productSPQ.getSize());
			if (productSPQparse.getSize() == productSPQ.getSize())
				errors.rejectValue("size", "com.packt.naturebesttouch.validator.ExistingSizePriceValidator.message");	
		}
		
	}
}