package com.codework.userservice.controller;

import com.codework.userservice.dto.UserDTO;
import com.codework.userservice.entity.UserEntity;
import com.codework.userservice.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public ResponseEntity<Page<UserEntity>> getAllUsers(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "25") int pageSize) {
        try {
            Page<UserEntity> users = userService.getAllUsers(offset, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/createCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createCustomer(@RequestBody UserDTO user) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            UserEntity convertToUser = modelMapper.map(user, UserEntity.class);
            userService.createCustomer(convertToUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/createStaff", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createStaff(@RequestBody UserDTO user) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            UserEntity convertToUser = modelMapper.map(user, UserEntity.class);
            userService.createStaff(convertToUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Staff created successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/createManager", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createManager(@RequestBody UserDTO user) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            UserEntity convertToUser = modelMapper.map(user, UserEntity.class);
            userService.createManager(convertToUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Manager created successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }
}
