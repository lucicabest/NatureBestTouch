package com.packt.naturebesttouch.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import com.packt.naturebesttouch.domain.Product;
import com.packt.naturebesttouch.domain.ProductSizePriceQuantity;
//import com.packt.naturebesttouch.exception.NoProductsFoundUnderCategoryException;
import com.packt.naturebesttouch.service.ProductService;
import com.packt.naturebesttouch.validator.ExistingSizePriceValidator;
import com.packt.naturebesttouch.validator.ProductSPQValidator;

@Controller
@RequestMapping("market")
public class ProductSPQController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductSPQValidator productSPQValidator;
	
//	@Autowired
//	private ExistingSizePriceValidator existingSizePriceValidator;
		
	// http://localhost:8080/naturebesttouch/market/product/1238/addSPQ
	@RequestMapping(value = "/product/{productId}/addSPQ", method = RequestMethod.GET)
	public String getAddNewProductSPQForm(Model model, @PathVariable("productId") String productId)
			 {
		model.addAttribute("product", productService.getProductById(productId));
		ProductSizePriceQuantity newProductSPQ = new ProductSizePriceQuantity();
		model.addAttribute("newProductSPQ", newProductSPQ);
		return "addProductSPQ";
	}

	@RequestMapping(value = "/product/{productId}/addSPQ", method = RequestMethod.POST)
	public String processAddNewProductSPQForm(Model model,
			@PathVariable("productId") String productId,
			@ModelAttribute("newProductSPQ") @Valid ProductSizePriceQuantity newProductSPQ,
			 
			BindingResult result) {
		
		System.out.println("test bash before has errors");
		System.out.println(newProductSPQ.getProductId());
		if (result.hasErrors()) {

			model.addAttribute("product", productService.getProductById(productId));
			model.addAttribute("newProductSPQ", newProductSPQ);

			System.out.println("test bash in hasErrors");

			 return "addProductSPQ";
		}

		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		productService.addProductSPQ(newProductSPQ, productId);
		String url = String.format("redirect:/market/product/%s/addSPQ", productId);
//		redirect:/market/product/1260/addSPQ
		return url;
		// return "redirect:/market/products";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
//		binder.setValidator(existingSizePriceValidator);
		binder.setValidator(productSPQValidator);
		binder.setAllowedFields("productId", "size", "price", "unitsInStock");
	}

}
