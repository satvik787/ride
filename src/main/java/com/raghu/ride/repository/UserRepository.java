package com.raghu.ride.repository;

import org.springframework.stereotype.Repository;
import com.raghu.ride.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPhone(String phoneNumber);
}