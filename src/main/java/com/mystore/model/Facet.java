package com.mystore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Facet implements Comparable<Facet>{

    // todo: Entity (many)-> Name (many)-> Facet(Value) - for future

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private FacetType facetType;

    @ManyToMany
    @JoinTable(name = "products_facets", joinColumns = {
            @JoinColumn(name = "product", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "facet", referencedColumnName = "id", nullable = false)})
    private List<Product> products;

    public Facet() {
    }

    public Facet(String name, FacetType facetType) {
        this.name = name;
        this.facetType = facetType;
    }

    @Override
    public int compareTo(Facet o) {
        return this.name.compareTo(o.name);
    }
}
