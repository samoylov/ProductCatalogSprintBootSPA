package com.mystore.controller;

import com.mystore.dao.CartDao;
import com.mystore.dao.ProductDao;
import com.mystore.model.Cart;
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

    @RequestMapping(value = "/modifyCartItemQuantity", method = RequestMethod.GET)
    public boolean modifyCartItemQuantity(
            @RequestParam("cartItemId") String cartItemId,
            @RequestParam("delta") String delta) {

        Long id = Long.parseLong(cartItemId);
        Cart cartItem = cartDao.findOne(id);

        cartItem.setQuantity(cartItem.getQuantity() + Integer.parseInt(delta));

        if (cartItem.getQuantity() == 0)
            cartDao.delete(id);
        else
            cartDao.save(cartItem);

        return true;
    }

    @RequestMapping(value = "/getCart", method = RequestMethod.GET)
    public CartRepresentation getCart() {
        return new CartRepresentation(cartDao.findAll());
    }

    @RequestMapping(value = "/deleteCartItem", method = RequestMethod.GET)
    public boolean deleteCartItem(@RequestParam("cartItemId") String cartItemId) {
        cartDao.delete(Long.parseLong(cartItemId));
        return true;
    }


}
