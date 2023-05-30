package com.microserviceproject.departmentservice.service;

import com.microserviceproject.departmentservice.entity.Department;
import com.microserviceproject.departmentservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public Department createDepartment(Department department){
        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long departmentId) {

        return departmentRepository.findByDepartmentId(departmentId);
    }
}
