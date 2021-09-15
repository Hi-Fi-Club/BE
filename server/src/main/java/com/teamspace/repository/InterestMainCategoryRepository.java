package com.teamspace.repository;

import com.teamspace.domain.InterestMainCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestMainCategoryRepository extends CrudRepository<InterestMainCategory, Long> {
}
