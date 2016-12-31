package com.mystore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity

public class FacetType {

    @Column(unique = true)
    String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "facetType")
    private List<Facet> facets;

    public FacetType() {
    }

    public FacetType(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

}
