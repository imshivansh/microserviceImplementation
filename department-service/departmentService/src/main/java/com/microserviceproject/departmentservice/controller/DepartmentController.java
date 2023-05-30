package com.microserviceproject.departmentservice.controller;

import com.microserviceproject.departmentservice.entity.Department;
import com.microserviceproject.departmentservice.repository.DepartmentRepository;
import com.microserviceproject.departmentservice.service.DepartmentService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping("/")
    public Department createDepartment(@RequestBody Department department){
        log.info("Inside saveDepartment method of DepartmentController");
        return departmentService.createDepartment(department);
    }

    @GetMapping("/{id}")
    @SneakyThrows
    public Department findDepartmentById(@PathVariable("id") Long departmentId){
        log.info("Inside findDepartmentById method of DepartmentController");
        return departmentService.findDepartmentById(departmentId);
    }


}
