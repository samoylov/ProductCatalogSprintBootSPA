package com.mystore.configuration;

import com.mystore.model.Cart;
import com.mystore.model.Product;
import com.mystore.model.Facet;
import com.mystore.model.FacetType;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
public class MyRestConfiguration extends RepositoryRestMvcConfiguration {

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Product.class);
        config.exposeIdsFor(Cart.class);
        config.exposeIdsFor(Facet.class);
        config.exposeIdsFor(FacetType.class);
    }
}