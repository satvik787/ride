package com.raghu.ride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.raghu.ride.entity.RideDetailsEntity;

public interface RideDetailsRepository extends JpaRepository<RideDetailsEntity, Long> {

}
