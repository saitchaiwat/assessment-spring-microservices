package com.codework.userservice.service;

import com.codework.userservice.entity.UserEntity;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<UserEntity> getAllUsers(int offset, int pageSize);
    void createCustomer(UserEntity user);
    void createStaff(UserEntity user);
    void createManager(UserEntity user);
}
