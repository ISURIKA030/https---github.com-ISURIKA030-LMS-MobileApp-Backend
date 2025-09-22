package com.loanmanagement.loan_backend.controller;

import com.loanmanagement.loan_backend.model.Employee;
import com.loanmanagement.loan_backend.repository.EmployeeRepository;
import com.loanmanagement.loan_backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /** Register a new employee and generate JWT token */
    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody Employee employee) {

        // Check if employee with the same empId or username already exists
        if (employeeRepository.existsByEmpId(employee.getEmpId())) {
            return ResponseEntity
                    .badRequest()
                    .body("Employee with this ID already exists.");
        }

        // Hash password
        String hashedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(hashedPassword);

        // Save employee
        Employee savedEmployee = employeeRepository.save(employee);

        // Generate JWT token using username (or empId)
        String token = JwtUtil.generateToken(savedEmployee.getEmpId());

        // Return JWT token
        return ResponseEntity.ok("Registration successful. JWT Token: " + token);
    }

    // Get Employee details where Emp_Id
    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String empId) {
        Optional<Employee> employee = employeeRepository.findById(empId);

        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Employee details by Emp_Id

    @PutMapping("/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String empId, @RequestBody Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            // Update fields
            employee.setEmpName(updatedEmployee.getEmpName());
            employee.setDepartment(updatedEmployee.getDepartment());
            employee.setContactNo(updatedEmployee.getContactNo());
            employee.setAddress(updatedEmployee.getAddress());
            employee.setSalaryType(updatedEmployee.getSalaryType());
            employee.setSalary(updatedEmployee.getSalary());
            employee.setAvailability(updatedEmployee.getAvailability());
            employee.setLocation(updatedEmployee.getLocation());
            employee.setEmpPhoto(updatedEmployee.getEmpPhoto());
            employee.setUsername(updatedEmployee.getUsername());
            // Update and hash password only if a new one is provided
            if (updatedEmployee.getPassword() != null && !updatedEmployee.getPassword().isBlank()) {
                String hashedPassword = passwordEncoder.encode(updatedEmployee.getPassword());
                employee.setPassword(hashedPassword);
            }

            // Save updated employee
            Employee savedEmployee = employeeRepository.save(employee);
            return ResponseEntity.ok(savedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
