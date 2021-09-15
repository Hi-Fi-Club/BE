package com.teamspace.repository;

import com.teamspace.domain.UserInterest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterestRepository extends CrudRepository<UserInterest, Long> {
}
