package com.mystore.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(path = "Cart")
public interface CartDao extends PagingAndSortingRepository<Cart, Long> {

    public Cart findByProductId(Long productId);

}
