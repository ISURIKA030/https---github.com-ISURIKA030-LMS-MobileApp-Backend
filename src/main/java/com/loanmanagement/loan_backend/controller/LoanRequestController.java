package com.loanmanagement.loan_backend.controller;

import com.loanmanagement.loan_backend.model.LoanRequest;
import com.loanmanagement.loan_backend.model.Loan;
import com.loanmanagement.loan_backend.repository.LoanRequestRepository;
import com.loanmanagement.loan_backend.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanRequestController {

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private LoanRepository loanRepository;

    /** Apply for a new loan */
    @PostMapping("/apply")
    public ResponseEntity<?> applyLoan(
            HttpServletRequest request,
            @RequestParam(required = false) String permission,
            @RequestBody LoanRequest loanRequest) {

        String empId = (String) request.getAttribute("empId");
        if (empId == null) {
            return ResponseEntity.status(401).body("Unauthorized: Missing employee ID");
        }

        // Check existing loans for this employee
        List<Loan> activeLoans = loanRepository.findByEmpIdAndLoanStatusIn(
                empId,
                Arrays.asList("new", "Active"));

        if (!activeLoans.isEmpty()) {
            if (permission == null) {
                // User has active loans → ask permission
                return ResponseEntity.badRequest()
                        .body("You already have active loans. Do you have any permission?");
            }

            if (permission.equalsIgnoreCase("yes")) {
                loanRequest.setEmpId(empId);
                loanRequest.setDate(LocalDate.now());
                LoanRequest savedLoan = loanRequestRepository.save(loanRequest);
                return ResponseEntity.ok(savedLoan);
            } else {
                return ResponseEntity.badRequest().body("Loan request denied: No permission.");
            }
        }

        // No active loans → save new loan request
        loanRequest.setEmpId(empId);
        loanRequest.setDate(LocalDate.now());
        loanRequestRepository.save(loanRequest);
        return ResponseEntity.ok("Loan request saved successfully. Current loan list is empty.");
    }
}
