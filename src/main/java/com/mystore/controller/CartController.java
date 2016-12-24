package com.mystore.controller;

import com.mystore.model.Cart;
import com.mystore.model.CartDao;
import com.mystore.model.ProductDao;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private CartDao cartDao;

    @RequestMapping(value = "/addToCart", method = RequestMethod.GET)
    public boolean addToCart(@RequestParam("productId") String productId) {

        Long id = Long.parseLong(productId);
        Cart cartItem = cartDao.findByProductId(id);

        if (cartItem == null)
            cartItem = new Cart(productDao.findOne(id), 1);
        else
            cartItem.setQuantity(cartItem.getQuantity() + 1);

        cartDao.save(cartItem);
        return true;
    }
    @RequestMapping(value = "/getCart", method = RequestMethod.GET)
    public CartRepresentation getCart() {

        return new CartRepresentation(cartDao.findAll());

    }



}
