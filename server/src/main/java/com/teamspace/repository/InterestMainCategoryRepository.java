package com.teamspace.repository;

import com.teamspace.domain.InterestMainCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestMainCategoryRepository extends CrudRepository<InterestMainCategory, Long> {

    @Override
    List<InterestMainCategory> findAll();
}
