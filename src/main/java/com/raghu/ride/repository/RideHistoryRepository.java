package com.raghu.ride.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.raghu.ride.entity.RideHistoryEntity;

@Repository
public interface RideHistoryRepository extends JpaRepository<RideHistoryEntity, Long> {
    public List<RideHistoryEntity> findByUserId(Long userId);
}
