package com.mystore.dao;

import com.mystore.model.ProductType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "ProductType")
public interface ProductTypeDao extends PagingAndSortingRepository<ProductType, Long> {

    ProductType findOneByName(String name);

}
