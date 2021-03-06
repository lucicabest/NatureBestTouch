package com.packt.naturebesttouch.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
//import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.packt.naturebesttouch.domain.Product;
import com.packt.naturebesttouch.domain.ProductSizePriceQuantity;
import com.packt.naturebesttouch.domain.repository.ProductRepository;
import com.packt.naturebesttouch.exception.ProductNotFoundException;

//import com.packt.webstore.domain.repository.impl.InMemoryProductRepository.ProductMapper;

@Repository
public class InMemoryProductRepository implements ProductRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	// @Override
	public List<Product> getAllProducts() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Product> result = jdbcTemplate.query("SELECT * FROM products", params, new ProductMapper());
		return result;
	}

	// public List<ProductSizePriceQuantity> getAllSPQ(String productPriceId) {
	//
	// String SQL = "SELECT * FROM PRICES WHERE ID =:id";
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("id", productPriceId);
	//
	// List<ProductSizePriceQuantity> result = jdbcTemplate.query("SELECT * FROM
	// prices", params, new ProductSizePriceQuantityMapper());
	// return result;
	// }

	private class ProductMapper implements RowMapper<Product> {

		// private ProductSizePriceQuantityMapper productSPQMapper;

		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

			Product product = new Product();

			String id = rs.getString("ID");
			// Cart cart = new Cart(id);
//			String SQL = String.format("SELECT * FROM PRICES WHERE PRODUCT_ID = '%s' ORDER BY SIZE", id);
			String SQL = String.format("SELECT PRODUCTS.NAME, PRICES.ID, PRICES.PRODUCT_ID, PRICES.SIZE, PRICES.PRICE, PRICES.UNITS_IN_STOCK, PRICES.UNITS_IN_ORDER FROM PRODUCTS INNER JOIN PRICES ON PRODUCTS.ID = PRICES.PRODUCT_ID WHERE PRICES.PRODUCT_ID = '%s' ORDER BY SIZE", id);
			List<ProductSizePriceQuantity> productsSPQ = jdbcTemplate.query(SQL, new ProductSizePriceQuantityMapper());
			// cart.setCartItems(cartItems);
			// return cart;

			product.setProductId(rs.getString("ID"));
			product.setName(rs.getString("NAME"));
			product.setUnitSPQ(productsSPQ);
			product.setDescription(rs.getString("DESCRIPTION"));
			// product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
			// product.setManufacturer(rs.getString("MANUFACTURER"));
			product.setCategory(rs.getString("CATEGORY"));
			// product.setCondition(rs.getString("PRODUCT_CONDITION"));
			// product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
			// product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
			// product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
			return product;
		}
	}

	// private static final class ProductSizePriceQuantityMapper implements
	// RowMapper<ProductSizePriceQuantity> {
	//
	// public ProductSizePriceQuantity mapRow(ResultSet rs, int rowNum) throws
	// SQLException {
	// ProductSizePriceQuantity productSPQ = new ProductSizePriceQuantity();
	// productSPQ.setPriceId(rs.getString("ID"));
	// productSPQ.setSize(rs.getFloat("SIZE"));
	// productSPQ.setPrice(rs.getBigDecimal("DESCRIPTION"));
	//// product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
	//// product.setManufacturer(rs.getString("MANUFACTURER"));
	//// product.setCategory(rs.getString("CATEGORY"));
	//// product.setCondition(rs.getString("PRODUCT_CONDITION"));
	// productSPQ.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
	// productSPQ.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
	//// product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
	// return productSPQ;
	// }
	// }

	@Override
	public void updateStock(String id, long noOfUnits) {
		String SQL = "UPDATE PRICES SET UNITS_IN_STOCK = :unitsInStock WHERE ID = :id";
		Map<String, Object> params = new HashMap<>();
		params.put("unitsInStock", noOfUnits);
		params.put("id", id);
		jdbcTemplate.update(SQL, params);
	}

	// @Override
	public List<Product> getProductsByCategory(String category) {
		String SQL = "SELECT * FROM PRODUCTS WHERE CATEGORY =:category";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("category", category);
		return jdbcTemplate.query(SQL, params, new ProductMapper());
	}
	//
	// public List<Product> getProductsByFilter(Map<String, List<String>>
	// filterParams) {
	// String SQL = "SELECT * FROM PRODUCTS WHERE CATEGORY IN (:categories) AND
	// MANUFACTURER IN (:brands)";
	// return jdbcTemplate.query(SQL, filterParams, new ProductMapper());
	// }

	@Override
	public Product getProductById(String productID) {
		String SQL = "SELECT * FROM PRODUCTS WHERE ID =:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", productID);
		 try {
		 return jdbcTemplate.queryForObject(SQL, params, new ProductMapper());
		 } catch (DataAccessException e) {
		 throw new ProductNotFoundException(productID);
		 }
