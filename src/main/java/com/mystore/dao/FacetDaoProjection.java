package com.mystore.dao;

import com.mystore.model.Facet;
import com.mystore.model.FacetType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "FacetDaoProjection", types = {Facet.class})
public interface FacetDaoProjection {

    long getId();
    String getName();
    FacetType getFacetType();

}
