package com.instac.userservice.repositories;

import com.instac.userservice.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    UserEntity findByUserEmail(String userEmail);
    UserEntity findByUserPhone(Integer userPhone);
}
