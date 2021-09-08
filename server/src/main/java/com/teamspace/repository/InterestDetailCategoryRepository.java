package com.teamspace.repository;

import com.teamspace.domain.InterestDetailCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestDetailCategoryRepository extends CrudRepository<InterestDetailCategory, Long> {

}
