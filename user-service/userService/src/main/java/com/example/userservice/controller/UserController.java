package com.example.userservice.controller;

import com.example.userservice.entity.User;
import com.example.userservice.model.Department;
import com.example.userservice.model.UserDTO;
import com.example.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/")
    public User createUser(@RequestBody  User user){
        log.info("creating new user");
        return userService.createUser(user);
    }

@GetMapping("/{id}")
@CircuitBreaker(name="department",fallbackMethod = "userFallbackMethod")
@TimeLimiter(name="department")
@Retry(name="department")
public CompletableFuture<UserDTO> getUserWithDepartment(@PathVariable("id") Long userId){
        log.info("inside getUserWithDepartment method of user controller");
        return CompletableFuture.supplyAsync(()->userService.getUserWithDepartment(userId));
    }

    public CompletableFuture<UserDTO> userFallbackMethod(Long userId, Throwable t) throws RuntimeException{
        log.info("Inside user-fallbackMethod for Department Service");
        {
            log.error("Error occurred while getting user with department for ID: {}", userId, t);
// Return a default UserDTO indicating the fallback scenario
            User user = userService.findByUserId(userId);
            Department department = new Department();
            department.setDepartmentAddress("No departAddress Found");
            department.setDepartmentCode("No departmentCode found");
            department.setDepartmentId(null);
            department.setDepartmentName("Implementing fallback error message,An Error occurred while getting the department details for the given user");
            return CompletableFuture.supplyAsync(()->new UserDTO(user,department));

    }
}
}

