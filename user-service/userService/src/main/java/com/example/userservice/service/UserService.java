package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.model.Department;
import com.example.userservice.model.UserDTO;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    public User createUser(User user){
        return userRepository.save(user);
    }


    public UserDTO getUserWithDepartment(Long userId) {
        UserDTO userDTO = new UserDTO();
        User user = userRepository.findByUserId(userId);
        Long departmentId = user.getDepartmentId();
       Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+departmentId, Department.class);
        userDTO.setDepartment(department);
        userDTO.setUser(user);
        return userDTO;




    }

    public User findByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }
}
