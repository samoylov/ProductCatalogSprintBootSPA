package com.mystore.controller;

import com.mystore.model.Gender;
import com.mystore.model.Product;
import com.mystore.model.ProductType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class ProductListRepresentation {

    Iterable<Product> products;
    TreeSet<Gender> genders;
    TreeSet<ProductType> productTypes;

    public ProductListRepresentation(Iterable<Product> products) {
        this.products = products;
        this.genders = new TreeSet<>();
        this.productTypes = new TreeSet<>();

        products.forEach(product -> {
            genders.add(product.getGender());
            productTypes.add(product.getProductType());
        });
    }

    public Iterable<Product> getProducts() {
        return products;
    }

    public void setProducts(Iterable<Product> products) {
        this.products = products;
    }

    public TreeSet<Gender> getGenders() {
        return genders;
    }

    public void setGenders(TreeSet<Gender> genders) {
        this.genders = genders;
    }

    public TreeSet<ProductType> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(TreeSet<ProductType> productTypes) {
        this.productTypes = productTypes;
    }

    @Override
    public String toString() {
        return "ProductListRepresentation{" +
                "products=" + products +
                ", genders=" + genders +
                ", productTypes=" + productTypes +
                '}';
    }
}
