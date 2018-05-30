package com.packt.naturebesttouch.validator;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.packt.naturebesttouch.domain.Product;
import com.packt.naturebesttouch.domain.ProductSizePriceQuantity;
import com.packt.naturebesttouch.service.ProductService;

@Component
public class CorectPricePerSizeValidator implements Validator {

	@Autowired
	private ProductService productService;

	public boolean supports(Class<?> clazz) {
		System.out.println("In boolean Function supports of CorectPricePerSizeValidator");
		// System.out.println("Class of clazz: " + clazz.getClass().getName());
		// System.out.println("Class of ProductSizePriceQuantity: " +
		// ProductSizePriceQuantity.class);
		// System.out.println(ProductSizePriceQuantity.class.isAssignableFrom(clazz));
		// System.out.println("Class of Product: " + Product.class);
		// System.out.println(Product.class.isAssignableFrom(clazz));
		return (ProductSizePriceQuantity.class.isAssignableFrom(clazz)) || (Product.class.isAssignableFrom(clazz));
		// return Product.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {

		System.out.println("In Validate Function of CorectPricePerSizeValidator");
		// System.out.println(target.getClass().getName());

		ProductSizePriceQuantity productSPQ = (ProductSizePriceQuantity) target;
		// System.out.println(productSPQ.getProductId());
		// if(product.getUnitPrice()!= null && new
		// BigDecimal(1000).compareTo(product.getUnitPrice())<=0 &&
		// product.getUnitsInStock()>99)

		// ProductSizePriceQuantity productSPQ =
		// product.getUnitSPQ().get(product.getUnitSPQ().size()-1);

		// System.out.println(productService);

		Product product = productService.getProductById(productSPQ.getProductId());

		// ProductSizePriceQuantity tempSPQ = product.getUnitSPQ().get(2);

		ProductSizePriceQuantity minSPQ = productSPQ;

		for (ProductSizePriceQuantity productSPQparse : product.getUnitSPQ()) {

			// System.out.println("Compar" + productSPQparse.getSize() + " cu " +
			// productSPQ.getSize());

			if (productSPQparse.getSize() < productSPQ.getSize())
				minSPQ = productSPQparse;

		}

		// System.out.println("Minim size " + minSPQ.getSize());
		int minIndex = product.getUnitSPQ().indexOf(minSPQ);
		// System.out.println("Minim size index : " + minIndex);

		ProductSizePriceQuantity maxSPQ = productSPQ;

		if ((product.getUnitSPQ().size() >= 0) && (minIndex < (product.getUnitSPQ().size() - 1)))
			maxSPQ = product.getUnitSPQ().get(minIndex + 1);
		// System.out.println("Maximum size " + maxSPQ.getSize());

		if (productSPQ.getPrice() != null) {

			float minCorectPrice;
			minCorectPrice = productSPQ.getSize() * minSPQ.getPrice().floatValue() / minSPQ.getSize();

			float maxCorectPrice = productSPQ.getSize() * maxSPQ.getPrice().floatValue() / maxSPQ.getSize();

			// System.out.println("Corect Min Price: " + minCorectPrice);
			// System.out.println("Corect Max Price: " + maxCorectPrice);

			if (((minCorectPrice * (0.75f)) <= productSPQ.getPrice().floatValue())
					&& (productSPQ.getPrice().floatValue() <= (maxCorectPrice * (1.33f)))) {
			} else {
				// System.out.println("minCorectPrice <= productSPQ.getPrice().floatValue()) <=
				// maxCorectPrice)");
				errors.rejectValue("price",
						"com.packt.naturebesttouch.validator.CorectPricePerSizeValidator.smaller.message");

				System.out.println("Price needs to be between " + (minCorectPrice * (0.75f)) + " and " + (maxCorectPrice * (1.33f)));
			}
		}
	}
}
