package com.codework.userservice.repository;

import com.codework.userservice.dto.UserClientDTO;
import com.codework.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(
            value = "SELECT id as userId, first_name as firstName, last_name as lastName FROM users WHERE id = ?1 AND role = ?2",
            nativeQuery = true
    )
    UserClientDTO findUserByNativeQuery(Long id, String role);
}
