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

    /** Register a new employee */
    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody Employee employee) {
        if (employeeRepository.existsByEmpId(employee.getEmpId())) {
            return ResponseEntity.badRequest().body("Employee with this ID already exists.");
        }
        if (employeeRepository.findByUsername(employee.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken.");
        }

        // Encode password before saving
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        Employee savedEmployee = employeeRepository.save(employee);

        return ResponseEntity.ok("Registration successful for: " + savedEmployee.getUsername());
    }

    /** Login employee and return JWT */
    @PostMapping("/login")
    public ResponseEntity<?> loginEmployee(@RequestBody Employee loginRequest) {
        Optional<Employee> optionalEmployee = employeeRepository.findByUsername(loginRequest.getUsername());

        if (optionalEmployee.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }

        Employee employee = optionalEmployee.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), employee.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }

        // JWT stores username, not empId
        String token = jwtUtil.generateToken(employee.getUsername());

        return ResponseEntity.ok().body("Login successful. JWT Token: " + token);
    }

    /** Example protected endpoint */
    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(@RequestAttribute("username") String username) {
        Optional<Employee> employee = employeeRepository.findByUsername(username);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
