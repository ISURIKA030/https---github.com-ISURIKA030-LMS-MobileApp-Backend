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

        // Encode the raw password ONCE at registration
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Employee savedEmployee = employeeRepository.save(employee);

        return ResponseEntity.ok("Registration successful for: " + savedEmployee.getUsername());
    }

    /** Login employee and return EMPID + JWT */
    @PostMapping("/login")
    public ResponseEntity<?> loginEmployee(@RequestBody Employee loginRequest) {
        Optional<Employee> optionalEmployee = employeeRepository.findByUsername(loginRequest.getUsername());

        if (optionalEmployee.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }

        Employee employee = optionalEmployee.get();

        // ✅ DO NOT encode loginRequest.getPassword() again. Use matches instead.
        boolean passwordOk = passwordEncoder.matches(loginRequest.getPassword(), employee.getPassword());
        if (!passwordOk) {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }

        // Generate JWT for username
        String token = jwtUtil.generateToken(employee.getUsername());

        // Attach empId at beginning (custom format)
        String combinedToken = employee.getEmpId() + "," + token;

        return ResponseEntity.ok().body("Login successful. Token: " + combinedToken);
    }

    /** Protected endpoint: Get logged-in employee profile */
    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(@RequestAttribute("empId") String empId) {
        Optional<Employee> employee = employeeRepository.findById(empId);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /** Edit logged-in employee profile */
    @PutMapping("/edit/me")
    public ResponseEntity<?> updateMyProfile(@RequestAttribute("empId") String empId,
            @RequestBody Employee updatedData) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);

        if (optionalEmployee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Employee employee = optionalEmployee.get();

        // Update fields if provided
        if (updatedData.getEmpName() != null)
            employee.setEmpName(updatedData.getEmpName());
        if (updatedData.getDepartment() != null)
            employee.setDepartment(updatedData.getDepartment());
        if (updatedData.getContactNo() != null)
            employee.setContactNo(updatedData.getContactNo());
        if (updatedData.getSalaryType() != null)
            employee.setSalaryType(updatedData.getSalaryType());
        if (updatedData.getSalary() != null)
            employee.setSalary(updatedData.getSalary());
        if (updatedData.getAvailability() != null)
            employee.setAvailability(updatedData.getAvailability());
        if (updatedData.getUsername() != null)
            employee.setUsername(updatedData.getUsername());

        // Only update password if explicitly provided and not blank
        if (updatedData.getPassword() != null && !updatedData.getPassword().isBlank()) {
            employee.setPassword(passwordEncoder.encode(updatedData.getPassword()));
        } else {
            // keep the current password unchanged
            employee.setPassword(employee.getPassword());
        }

        if (updatedData.getAddress() != null)
            employee.setAddress(updatedData.getAddress());
        if (updatedData.getLocation() != null)
            employee.setLocation(updatedData.getLocation());
        if (updatedData.getEmpPhoto() != null)
            employee.setEmpPhoto(updatedData.getEmpPhoto());

        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(savedEmployee);
    }

}
