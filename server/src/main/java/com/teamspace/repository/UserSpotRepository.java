package com.teamspace.repository;

import com.teamspace.domain.UserSpot;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserSpotRepository extends CrudRepository<UserSpot, Long> {

    List<UserSpot> findAllByUser(Long userId);
}
