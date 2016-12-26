package com.mystore.dao;

import com.mystore.model.Cart;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "Cart")
public interface CartDao extends PagingAndSortingRepository<Cart, Long> {

    Cart findByProductId(Long productId);

}
