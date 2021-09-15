package com.teamspace.repository;

import com.teamspace.domain.InterestDetailCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestDetailCategoryRepository extends CrudRepository<InterestDetailCategory, Long> {

    List<InterestDetailCategory> findAllByInterestMainCategory_Id(Long mainId);
}
