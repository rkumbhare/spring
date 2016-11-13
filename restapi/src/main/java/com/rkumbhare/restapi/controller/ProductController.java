/**
 * @auther Rakesh
 * @time Sep 7, 2016
 */

package com.rkumbhare.restapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rkumbhare.restapi.vo.Product;

@RestController
public class ProductController {

	private List<Product> productList;
	private int seq = 1000;

	@PostConstruct
	public void init() {
		productList = new ArrayList<Product>();
		productList.add(new Product(++seq, "Dettol Soap", "Prod" + seq, "Dettol Soap", 25.00, 25, 5));
		productList.add(new Product(++seq, "Lifeboy Soap", "Prod" + seq, "Lifeboy Soap", 20.00, 10, 3));
		productList.add(new Product(++seq, "Lux Soap", "Prod" + seq, "Lux Soap", 40.50, 20, 2));
		productList.add(new Product(++seq, "Santoor Soap", "Prod" + seq,"Santoor Soap", 40.50,40,4));
		productList.add(new Product(++seq, "Dove Soap", "Prod" + seq, "Dove Soap", 60.50, 25, 4));
		productList.add(new Product(++seq, "Fair & Lovely cream", "Prod" + seq, "Fair & Lovely cream", 70.00, 10, 2));
		productList.add(new Product(++seq, "Vicco cream", "Prod" + seq, "Vicco Cream", 75.00, 25, 4));
		productList.add(new Product(++seq, "Colgate ToothPaste", "Prod" + seq, "Colgate ToothPaste", 40.50, 20, 2));
		productList.add(new Product(++seq, "Dabur Hair Oil", "Prod" + seq, "Hair Oil", 45.00, 20, 4));
		productList.add(new Product(++seq, "Gellete Shaver", "Prod" + seq, "Gellete Shaver", 95.00, 50, 4));
	}

	@PreDestroy
	public void destroy() {
	}

	@RequestMapping(value = "/product", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {
			"application/json"})
	@ResponseBody
	public Product createProduct(@RequestBody Product product) {
		System.out.println("Creating new Product : " + product);
		product.setProductId(++seq);
		product.setProductCode("Prod" + seq);
		productList.add(product);
		return product;
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST, consumes = {"application/json"}, produces = {
			"application/json"})
	@ResponseBody
	public Product updateProduct(@RequestBody Product product) {
		System.out.println("Updating Product : " + product.getProductId());
		this.productList.stream().forEach(prod -> {
			if (prod.getProductId() == product.getProductId()) {
				prod.setProductName(product.getProductName());
				prod.setDescription(product.getDescription());
				prod.setPrice(product.getPrice());
				prod.setAvailable(product.getAvailable());
				prod.setRating(product.getRating());
			}
		});
		return product;
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET, produces = {"application/json"})
	@ResponseBody
	public List<Product> getProduct() {
		System.out.println("fetch all products");
		return productList;
	}

	@RequestMapping(value = "/product/{productId}", method = RequestMethod.GET, produces = {"application/json"})
	@ResponseBody
	public Product getProduct(@PathVariable("productId") int productId) {
		System.out.println("fetch product by id : " + productId);
		Optional<Product> productOpt = productList.stream().filter(product -> product.getProductId() == productId)
				.findAny();
		if (productOpt.isPresent()) {
			return productOpt.get();
		}
		return null;
	}

	@RequestMapping(value = "/product/search", method = RequestMethod.GET, produces = {"application/json"})
	@ResponseBody
	public List<Product> searchProduct(@RequestParam("query") String query) {
		System.out.println("Searching Product by query : " + query);
		List<Product> filtered = productList.stream()
				.filter(product -> product.getProductName().toLowerCase().contains(query.toLowerCase()))
				.collect(Collectors.toList());
		System.out.println(filtered);
		return filtered;
	}

}
