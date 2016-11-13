/**
 * @auther Rakesh
 * @time Sep 7, 2016
 */

package com.rkumbhare.restapi.vo;

public class Product {
	private int productId;
	private String productName;
	private String productCode;
	private String description;
	private double price;
	private int available;
	private double rating;

	public Product() {
	}

	public Product(int productId, String productName, String productCode, String description, double price,
			int available, double rating) {
		this.productId = productId;
		this.productName = productName;
		this.productCode = productCode;
		this.description = description;
		this.price = price;
		this.available = available;
		this.rating = rating;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productCode=" + productCode
				+ ", description=" + description + ", price=" + price + ", available=" + available + ", rating="
				+ rating + "]";
	}

}
