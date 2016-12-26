package com.mystore.dao;

import com.mystore.model.Gender;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "Gender")
public interface GenderDao extends PagingAndSortingRepository<Gender, Long> {

    Gender findOneByName(String name);

}
