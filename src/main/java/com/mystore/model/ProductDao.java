package com.mystore.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "Product")
public interface ProductDao extends PagingAndSortingRepository<Product, Long> {

}
