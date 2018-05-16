package com.packt.naturebesttouch.controller;

import java.util.List;
import java.util.Map;

//import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.packt.naturebesttouch.domain.Product;
import com.packt.naturebesttouch.domain.ProductSizePriceQuantity;
import com.packt.naturebesttouch.service.ProductService;

@Controller
@RequestMapping("market")
public class ProductController {

	// @Autowired
	// private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@RequestMapping("/products")
	public String list(Model model) {
		// Product iphone = new Product("P1234", "iPhone 6s", new BigDecimal(500));
		// iphone.setDescription("Apple iPhone 6s smartphone with 4.00-inch 640x1136
		// display and 8-megapixel rear camera");
		// iphone.setCategory("Smartphone");
		// iphone.setManufacturer("Apple");
		// iphone.setUnitsInStock(1000);

		// model.addAttribute("product", iphone);

		model.addAttribute("products", productService.getAllProducts());

		// model.addAttribute("greeting", "Welcome to my Final Project!");
		// model.addAttribute("tagline", "The one and only amazing web store");
		return "products";
	}

	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
		productService.updateAllStock();
		return "redirect:/market/products";
	}

	@RequestMapping("/webshop/products")
	public String list2(Model model) {

		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	// http://localhost:8080/webstore/market/products/Tablet
	@RequestMapping("/products/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		return "products";
	}

	// http://localhost:8080/webstore/market/products/filter/params;brands=Google,Dell;categories=Tablet,Laptop.
	// @RequestMapping("/products/filter/{params}")
	// public String getProductsByFilter(Model model,
	// @MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams) {
	// model.addAttribute("products",
	// productService.getProductsByFilter(filterParams));
	// return "products";
	// }
	// http://localhost:8080/webstore/market/products/filter/params;brands=Goo
	// gle,Dell;categories=Tablet,Laptop/specification;dimention=10,20,15;color=red,green,blue

	// @RequestMapping("/products/filter/{params}/{specification}")
	// public String filter(@MatrixVariable(pathVar="params")
	// Map<String,List<String>> criteriaFilter, @MatrixVariable(pathVar="
	// specification") Map<String,List<String>> specFilter, Model model)

	// http://localhost:8080/webstore/market/product?id=P1234
	@RequestMapping("/product")
	public String getProductsById(Model model, @RequestParam("id") String productId) {
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}

	// http://localhost:8080/webstore/productss?category=laptop&price=700
	// @RequestMapping("/productss")
	// public String getProducts(Model model, @RequestParam("category") String
	// category,
	// @RequestParam("price") String price) {
	// model.addAttribute("product",
	// productService.getProductBycategoryAndPrice(category, price));
	// return "product";
	//
	// }

	// http://localhost:8080/webstore/products/Tablet/price;low=200;high=400?brand=Google
	// market/products/Tablet/price;low=200;high=400?brands=Google

	// @RequestMapping("/products/{category}/{params}")
	// public String filterProducts(@PathVariable("category") String category,
	// @MatrixVariable(pathVar = "params") Map<String, String> filterParams,
	// @RequestParam("brand") String brand, Model model) {
	// model.addAttribute("products",
	// productService.getProductsByCategoryByFilters(category, filterParams,
	// brand));
	// return "products";
	// }

	// @RequestMapping("/products/{category}/{params}")
	// public String filterProducts(@PathVariable("category") String
	// productCategory,
	// @MatrixVariable(pathVar = "params") Map<String, String> filterParams,
	// @RequestParam("brands") String productBrand, Model model) {
	// model.addAttribute("products",
	// productService.getProductsByCategoryByFilter(productCategory, filterParams,
	// productBrand));
	// return "products";
	// }

	// http://localhost:8080/naturebesttouch/market/products/add
	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}

	// http://localhost:8080/naturebesttouch/market/products/add
	@RequestMapping(value = "/products/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result) {
		// productService.addProduct(newProduct);
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		String url = String.format("redirect:/market/product/addSPQ?id=%s", productService.addProduct(newProduct));
		return url;
		// return "redirect:/market/products";
	}

	// http://localhost:8080/naturebesttouch/market/product/addSPQ?id=1238
	@RequestMapping(value = "/product/addSPQ", method = RequestMethod.GET)
	public String getAddNewProductSPQForm(Model model, @RequestParam("id") String productId) {
		model.addAttribute("product", productService.getProductById(productId));
		ProductSizePriceQuantity newProductSPQ = new ProductSizePriceQuantity();
		model.addAttribute("newProductSPQ", newProductSPQ);
		return "addProductSPQ";
	}

	@RequestMapping(value = "/product/addSPQ", method = RequestMethod.POST)
	public String processAddNewProductSPQForm(@ModelAttribute("newProductSPQ") ProductSizePriceQuantity newProductSPQ,
			@RequestParam("id") String productId, BindingResult result) {
		productService.addProductSPQ(newProductSPQ, productId);
		String url = String.format("redirect:/market/product/addSPQ?id=%s", productId);
		return url;
		// return "redirect:/market/products";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("name", "description", "category");
	}
}
