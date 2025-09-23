package com.loanmanagement.loan_backend.controller;

import com.loanmanagement.loan_backend.model.Employee;
import com.loanmanagement.loan_backend.repository.EmployeeRepository;
import com.loanmanagement.loan_backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /** Register a new employee and return JWT */
    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody Employee employee) {

        // Check if employee with same empId exists
        if (employeeRepository.existsByEmpId(employee.getEmpId())) {
            return ResponseEntity.badRequest().body("Employee with this ID already exists.");
        }

        // Hash password
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        // Save employee
        Employee savedEmployee = employeeRepository.save(employee);

        // Generate JWT token
        String token = jwtUtil.generateToken(savedEmployee.getEmpId());

        return ResponseEntity.ok().body("Registration successful. JWT Token: " + token);
    }

    /** Login employee and return JWT */
    @PostMapping("/login")
    public ResponseEntity<?> loginEmployee(@RequestBody Employee loginRequest) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(loginRequest.getEmpId());

        if (optionalEmployee.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid empId or password.");
        }

        Employee employee = optionalEmployee.get();

        // Verify password
        if (!passwordEncoder.matches(loginRequest.getPassword(), employee.getPassword())) {
            return ResponseEntity.status(401).body("Invalid empId or password.");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(employee.getEmpId());
        return ResponseEntity.ok("Login successful. JWT Token: " + token);
    }

    /** Protected endpoint example */
    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String empId) {
        Optional<Employee> employee = employeeRepository.findById(empId);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
