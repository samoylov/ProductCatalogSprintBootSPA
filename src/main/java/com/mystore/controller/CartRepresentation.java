package com.mystore.controller;

import com.mystore.model.Cart;
import com.mystore.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartRepresentation {

    private ArrayList<Cart> cartItems;
    private double totalAmount;
    private int numberOfProducts;
    private String cartStatus;

    public CartRepresentation(Iterable<Cart> cartItems) {
        this.cartItems = new ArrayList<Cart>();
        for (Cart cartItem : cartItems)
            this.cartItems.add(cartItem);
    }

    public Iterable<Cart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<Cart> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalAmount() {
        totalAmount = 0;
        for (Cart cartItem : cartItems)
            totalAmount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCartStatus() {
        cartStatus = getNumberOfProducts() == 0 ?
                "Cart is empty" :
                String.format("%.2f USD, %d products", getTotalAmount(), getNumberOfProducts());
        return cartStatus;
    }

    public void setCartStatus(String cartStatus) {
        this.cartStatus = cartStatus;
    }

    public int getNumberOfProducts() {
        return cartItems.size();
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    @Override
    public String toString() {
        System.out.println(cartItems.toString());
        return "CartRepresentation{" +
                "cartItems=" + cartItems.toString() +
                ", totalAmount=" + getTotalAmount() +
                ", numberOfProducts=" + getNumberOfProducts() +
                ", cartStatus=" + getCartStatus() +
                '}';
    }
}
