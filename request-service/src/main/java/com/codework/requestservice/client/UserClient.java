package com.codework.requestservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/api/user/getCustomer/{id}")
    UserModel getCustomerById(@PathVariable Long id);

    @GetMapping("/api/user/getStaff/{id}")
    UserModel getStaffById(@PathVariable Long id);

    @GetMapping("/api/user/getManager/{id}")
    UserModel getManagerById(@PathVariable Long id);
}
