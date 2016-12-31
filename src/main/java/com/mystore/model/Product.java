package com.mystore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private String photo;
    private double price;

    @ManyToMany
    @JoinTable(name = "products_facets", joinColumns = {
            @JoinColumn(name = "facet", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "product", referencedColumnName = "id", nullable = false)})
    private List<Facet> facets;

    @OneToMany(mappedBy = "product")
    private List<Cart> cartList;

    public Product() {
    }

    public Product(String name, String description, String photo, double price) {
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.price = price;
    }

    public void setFacets(List<Facet> facets) {
        this.facets = facets;
    }
}
