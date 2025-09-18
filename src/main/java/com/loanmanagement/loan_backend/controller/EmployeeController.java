package com.loanmanagement.loan_backend.controller;

import com.loanmanagement.loan_backend.model.Employee;
import com.loanmanagement.loan_backend.model.Loan;
import com.loanmanagement.loan_backend.repository.EmployeeRepository;
import com.loanmanagement.loan_backend.repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
     @Autowired
     private EmployeeRepository employeeRepository;

      // Get Employee details where Emp_Id
    @GetMapping("/employee/{empId}")
    public ResponseEntity<List<Loan>> getActiveOrNewLoansByEmpId(@PathVariable String empId) {
        List<String> statuses = Arrays.asList("Active", "new");
        List<Loan> loans = loanRepository.findByEmpIdAndLoanStatusIn(empId, statuses);

        if (loans.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(loans);
    }


}
