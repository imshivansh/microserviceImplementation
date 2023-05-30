package com.example.APIGateway.FallbackController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class fallbackController {

    @GetMapping("/fallbackUserService")
    public String fallbackUserServiceMethod(){
        return "User-service took more than expected to respond "+"Please, Try again later";
    }

    @GetMapping("/fallbackDepartmentService")
    public String fallbackDepartmentServiceMethod(){
        return "Department-service took more than expected to respond "+"Please, Try again later";
    }
}
