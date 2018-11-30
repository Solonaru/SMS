package com.project.sms.entities.item;

import java.util.List;
import java.util.Optional;

public interface IProductService {
	
	public Optional<Product> findProductById(int productId);

	public List<Product> findAllProducts();

	public void insertProduct(Product product);

	public void updateProduct(Product product);

	public void deleteProductById(int productId);
}
