package com.teamspace.repository;

import com.teamspace.domain.UserInterest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterestRepository extends CrudRepository<UserInterest, Long> {

    List<UserInterest> findAllByUser(Long userId);
}
