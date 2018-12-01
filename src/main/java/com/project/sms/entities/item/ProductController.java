package com.project.sms.entities.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.sms.utils.DisplayData;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Product> findProductById(@PathVariable("productId") int productId) {
		dataDisplay.printCrudInfo(productId); 
		return productService.findProductById(productId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getProducts() {
		dataDisplay.printCrudInfo(); 
		return productService.findAllProducts();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertProduct(@RequestBody Product product) {
		dataDisplay.printCrudInfo(); 
		productService.insertProduct(product);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateProduct(@RequestBody Product product) {
		dataDisplay.printCrudInfo(); 
		productService.updateProduct(product);
	}

	@RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteProduct(@PathVariable("productId") int productId) {
		dataDisplay.printCrudInfo(productId); 
		productService.deleteProductById(productId);
	}
}
