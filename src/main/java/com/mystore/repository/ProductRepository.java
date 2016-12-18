package com.mystore.repository;


import com.mystore.domain.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "Product")
public interface ProductRepository extends PagingAndSortingRepository <Product, Long>{

    // List<Product> findByName(@Param("name") String name);

}
