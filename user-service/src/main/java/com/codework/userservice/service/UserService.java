package com.codework.userservice.service;

import com.codework.userservice.dto.UserClientDTO;
import com.codework.userservice.entity.UserEntity;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<UserEntity> getAllUsers(int offset, int pageSize);
    UserClientDTO getCustomerById(Long id);
    UserClientDTO getStaffById(Long id);
    UserClientDTO getManagerById(Long id);
    void createCustomer(UserEntity user);
    void createStaff(UserEntity user);
    void createManager(UserEntity user);
}
