package com.mystore.dao;

import com.mystore.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "Product")
public interface ProductDao extends PagingAndSortingRepository<Product, Long> {

}
