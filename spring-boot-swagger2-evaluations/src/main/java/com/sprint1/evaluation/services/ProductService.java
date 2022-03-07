package com.sprint1.evaluation.services;

import com.sprint1.evaluation.domain.Product;

public interface ProductService {
	
	 	Iterable<Product> listAllProducts();

	    Product getProductById(Integer id);

	    Product saveProduct(Product product);

	    void deleteProduct(Integer id);
}
