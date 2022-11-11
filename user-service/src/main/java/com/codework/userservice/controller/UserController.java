package com.codework.userservice.controller;

import com.codework.userservice.dto.UserClientDTO;
import com.codework.userservice.dto.UserDTO;
import com.codework.userservice.entity.UserEntity;
import com.codework.userservice.response.ResponseHandler;
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
    public ResponseEntity<Object> getAllUsers(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "25") int pageSize) {
        try {
            Page<UserEntity> users = userService.getAllUsers(offset, pageSize);

            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, users);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getCustomer/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserClientDTO> getCustomer(@PathVariable Long id) {
        try {
            UserClientDTO user = userService.getCustomerById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getStaff/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStaff(@PathVariable Long id) {
        try {
            UserClientDTO user = userService.getStaffById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getManager/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserClientDTO> getManager(@PathVariable Long id) {
        try {
            UserClientDTO user = userService.getManagerById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/createCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCustomer(@RequestBody UserDTO user) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            UserEntity convertToUser = modelMapper.map(user, UserEntity.class);
            userService.createCustomer(convertToUser);

            return ResponseHandler.generateResponse("Customer created successfully", HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/createStaff", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createStaff(@RequestBody UserDTO user) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            UserEntity convertToUser = modelMapper.map(user, UserEntity.class);
            userService.createStaff(convertToUser);

            return ResponseHandler.generateResponse("Staff created successfully", HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/createManager", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createManager(@RequestBody UserDTO user) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            UserEntity convertToUser = modelMapper.map(user, UserEntity.class);
            userService.createManager(convertToUser);

            return ResponseHandler.generateResponse("Manager created successfully", HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
