package com.mystore.dao;

import com.mystore.model.Facet;
import com.mystore.model.FacetType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "Facet", excerptProjection = FacetDaoProjection.class)
public interface FacetDao extends CrudRepository<Facet, Long> {

    Facet findOneByName(String name);
    Facet findOneByNameAndFacetTypeId(String name, Long id);

}
