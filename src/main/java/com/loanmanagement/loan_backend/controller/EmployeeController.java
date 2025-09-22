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

    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody Employee employee) {
        // Hash the password
        String hashedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(hashedPassword);

        // Save employee to DB
        employeeRepository.save(employee);

        // Generate JWT token
        String token = JwtUtil.generateToken(employee.getEmpId());

        // Return token to mobile app
        return ResponseEntity.ok().body(token);
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

            // Save updated employee
            Employee savedEmployee = employeeRepository.save(employee);
            return ResponseEntity.ok(savedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
