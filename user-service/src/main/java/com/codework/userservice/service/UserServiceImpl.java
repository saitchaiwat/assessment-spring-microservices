package com.codework.userservice.service;

import com.codework.userservice.entity.UserEntity;
import com.codework.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserEntity> getAllUsers(int offset, int pageSize) {
        return userRepository.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public void createCustomer(UserEntity user) {
        user.setRole(UserEntity.Role.customer);
        userRepository.save(user);
    }

    @Override
    public void createStaff(UserEntity user) {
        user.setRole(UserEntity.Role.Staff);
        userRepository.save(user);
    }

    @Override
    public void createManager(UserEntity user) {
        user.setRole(UserEntity.Role.Manager);
        userRepository.save(user);
    }
}
