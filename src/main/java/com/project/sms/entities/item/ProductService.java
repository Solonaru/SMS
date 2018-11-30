package com.project.sms.entities.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{
	
	@Autowired
	private IProductRepository productRepository;

	public Optional<Product> findProductById(int productId) {
		return productRepository.findById(productId);
	}

	public List<Product> findAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	public void insertProduct(Product product) {
		productRepository.save(product);
	}

	public void updateProduct(Product product) {
		productRepository.save(product);
	}

	public void deleteProductById(int productId) {
		productRepository.deleteById(productId);
	}
}
