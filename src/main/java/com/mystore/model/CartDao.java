package com.mystore.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "Cart")
public interface CartDao extends PagingAndSortingRepository<Cart, Long> {

    public Cart findByProductId(Long productId);


}
