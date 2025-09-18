package com.loanmanagement.loan_backend.controller;

import com.loanmanagement.loan_backend.model.Employee;
import com.loanmanagement.loan_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
     @Autowired
     private EmployeeRepository employeeRepository;

      // Get Employee details where Emp_Id
    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String empId) {
        Optional<Employee> employee = employeeRepository.findById(empId);

        return employee.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
