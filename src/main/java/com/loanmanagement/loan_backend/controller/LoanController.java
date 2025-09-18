package com.loanmanagement.loan_backend.controller;

import com.loanmanagement.loan_backend.model.Loan;
import com.loanmanagement.loan_backend.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/loans")

public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    // Get loans by EmpId where loanStatus = Active or New
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
