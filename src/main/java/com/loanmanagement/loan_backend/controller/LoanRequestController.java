package com.loanmanagement.loan_backend.controller;

import com.loanmanagement.loan_backend.model.LoanRequest;
import com.loanmanagement.loan_backend.repository.LoanRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/loans")
public class LoanRequestController {

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    /** Apply for a new loan */
    @PostMapping("/apply")
    public ResponseEntity<?> applyLoan(@RequestBody LoanRequest loanRequest) {
        // Always set current date
        loanRequest.setDate(LocalDate.now());

        LoanRequest savedLoanRequest = loanRequestRepository.save(loanRequest);
        return ResponseEntity.ok(savedLoanRequest);
    }
}
