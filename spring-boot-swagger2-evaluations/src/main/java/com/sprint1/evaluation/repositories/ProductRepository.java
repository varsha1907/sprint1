package com.sprint1.evaluation.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sprint1.evaluation.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}
