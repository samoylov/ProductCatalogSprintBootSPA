package com.mystore.controller;

import com.mystore.model.Cart;
import com.mystore.model.Product;

import java.util.ArrayList;

public class CartRepresentation {

    private ArrayList<CartItem> cartItems;
    private double totalAmount;
    private int numberOfProducts;
    private String cartStatus;

    public CartRepresentation(Iterable<Cart> carts) {
        cartItems = new ArrayList<>();
        totalAmount = 0;

        for (Cart cart : carts) {
            Product product = cart.getProduct();
            this.cartItems.add(new CartItem(cart, product));
            totalAmount += product.getPrice() * cart.getQuantity();
        }
        numberOfProducts = cartItems.size();
        cartStatus = getNumberOfProducts() == 0 ?
                "Cart is empty" :
                String.format("%.2f USD, %d products", getTotalAmount(), getNumberOfProducts());
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public String getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(String cartStatus) {
        this.cartStatus = cartStatus;
    }

    class CartItem {

        Cart cartItem;
        Product product;
        double amount;

        public CartItem(Cart cartItem, Product product) {
            this.cartItem = cartItem;
            this.product = product;
            this.amount = cartItem.getQuantity() * product.getPrice();
        }

        public Cart getCartItem() {
            return cartItem;
        }

        public void setCartItem(Cart cartItem) {
            this.cartItem = cartItem;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "CartItem{" +
                    "cartItem=" + cartItem +
                    ", product=" + product +
                    ", amount=" + amount +
                    '}';
        }
    }

}