//		return jdbcTemplate.queryForObject(SQL, params, new ProductMapper());
	}

	// @Override
	// public Product getProductBycategoryAndPrice(String category, String price) {
	// String SQL = "SELECT * FROM PRODUCTS WHERE CATEGORY =:category AND UNIT_PRICE
	// =:price";
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("category", category);
	// params.put("price", price);
	// return jdbcTemplate.queryForObject(SQL, params, new ProductMapper());
	// }

	// @Override
	// public List<Product> getProductsByCategoryByFilters(String category,
	// Map<String, String> filterParams, String brand) {
	// String SQL = "SELECT * FROM PRODUCTS WHERE MANUFACTURER =:brand AND CATEGORY
	// =:category AND UNIT_PRICE BETWEEN :low AND :high";
	// Map<String, String> params = new HashMap<String, String>();
	// params.put("category", category);
	// params.put("brand", brand);
	// params.putAll(filterParams);
	// return jdbcTemplate.query(SQL, params, new ProductMapper());
	// }

	// @Override
	// public List<Product> getProductsByCategoryByFilter(String category,
	// Map<String, String> filterParams,
	// String brands) {
	// // TODO Auto-generated method stub
	//
	// String SQL = "SELECT * FROM PRODUCTS WHERE CATEGORY IN (:category) AND
	// MANUFACTURER IN (:brands) AND UNIT_PRICE BETWEEN (:low) AND (:high) ";
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("category", category);
	// params.put("brands", brands);
	// params.putAll(filterParams);
	// return jdbcTemplate.query(SQL, params, new ProductMapper());
	// }

	@Override
	public String addProduct(Product product) {
		// String SQL = "INSERT INTO PRODUCTS (ID, " + "NAME," + "DESCRIPTION," +
		// "UNIT_PRICE," + "MANUFACTURER,"
		// + "CATEGORY," + "CONDITION," + "UNITS_IN_STOCK," + "UNITS_IN_ORDER," +
		// "DISCONTINUED) "
		// + "VALUES (:id, :name, :desc, :price, :manufacturer, :category, :condition,
		// :inStock, :inOrder, :discontinued)";
		String SQL = "INSERT INTO PRODUCTS (NAME," + "DESCRIPTION," + "CATEGORY) " + "VALUES (:name, :desc, :category)";
		Map<String, Object> params = new HashMap<>();
//		params.put("id", product.getProductId());
		params.put("name", product.getName());
		params.put("desc", product.getDescription());
		// params.put("price", product.getUnitPrice());

		params.put("category", product.getCategory());

		// params.put("inStock", product.getUnitsInStock());
		// params.put("inOrder", product.getUnitsInOrder());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(SQL, new MapSqlParameterSource(params), keyHolder);
		
		//		for (ProductSizePriceQuantity productSPQ : product.getUnitSPQ()) {
//			String SQL_SPQ = "INSERT INTO PRICES (PRODUCT_ID," + "SIZE," + "PRICE," + "UNITS_IN_STOCK,"
//					+ "UNITS_IN_ORDER) " + "VALUES (:id, :size, :price, :unitsInStock, :unitsInOrder)";
////		params.put("productId", product.getProductId());
//			params.put("size", productSPQ.getSize());
//			params.put("price", productSPQ.getPrice());
//			params.put("unitsInStock", productSPQ.getUnitsInStock());
//			params.put("unitsInOrder", productSPQ.getUnitsInOrder());
//			jdbcTemplate.update(SQL, params);
//		}
		return keyHolder.getKey().toString();
	}

	@Override
	public void addProductSPQ(ProductSizePriceQuantity productSPQ, String productId) {
		Map<String, Object> params = new HashMap<>();
		String SQL_SPQ = "INSERT INTO PRICES (PRODUCT_ID," + "SIZE," + "PRICE," + "UNITS_IN_STOCK,"
				+ "UNITS_IN_ORDER) " + "VALUES (:productId, :size, :price, :unitsInStock, :unitsInOrder)";
		params.put("productId", productId);
		params.put("size", productSPQ.getSize());
		params.put("price", productSPQ.getPrice());
		params.put("unitsInStock", productSPQ.getUnitsInStock());
		params.put("unitsInOrder", 0);
		jdbcTemplate.update(SQL_SPQ, params);
	}

}
