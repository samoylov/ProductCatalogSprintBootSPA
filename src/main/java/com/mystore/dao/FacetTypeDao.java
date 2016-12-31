package com.mystore.dao;

import com.mystore.model.FacetType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "FacetType")
public interface FacetTypeDao extends CrudRepository<FacetType, Long> {

    FacetType findOneByName(String name);

}
