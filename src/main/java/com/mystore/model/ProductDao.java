package com.mystore.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "Product")
public interface ProductDao extends PagingAndSortingRepository<Product, Long> {

}
